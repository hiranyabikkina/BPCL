package com.bpcl.controller;

import java.net.SocketException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bpcl.bean.CouponInspectionsBean;
import com.bpcl.dto.LineInformation;
import com.bpcl.service.CouponInspectionsService;
import com.bpcl.service.LineInformationService;

@Controller
@RequestMapping("/coupon/")
public class CouponInspectionsController {
	@Autowired
	private CouponInspectionsService couponInspectionsService;

	@RequestMapping("page")
	public String pageReturn(Model model) throws SocketException {	

		return "couponInsertion";

	}

	@RequestMapping("insert")
	@ResponseBody
	public String insert(@RequestBody CouponInspectionsBean couponInspectionsBean, Model model) {

		System.out.println(couponInspectionsBean + "==================");
		if (couponInspectionsBean != null) {
			String returnString = couponInspectionsService.threadCalling(couponInspectionsBean);
			
			return returnString;
		}
		return "Parameter Are Missing !!";
		

	}

}
