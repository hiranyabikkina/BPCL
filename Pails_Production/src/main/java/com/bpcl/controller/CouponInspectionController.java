package com.bpcl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bpcl.dto.CouponInspection;
import com.bpcl.service.CouponInspectionService;
import com.bpcl.util.ResponseBean;

@Controller
@RequestMapping("/coupon/inspection/")
public class CouponInspectionController {

	@Autowired
	private CouponInspectionService couponInspectionService;

	/**
	 * This functionality used for get list of CouponInspection based on status
	 * 3(inspected)
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	public ResponseBean<List<CouponInspection>> list(HttpServletRequest request) {

		System.out.println("CouponInspectionController.list()");

		Map<String, Object> shParam = new HashMap<>();
		shParam.put("status", 3);

		List<CouponInspection> couponInspections = couponInspectionService.getList(shParam);

		return ResponseBean.createSuccess("Fatch CouponInspections list successfully", couponInspections);

	}
	
	@RequestMapping("insert")
	@ResponseBody
	public ResponseBean<String> insert(@RequestBody CouponInspection couponInspection) {
		int result=couponInspectionService.insert(couponInspection);
		if(result<=0) {
			return ResponseBean.createSuccess("Insert CouponInspection failed");
		}
		return ResponseBean.createSuccess("Insert CouponInspection successful");
	}

}
