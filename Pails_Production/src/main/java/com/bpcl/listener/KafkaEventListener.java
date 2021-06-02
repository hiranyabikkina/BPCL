package com.bpcl.listener;

import java.io.IOException;
import java.net.SocketException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.bpcl.bean.CouponDetails;
import com.bpcl.bean.PailsBarrelsDetails;
import com.bpcl.bean.PayloadProdLine;
import com.bpcl.bean.ProdPlanPayload;
import com.bpcl.bean.ResProductionData;
import com.bpcl.constant.AppConstant;
import com.bpcl.dto.BottleInspection;
import com.bpcl.dto.CouponInspection;
import com.bpcl.dto.LineInformation;
import com.bpcl.dto.ProductionPlan;
import com.bpcl.producer.KafkaProducerService;
import com.bpcl.service.BottleInspectionService;
import com.bpcl.service.CouponInspectionService;
import com.bpcl.service.LineInformationService;
import com.bpcl.service.PailsProductionScannerService;
import com.bpcl.service.PailsProductionService;
import com.bpcl.service.ProductionPlanService;
import com.bpcl.service.RestTemplateService;
import com.bpcl.thread.PailsScannerThread;
import com.bpcl.util.DateUtil;
import com.bpcl.util.MacId;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class KafkaEventListener {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private PailsProductionService pailsProductionService;

	@Autowired
	private LineInformationService lineInformationService;

	@Autowired
	private CouponInspectionService couponInspectionService;

	@Autowired
	private BottleInspectionService bottleInspectionService;

	@Autowired
	private ProductionPlanService productionPlanService;

	private PailsScannerThread pailsScannerThread;

	@Autowired
	private RestTemplateService restTemplateService;

	@Autowired
	private KafkaProducerService kafkaProducerService;

	@Autowired
	private PailsProductionScannerService pailsProductionScannerService;

	/**
	 * 
	 * @param pailsScannerThread
	 */
	public void setPailsScannerThread(PailsScannerThread pailsScannerThread) {
		this.pailsScannerThread = pailsScannerThread;
	}

	/**
	 * 
	 * @param lineInformationMsg
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 * @throws SocketException
	 */
	@KafkaListener(topics = "hwEquipments")
	public void lineInformationListener(String lineInformationMsg)
			throws JsonMappingException, JsonProcessingException, SocketException {

		System.out.println(lineInformationMsg + "    ??????????");
		LineInformation lineInformation = objectMapper.readValue(lineInformationMsg, LineInformation.class);
		if (MacId.macId().equalsIgnoreCase(lineInformation.getMacId())) {

			LineInformation existInformation = lineInformationService.getByMacId(lineInformation.getMacId());
			if (existInformation != null) {
				lineInformation.setUuid(existInformation.getUuid());
				lineInformationService.update(lineInformation);
			} else {
				lineInformationService.insert(lineInformation);
			}

		}

	}

	/**
	 * 
	 * @param lineInformationMsg
	 * @throws IOException
	 * @throws ParseException
	 */
	@KafkaListener(topics ="productionLine")
	public void productionPlanListener(String productionLineMsg) throws IOException, ParseException {

//		System.out.println(productionLineMsg + "   !!!!!!!!!!!!!!!!!!");

		ProdPlanPayload productionPlanPayload = objectMapper.readValue(productionLineMsg, ProdPlanPayload.class);

		System.out.println("   productionLineMsg      " + productionPlanPayload);

		LineInformation lineInformation = lineInformationService.getByMacId(MacId.macId());
		System.out.println(lineInformation);

//		System.out.println(productionPlan + "      !!!!!!!!!!!!!!!!   ");

		if(null!=lineInformation)
		{
			if (lineInformation.getLocCode().compareTo(productionPlanPayload.getLocCode()) == 0
					&& lineInformation.getLineNo().equalsIgnoreCase(productionPlanPayload.getLineUuid())) {

				ProductionPlan productionPlan = new ProductionPlan();
				BeanUtils.copyProperties(productionPlanPayload, productionPlan);

//				System.out.println(productionPlan + "     productionPlan");

//				if (productionPlan.getStatus() == AppConstant.PLAN_START) {
//					productionPlan.setStatus(AppConstant.PLAN_STARTED);
//				}

				ProductionPlan existProductionPlan = productionPlanService.getByPlanId(productionPlan.getPlanId());
				if (existProductionPlan != null) {
					productionPlan.setUuid(existProductionPlan.getUuid());
					productionPlan.setLabelPrint(existProductionPlan.getLabelPrint());
					int result = productionPlanService.update(productionPlan);
//			    	 System.out.println(result+"updated");
				} else {
					productionPlan.setLabelPrint("Yes");
					int result = productionPlanService.insert(productionPlan);
//						System.out.println(result+"inserted");
				}

				try {
					ResponseEntity<String> response = restTemplateService.requestProductionData(productionPlan);

					ResProductionData resProductionData = objectMapper.readValue(response.getBody().toString(),
							ResProductionData.class);

					List<PayloadProdLine> payloadProdLines = resProductionData.getPayload();
//					System.out.println("Payload" +payloadProdLines);

					if (payloadProdLines != null) {
						for (PayloadProdLine payloadProdLine : payloadProdLines) {
							System.out.println(payloadProdLine);

							if (productionPlan.getIsCouponApply().equalsIgnoreCase("yes")) {
								CouponDetails couponDetails = payloadProdLine.getCouponDetails();
								this.insertCouponDetails(couponDetails, productionPlan);

							}

							PailsBarrelsDetails pailsBarrelsDetails = payloadProdLine.getPailsBarrelsDetails();

							this.insertPailsBarrelsDetails(pailsBarrelsDetails, productionPlan);

						}
					}

				} catch (Exception e) {
					String message = e.getMessage();
					System.out.println(message);
				}

				if (pailsScannerThread == null) {
					pailsScannerThread = new PailsScannerThread(productionPlan, pailsProductionService,
							bottleInspectionService, couponInspectionService, productionPlanService, kafkaProducerService,pailsProductionScannerService,this);
					pailsScannerThread.start();
					System.out.println("000000000000000000");
				} else {
					System.out.println("111111111111111111111111");
					pailsScannerThread.setProductionPlan(productionPlan);

				}

			}
		}
		

	}

	/**
	 * 
	 * @param couponDetails
	 * @param productionPlan
	 */
	public void insertCouponDetails(CouponDetails couponDetails, ProductionPlan productionPlan) {
		if (couponDetails != null) {
			String lotCode = couponDetails.getLotNo();

			couponDetails.getCouponDetail().parallelStream().forEach(couponCode -> {

				CouponInspection couponInspection = new CouponInspection();

				couponInspection.setCouponCode(couponCode);
				couponInspection.setCouponSize("16X25");
				couponInspection.setCreateTime(DateUtil.getSystemTime());
				couponInspection.setLineUuid(productionPlan.getLineUuid());
				couponInspection.setLotCode(lotCode);
				couponInspection.setPlantLocation(productionPlan.getPlantLocation());
				couponInspection.setStatus(AppConstant.COUPON_NEW);

				couponInspectionService.insert(couponInspection);

			});
			System.out.println(couponDetails.getCouponDetail().size() + "   CouponDetails size");
		}
	}

	/**
	 * 
	 * @param pailsBarrelsDetails
	 * @param productionPlan
	 */
	public void insertPailsBarrelsDetails(PailsBarrelsDetails pailsBarrelsDetails, ProductionPlan productionPlan) {

		if (pailsBarrelsDetails != null) {

			pailsBarrelsDetails.getPailsBarrelsDetail().parallelStream().forEach(bottleCode -> {

				BottleInspection bottleInspection = new BottleInspection();

				bottleInspection.setBottleCode(bottleCode);
				bottleInspection.setCreatedTime(DateUtil.getSystemTime());
				bottleInspection.setLineUuid(productionPlan.getLineUuid());
				bottleInspection.setPlantLocation(productionPlan.getPlantLocation());
				bottleInspection.setStatus(AppConstant.LABEL_NEW);

				bottleInspectionService.insert(bottleInspection);

			});
			System.out.println(pailsBarrelsDetails.getPailsBarrelsDetail().size() + "    pailsBarrelsDetails size");
		}
	}
}
