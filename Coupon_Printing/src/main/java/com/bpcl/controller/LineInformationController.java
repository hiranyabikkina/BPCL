package com.bpcl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bpcl.dto.LineInformation;
import com.bpcl.service.LineInformationService;

@Controller
@RequestMapping("/lineinfo/api/")
public class LineInformationController {
@Autowired
	private LineInformationService lineInformationService;
@RequestMapping("insert")
@ResponseBody
	public int insert(@RequestBody LineInformation LineInformation)
	{		
		return lineInformationService.insert(LineInformation);
		
	}
	@RequestMapping("update")
	@ResponseBody
	public int update(@RequestBody LineInformation LineInformation)
	{	
		return lineInformationService.update(LineInformation);
	}
	@RequestMapping("get")
	@ResponseBody
	public LineInformation getByCreateTime()
	{	
		LineInformation lineInformation = lineInformationService.orderByCreateTime();
		return lineInformation;
	}
}
