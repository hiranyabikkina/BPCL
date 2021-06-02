package com.bpcl.schedule;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.bpcl.bean.CouponInspectionsToKafka;
import com.bpcl.constant.AppConstants;
import com.bpcl.dto.CouponInspectionsMongo;
import com.bpcl.dto.CouponProduction;
import com.bpcl.dto.LineInformation;
import com.bpcl.loccodeprint.ZXingHelper;
import com.bpcl.service.CouponInspectionsMongoService;
import com.bpcl.service.CouponProductionService;
import com.bpcl.service.LineInformationService;
import com.bpcl.util.FileStoredPath;
import com.bpcl.util.UUIDGeneratedUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LotCodeSchedule {
	@Autowired
	private CouponProductionService couponProductionService;
	@Autowired
	private CouponInspectionsMongoService couponInspectionsMongoService;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	@Autowired
	private LineInformationService LineInformationService;

	@Scheduled(fixedRate = 15000)
	public void lotCodeProducer() throws IOException, InterruptedException {
		LineInformation LineInformation = LineInformationService.orderByCreateTime();
		CouponProduction couponProduction = couponProductionService.getByDesOrderDate();

		List<CouponInspectionsMongo> list = couponInspectionsMongoService.getbyStatus(4,couponProduction.getCouponSize());

		List<String> couponList = new ArrayList<String>();
		if (couponProduction != null && list.size() > 0) {
			int start = 0;
			int end = couponProduction.getLotCodeQuantity();
			while (list.size() >= end) {

				if (couponProduction.getLotCodeQuantity() <= list.size()) {

					String countryCode = "890";

					DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
					Date date = new Date();
					String todayDate = dateformat.format(date).toString().replaceAll("/", "");
					String lotCode = countryCode + LineInformation.getLocCode() + todayDate
							+ Long.toString(UUIDGeneratedUtil.generateLotCode());

					for (CouponInspectionsMongo CouponInspectionsMongo : list.subList(start, end)) {
						CouponInspectionsMongo.setLotCode(lotCode);
						CouponInspectionsMongo.setStatus(5);
						couponInspectionsMongoService.update(CouponInspectionsMongo);

						couponList.add(CouponInspectionsMongo.getCouponCode());
					}
					CouponInspectionsToKafka couponInspectionsToKafka = new CouponInspectionsToKafka();
					couponInspectionsToKafka.setCouponSize(list.get(0).getCouponSize());
					couponInspectionsToKafka.setLotCode(lotCode);
					couponInspectionsToKafka.setPlantLocation(LineInformation.getLocCode());
					couponInspectionsToKafka.setCouponCode(couponList);

					kafkaTemplate.send(AppConstants.TOPIC_NAME, AppConstants.GROUP_ID,
							objectMapper.writeValueAsString(couponInspectionsToKafka));
					start = end;
					end = end + couponProduction.getLotCodeQuantity();
					couponList.clear();
					FileStoredPath.filePath(lotCode);

				}
			}

		}

	}
}
