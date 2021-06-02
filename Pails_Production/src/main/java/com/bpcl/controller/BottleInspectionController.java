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

import com.bpcl.dto.BottleInspection;
import com.bpcl.service.BottleInspectionService;
import com.bpcl.util.ResponseBean;

@Controller
@RequestMapping("/bottle/inspection/")
public class BottleInspectionController {

	@Autowired
	private BottleInspectionService bottleInspectionService;

	/**
	 * This functionality used for get list of BottleInspection based on status
	 * 3(inspected)
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	public ResponseBean<List<BottleInspection>> list(HttpServletRequest request) {

		System.out.println("BottleInspectionController.list()");

		Map<String, Object> shParam = new HashMap<>();
		shParam.put("status", 3);

		List<BottleInspection> bottleInspections = bottleInspectionService.getList(shParam);

		return ResponseBean.createSuccess("Fatch BottleInspection list successfully", bottleInspections);

	}

	@RequestMapping("insert")
	@ResponseBody
	public ResponseBean<String> insert(@RequestBody BottleInspection bottleInspection) {
		int result = bottleInspectionService.insert(bottleInspection);
		if (result <= 0) {
			return ResponseBean.createSuccess("Insert BottleInspection failed");
		}
		return ResponseBean.createSuccess("Insert BottleInspection successful");
	}

}
