package com.bpcl.controller;

import java.net.SocketException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bpcl.bean.PailBean;
import com.bpcl.dto.ProductionPlan;
import com.bpcl.service.BottleInspectionService;
import com.bpcl.service.ProductionPlanService;

@Controller
@RequestMapping("/pail/api/")
public class BottleInspectionController {
	@Autowired
	private BottleInspectionService bottleInspectionService;

	@RequestMapping("/page")
	public String pailPrintingPage(Model model) {
		return "pailPrinting";

	}

	@RequestMapping("/printerStatus")
	public String printerStatus(Model model) {
		return "printerStatus";

	}

	@RequestMapping("/insert")
	@ResponseBody
	public String callingThreadMethode(@RequestBody PailBean pailBean) throws SocketException {
		
		if (null != pailBean) {
			String result = bottleInspectionService.callingThreadMethode(pailBean);

			return result;
		}
		return "Parameter Missing";

	}

}
