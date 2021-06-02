package com.bpcl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bpcl.dto.ProductionPlan;
import com.bpcl.service.ProductionPlanService;

@Controller
@RequestMapping("/proplan/api/")
public class ProductionPlanController {
	@Autowired
	private ProductionPlanService productionPlanService;

	@RequestMapping("/get")
	@ResponseBody
	public ProductionPlan getByStatus() {
		return productionPlanService.getByStatus(25);

	}
}
