package com.bpcl.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

import com.bpcl.constant.AppConstant;
import com.bpcl.dto.PailsProductionScanner;
import com.bpcl.dto.ProductionPlan;
import com.bpcl.service.PailsProductionScannerService;
import com.bpcl.service.PailsProductionService;
import com.bpcl.service.ProductionPlanService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class SseEmitterController {

	@Autowired
	private PailsProductionService pailsProductionService;

	@Autowired
	private PailsProductionScannerService pailsProductionScannerService;

	@Autowired
	private ProductionPlanService productionPlanService;

	@Autowired
	private ObjectMapper mapper;

	private ExecutorService nonBlockingService = Executors.newCachedThreadPool();
	
	private int planId;

	/**
	 * 
	 * @return
	 */
	@GetMapping("/production/plan")
	public SseEmitter getProductionPlan() {
//		System.out.println("SseEmitterController.getProductionPlan()");
		SseEmitter emitter = new SseEmitter();
		SseEventBuilder eventBuilder = SseEmitter.event();
		nonBlockingService.execute(() -> {
			try {
				ProductionPlan productionPlan = productionPlanService.getByStatus(AppConstant.PLAN_STARTED);
				if(productionPlan!=null) {
					planId=productionPlan.getPlanId();
				}else {
					planId=0;
				}
				
				emitter.send(eventBuilder.data(
						mapper.writeValueAsString(productionPlan) + "\n\n")
						.name("dataSet-created").id("123456").reconnectTime(300)

				);

				emitter.complete();
			} catch (Exception ex) {
				emitter.completeWithError(ex);
			}
		});
		return emitter;
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/pails/production")
	public SseEmitter getPailsProduction() {
		
		SseEmitter emitter = new SseEmitter();
		SseEventBuilder eventBuilder = SseEmitter.event();
		nonBlockingService.execute(() -> {
			try {
				System.out.println(planId);
				int compQnty = pailsProductionService.getPailsComp(planId);
				
				PailsProductionScanner pailsProductionScanner=pailsProductionScannerService.get();
				pailsProductionScanner.setCompQnty(compQnty);
				
				emitter.send(eventBuilder.data(mapper.writeValueAsString(pailsProductionScanner) + "\n\n")
						.name("dataSet-created").id("123456").reconnectTime(300)

				);

				emitter.complete();
			} catch (Exception ex) {
				emitter.completeWithError(ex);
			}
		});
		return emitter;
	}
	
	@GetMapping("index")
	public String indexPage() {
		return "index";
	}

}
