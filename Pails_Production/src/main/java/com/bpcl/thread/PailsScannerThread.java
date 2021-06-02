package com.bpcl.thread;

import java.io.IOException;
import java.util.regex.Pattern;

import com.bpcl.constant.AppConstant;
import com.bpcl.dto.BottleInspection;
import com.bpcl.dto.CouponInspection;
import com.bpcl.dto.PailsProduction;
import com.bpcl.dto.ProductionPlan;
import com.bpcl.listener.KafkaEventListener;
import com.bpcl.producer.KafkaProducerService;
import com.bpcl.service.BottleInspectionService;
import com.bpcl.service.CouponInspectionService;
import com.bpcl.service.PailsProductionScannerService;
import com.bpcl.service.PailsProductionService;
import com.bpcl.service.ProductionPlanService;
import com.bpcl.util.DateUtil;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class PailsScannerThread extends Thread {

	private SerialPort serialPort;

	private ProductionPlan productionPlan;

	private PailsProductionService pailsProductionService;

	private BottleInspectionService bottleInspectionService;

	private CouponInspectionService couponInspectionService;

	private ProductionPlanService productionPlanService;

	private KafkaProducerService kafkaProducerService;

	private KafkaEventListener kafkaEventListener;

	private PailsProductionScannerService pailsProductionScannerService;

	public PailsScannerThread(ProductionPlan productionPlan, PailsProductionService pailsProductionService,
			BottleInspectionService bottleInspectionService, CouponInspectionService couponInspectionService,
			ProductionPlanService productionPlanService, KafkaProducerService kafkaProducerService,
			PailsProductionScannerService pailsProductionScannerService, KafkaEventListener kafkaEventListener) {
		this.productionPlan = productionPlan;
		this.pailsProductionService = pailsProductionService;
		this.bottleInspectionService = bottleInspectionService;
		this.couponInspectionService = couponInspectionService;
		this.productionPlanService = productionPlanService;
		this.kafkaProducerService = kafkaProducerService;
		this.pailsProductionScannerService = pailsProductionScannerService;
		this.kafkaEventListener = kafkaEventListener;
	}

	public PailsScannerThread(ProductionPlan productionPlan2, PailsProductionService pailsProductionService2,
			BottleInspectionService bottleInspectionService2, CouponInspectionService couponInspectionService2,
			ProductionPlanService productionPlanService2, KafkaProducerService kafkaProducerService2,
			PailsProductionScannerService pailsProductionScannerService2) {
		// TODO Auto-generated constructor stub
	}

	public void setProductionPlan(ProductionPlan productionPlan) {
		this.productionPlan = productionPlan;

	}

	public void startScan() throws SerialPortException, IOException {

		System.out.println("PailsScanner.startScan()");
		String[] portNames = SerialPortList.getPortNames("/dev/",
				Pattern.compile("ttyS|ttyUSB|ttyACM|ttyAMA|rfcomm|ttyO[0-9]{1,3}"));

		System.out.println(portNames.length);
		if (serialPort == null || !serialPort.isOpened()) {

			serialPort = new SerialPort(portNames[0]);

			// Open serial port
			serialPort.openPort();

			// Set params.
			serialPort.setParams(4800, 8, 1, 0);

		}

		PailsProduction pailsProduction = new PailsProduction();
		pailsProduction
				.setIsCouponApply(productionPlan.getIsCouponApply().equalsIgnoreCase("yes") ? AppConstant.COUPON_AVL
						: AppConstant.COUPON_NOT_AVL);

//		String insType = "";
		while (true) {
//			System.out.println("while");

			byte[] buffer = serialPort.readBytes();
			if (productionPlan.getStatus() == AppConstant.PLAN_STARTED) {
				if (buffer != null) {

					String barcode = new String(buffer).trim();
					System.out.println(barcode);
					// Coupon not available
					
					if (productionPlan.getIsCouponApply().equalsIgnoreCase("no")) {
						pailsProductionScannerService.updateToEmpty();
						
							BottleInspection existBottleInspection = bottleInspectionService.getByBottleCode(barcode,
									AppConstant.LABEL_NEW);
							if (existBottleInspection == null) {
								System.out.println("Invalid BottleCode");
								pailsProductionScannerService.updateBottleCode(barcode, 1);

							} else {
								pailsProduction.setBottleCode(barcode);
								pailsProduction.setStatus(AppConstant.PAILS_COMPLETED);

								pailsProductionScannerService.updateBottleCode(barcode, 0);
							}

						

					}
					// Coupon available
					else {

						if (pailsProduction.getCouponCode() != null) {
							BottleInspection existBottleInspection = bottleInspectionService.getByBottleCode(barcode,
									AppConstant.LABEL_NEW);
							if (existBottleInspection == null) {
								System.out.println("Invalid BottleCode");
								pailsProductionScannerService.updateBottleCode(barcode, 1);
							} else {
								System.out.println("BottleCode");
								pailsProduction.setBottleCode(barcode);
								pailsProduction.setStatus(AppConstant.PAILS_COMPLETED);

								pailsProductionScannerService.updateBottleCode(barcode, 0);
							}

						} else if (pailsProduction.getBottleCode() != null) {
							CouponInspection existCouponInspection = couponInspectionService.getByCouponCode(barcode,
									AppConstant.COUPON_NEW);
							if (existCouponInspection == null) {
								System.out.println("Invalid CouponCode");
								pailsProductionScannerService.updateCouponCode(barcode, 1);
							} else {
								System.out.println("CouponCode");
								pailsProduction.setCouponCode(barcode);
								pailsProduction.setStatus(AppConstant.PAILS_COMPLETED);

								pailsProductionScannerService.updateCouponCode(barcode, 0);
							}

						} else {
							pailsProductionScannerService.updateToEmpty();
							BottleInspection existBottleInspection = bottleInspectionService.getByBottleCode(barcode,
									AppConstant.LABEL_NEW);
							if (existBottleInspection != null) {
								System.out.println("BottleCode");
								pailsProduction.setBottleCode(barcode);
								pailsProduction.setStatus(AppConstant.PAILS_NEW);
								pailsProductionScannerService.updateBottleCode(barcode, 0);

							} else {
								CouponInspection existCouponInspection = couponInspectionService
										.getByCouponCode(barcode, AppConstant.COUPON_NEW);
								if (existCouponInspection != null) {
									System.out.println("CouponCode");
									pailsProduction.setCouponCode(barcode);
									pailsProduction.setStatus(AppConstant.PAILS_NEW);

									pailsProductionScannerService.updateCouponCode(barcode, 0);
								} else {
									pailsProductionScannerService.updateBottleCode(barcode, 1);
									pailsProductionScannerService.updateCouponCode(barcode, 1);
									System.out.println("Invalid Coupon/Bottle Code");
								}
							}

						}

					}
System.out.println(pailsProduction.getStatus());
					// Check condition for Pail creation
					if (pailsProduction.getStatus() != null
							&& pailsProduction.getStatus() == AppConstant.PAILS_COMPLETED) {

						PailsProduction existPailsProduction = pailsProductionService.getByBottleCodeCouponCode(
								pailsProduction.getBottleCode(), pailsProduction.getCouponCode());

						if (existPailsProduction != null) {
							System.out.println("Already Produced");
						} else {
							pailsProduction.setPlanId("" + productionPlan.getPlanId());
							pailsProduction.setLineId(productionPlan.getLineUuid());
							pailsProduction.setIsCouponApply(
									productionPlan.getIsCouponApply().equalsIgnoreCase("yes") ? AppConstant.COUPON_AVL
											: AppConstant.COUPON_NOT_AVL);
							pailsProduction.setPackType(productionPlan.getPackType());
							pailsProduction.setCreateTime(DateUtil.getSystemTime());
							int result = pailsProductionService.insert(pailsProduction);
							if (result > 0) {

								pailsProductionScannerService.updateToEmpty();

								System.out.println(productionPlan.getPackType() + " inserted\n");
								couponInspectionService.updateByCouponCode(pailsProduction.getCouponCode(),
										AppConstant.COUPON_USED);
								bottleInspectionService.updateByBottleCode(pailsProduction.getBottleCode(),
										AppConstant.LABEL_USED);

								// send to central
								kafkaProducerService.pailBarrelProduce(pailsProduction, productionPlan);

								pailsProduction = new PailsProduction();

								int countCompPails = pailsProductionService.getPailsComp(productionPlan.getPlanId());

								if (productionPlan.getVolume() == countCompPails) {
									productionPlan.setStatus(AppConstant.PLAN_COMPLETED);
									productionPlanService.statusUpdatedByPlanId(productionPlan.getPlanId(),
											AppConstant.PLAN_COMPLETED);
									pailsProductionScannerService.updateToEmpty();
								}

							} else {
								System.out.println("Insert Failed");
							}
						}

					}

				}

			} else {
//				System.out.println("Production Completed or not started");
			}

		}

	}

	public boolean close() {
		try {
			return serialPort.closePort();

		} catch (SerialPortException e) {
			return false;
		}

	}

	@Override
	public void run() {
		try {
			startScan();
		} catch (SerialPortException | IOException | ArrayIndexOutOfBoundsException e) {
			kafkaEventListener.setPailsScannerThread(null);
			System.out.println("execption");
			e.printStackTrace();
			this.stop();

		}
	}

}
