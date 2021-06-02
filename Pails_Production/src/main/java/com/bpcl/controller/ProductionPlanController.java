package com.bpcl.controller;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bpcl.constant.AppConstant;
import com.bpcl.dto.ProductionPlan;
import com.bpcl.listener.KafkaEventListener;
import com.bpcl.service.ProductionPlanService;
import com.bpcl.util.ResponseBean;

@Controller
@RequestMapping("/production/plan/")
public class ProductionPlanController {

	@Autowired
	private ProductionPlanService productionPlanService;
	
	@Autowired
	private KafkaEventListener kafkaEventListener;

	@RequestMapping("insert")
	@ResponseBody
	public ResponseBean<String> insert(@RequestBody String productionLineMsg) throws IOException, ParseException {
		
		System.out.println(productionLineMsg  +"    productionLineMsg");
		int result = 0;
		
		/*ProductionPlan existProductionPlan = productionPlanService.getByPlanId(productionPlan.getPlanId());
		if (existProductionPlan != null) {
			productionPlan.setUuid(existProductionPlan.getUuid());

			result = productionPlanService.update(productionPlan);
//	    	 System.out.println(result+"updated");
		} else {
			result = productionPlanService.insert(productionPlan);
//				System.out.println(result+"inserted");
		}
		if (result <= 0) {
			return ResponseBean.createSuccess("Insert ProductionPlan failed");
		}*/
		
		kafkaEventListener.productionPlanListener(productionLineMsg);
		
		return ResponseBean.createSuccess("Insert ProductionPlan successful");
	}
	
	
	@RequestMapping("get")
	@ResponseBody
	public ResponseBean<ProductionPlan> getStartedProdPlan() {

		ProductionPlan productionPlan = productionPlanService.getByStatus(AppConstant.PLAN_STARTED);
		return ResponseBean.createSuccess("Fatch data successful", productionPlan);
	}

}
