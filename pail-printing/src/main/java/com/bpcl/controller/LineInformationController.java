package com.bpcl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.bpcl.dto.LineInformation;
import com.bpcl.service.LineInformationService;

@Controller
public class LineInformationController {
	@Autowired
	private LineInformationService lineInformationService;

	public int insert(@RequestBody LineInformation LineInformation) {

		return lineInformationService.insert(LineInformation);

	}

	public int update(@RequestBody LineInformation LineInformation) {

		return lineInformationService.update(LineInformation);

	}
}
