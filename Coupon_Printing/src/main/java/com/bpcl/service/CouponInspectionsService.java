package com.bpcl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpcl.bean.CouponInspectionsBean;
import com.bpcl.dto.CouponInspections;
import com.bpcl.dto.CouponProduction;
import com.bpcl.dto.LineInformation;
import com.bpcl.mapper.CouponInspectionsMapper;
import com.bpcl.util.BaseServiceSupport;
import com.bpcl.util.CouponInsertThread;
import com.bpcl.util.DateUtil;
import com.bpcl.util.IMapper;

@Service
public class CouponInspectionsService extends BaseServiceSupport<CouponInspections> {
	
	@Autowired
	private CouponInspectionsMapper<CouponInspections> couponInspectionsMapper;
	@Autowired
	private CouponInspectionsService couponInspectionsService;
	@Autowired
	private CouponInspectionsMongoService couponInspectionsMongoService;
	@Autowired
	private CouponProductionService couponProductionService;
	@Autowired
	private LineInformationService lineInformationService;
	@Autowired
	private PrinterStateService printerStateService;
	
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	@Override
	public IMapper<CouponInspections> getMapper() {
		// TODO Auto-generated method stub
		return couponInspectionsMapper;
	}

	@Override
	public String getPK() {
		// TODO Auto-generated method stub
		return "uuid";
	}

	public String threadCalling(CouponInspectionsBean couponInspectionsBean) {
		if (couponInspectionsBean.getQuantity() != null && couponInspectionsBean.getLotCode() != null) {

			String couponProdUuid = UUID.randomUUID().toString().replaceAll("-", "");

			CouponProduction couponProduction = new CouponProduction();
			couponProduction.setUuid(couponProdUuid);
			couponProduction.setCouponQuantity(couponInspectionsBean.getQuantity());
			couponProduction.setLotCodeQuantity(couponInspectionsBean.getLotCode());
			couponProduction.setCouponSize(couponInspectionsBean.getCouponSize());
			couponProduction.setCreateTime(DateUtil.getSystemTime());
			couponProduction.setStatus(0);

			int result = couponProductionService.insert(couponProduction);
			LineInformation lineInformation = lineInformationService.orderByCreateTime();
			System.out.println(lineInformation);
			System.out.println(lineInformation != null);
			if (lineInformation != null) {
				String convertCouponBarcodeString = couponQuantityWiseCreateAndInsert(
						couponInspectionsBean, lineInformation);
				System.out.println(convertCouponBarcodeString);
				if (convertCouponBarcodeString != null) {
					CouponInsertThread couponInsertThread = new CouponInsertThread(couponInspectionsBean.getQuantity(),
							couponInspectionsService, couponProductionService,
							lineInformation, couponProdUuid, printerStateService, convertCouponBarcodeString,couponInspectionsBean.getCouponSize());

					couponInsertThread.start();
					return "success";
				}

				return "Hardware configuration failed";
			}

		}

		return "Required parameters are missing";
	}

	public CouponInspections getByCouponId(String CouponId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("CouponId", CouponId);
		return couponInspectionsMapper.getInfoByMap(map);
	}

	public int insert(CouponInspections couponLeaserPrinting) {

		return couponInspectionsMapper.insert(couponLeaserPrinting);
	}

	public CouponInspections getInfoByMap(String couponId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("couponCode", couponId);
		// TODO Auto-generated method stub
		return couponInspectionsMapper.getInfoByMap(map);
	}

	public List<CouponInspections> query(int status) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);

		return couponInspectionsMapper.query(map);
	}

	public void batchUpdateStatus(List<CouponInspections> couponLeaserPrintingListGet) {
		// TODO Auto-generated method stub
		couponInspectionsMapper.batchUpdateStatus(couponLeaserPrintingListGet);
	}

	public int getCountOfLotCode() {
		// TODO Auto-generated method stub
		return couponInspectionsMapper.getCountOfLotCode();
	}

	public List<CouponInspections> getInfoByLotCode() {
		// TODO Auto-generated method stub
		return couponInspectionsMapper.getInfoByLotCode();
	}

	public int update(CouponInspections couponInspections) {
		// TODO Auto-generated method stub
		return couponInspectionsMapper.update(couponInspections);
	}

	public List<CouponInspections> getByPrintingStatusChange(String pendingValues) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pendingValues", pendingValues);
		map.put("status", 1);
		return couponInspectionsMapper.getByPrintingStatusChange(map);
	}

	public int deleteAll() {
		return couponInspectionsMapper.deleteAll();
		// TODO Auto-generated method stub

	}

	public String couponQuantityWiseCreateAndInsert(CouponInspectionsBean couponInspectionsBean, LineInformation lineInformation) {
		String convertCouponBarcodeString = null;
		List<String> couponBarCodeList = new ArrayList<String>();
		IntStream stream=IntStream.rangeClosed(0, couponInspectionsBean.getQuantity()).limit(couponInspectionsBean.getQuantity()); // iterates 1 to 1000
		stream.// converts to parallel stream
				forEach(index -> {			

					CouponInspections couponInspections = new CouponInspections();
					couponInspections.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
					couponInspections.setCouponCode(
							"W" + DateUtil.yymmdd()+randomAlphaNumeric(9));
					couponInspections.setStatus(0);
					couponInspections.setPlantLocationCode(lineInformation.getLocCode());
					couponInspections.setCouponSize(couponInspectionsBean.getCouponSize());
					CouponInspections couponLeaserPrinting2 = couponInspectionsService
							.getInfoByMap(couponInspections.getCouponCode());
					if (couponLeaserPrinting2 == null) {
						couponInspectionsMapper.insert(couponInspections);
						couponInspectionsMongoService.insert(couponInspections.getCouponCode(),
								couponInspections.getLotCode(),couponInspections.getCouponSize());
						couponBarCodeList.add(couponInspections.getCouponCode());
					} else {
						
						index--;
					}

				});

		if (couponBarCodeList.size() > 0) {
			System.out.println(couponBarCodeList.size() + "  size  ");
			convertCouponBarcodeString = couponBarCodeList.stream()
					.map(e -> e+ "|").reduce("|", String::concat);
		}

		return convertCouponBarcodeString;

	}
	
	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
		int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
		builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		
		return builder.toString();
		}

}
