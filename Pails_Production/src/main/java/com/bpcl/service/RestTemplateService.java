package com.bpcl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bpcl.bean.ReqProdData;
import com.bpcl.dto.ProductionPlan;

@Service
public class RestTemplateService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${pord.req.url}")
	private String reqProdDataurl;

	/**
	 * 
	 * @param productionPlan
	 * @return
	 */
	public ResponseEntity<String> requestProductionData(ProductionPlan productionPlan) {

		ReqProdData reqProdData = new ReqProdData();
		reqProdData.setProductionLineId(productionPlan.getPlanId());
		reqProdData.setLineNo(productionPlan.getLineUuid());
		reqProdData.setLocCode(productionPlan.getLocCode());

		ResponseEntity<String> response = restTemplate.postForEntity(reqProdDataurl, reqProdData, String.class);

		return response;

	}

}
