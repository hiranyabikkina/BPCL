package com.bpcl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bpcl.dto.CouponProduction;
import com.bpcl.service.CouponProductionService;

@Controller
@RequestMapping("/api/coupon/production/")
public class CouponProductionController {
	@Autowired
	private CouponProductionService couponProductionService;
	@RequestMapping("insert")
	public int insert(@RequestBody CouponProduction couponProduction)
	{
		return couponProductionService.insert(couponProduction);
		
	}
	@RequestMapping("update")
	public int update(@RequestBody CouponProduction couponProduction)
	{
		return couponProductionService.update(couponProduction);
		
	}
	
}
