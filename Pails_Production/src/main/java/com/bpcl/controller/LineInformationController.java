package com.bpcl.controller;

import java.net.SocketException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bpcl.dto.LineInformation;
import com.bpcl.service.LineInformationService;
import com.bpcl.util.MacId;
import com.bpcl.util.ResponseBean;

@Controller
@RequestMapping("/line/information/")
public class LineInformationController {

	@Autowired
	private LineInformationService lineInformationService;

	@RequestMapping("get/by/macid")
	@ResponseBody
	public ResponseBean<LineInformation> getByMACID(@RequestParam String macId) {

		LineInformation lineInformation = lineInformationService.getByMacId(macId);
		return ResponseBean.createSuccess("Fatch data successful", lineInformation);

	}

	@RequestMapping("insert")
	@ResponseBody
	public ResponseBean<String> insert(@RequestBody LineInformation lineInformation) throws SocketException {
		int result = 0;

		if (MacId.macId().equalsIgnoreCase(lineInformation.getMacId())) {

			LineInformation existInformation = lineInformationService.getByMacId(lineInformation.getMacId());
			if (existInformation != null) {
				lineInformation.setUuid(existInformation.getUuid());
				result=lineInformationService.update(lineInformation);
			} else {
				result=lineInformationService.insert(lineInformation);
			}

		}
		if (result <= 0) {
			return ResponseBean.createSuccess("Insert LineInformation failed");
		}
		return ResponseBean.createSuccess("Insert LineInformation successful");
	}
}
