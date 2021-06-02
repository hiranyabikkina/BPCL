package com.bpcl.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bpcl.dto.PailsProduction;
import com.bpcl.dto.ProductionPlan;
import com.bpcl.service.BottleInspectionService;
import com.bpcl.service.CouponInspectionService;
import com.bpcl.service.PailsProductionService;
import com.bpcl.service.ProductionPlanService;
import com.bpcl.util.ResponseBean;

@Controller
@RequestMapping("/pails/production/")
public class PailsProductionController {

	@Autowired
	private PailsProductionService pailsProductionService;

	@Autowired
	private BottleInspectionService bottleInspectionService;

	@Autowired
	private CouponInspectionService couponInspectionService;

	@Autowired
	private ProductionPlanService productionPlanService;


	@RequestMapping("start")
	public void start(HttpServletRequest request) throws IOException {

		System.out.println("PailsProductionController.start()");
		ProductionPlan productionPlan = new ProductionPlan();
//		productionPlan.setIsCouponApply(AppConstant.COUPON_AVL);
//		PailsScannerThread pailsScannerThread = new PailsScannerThread(productionPlan, pailsProductionService,
//				bottleInspectionService, couponInspectionService, productionPlanService);

//		pailsScannerThread.start();

	}

	@RequestMapping("productionPage")
	public String productionPage() {
		System.out.println("PailsProductionController.productionPage()");
		return "productionPage";

	}

	@RequestMapping("insert")
	@ResponseBody
	public ResponseBean<String> insert(@RequestBody PailsProduction pailsProduction) {
		int result = pailsProductionService.insert(pailsProduction);
		if (result <= 0) {
			return ResponseBean.createSuccess("Insert PailsProduction failed");
		}
		return ResponseBean.createSuccess("Insert PailsProduction successful");
	}

}
