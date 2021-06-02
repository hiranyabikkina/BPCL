package com.bpcl.producer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.bpcl.bean.PackDetails;
import com.bpcl.bean.SendingPailsBarrelBean;
import com.bpcl.dto.PailsProduction;
import com.bpcl.dto.ProductionPlan;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaProducerService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private ObjectMapper mapper;

	public void pailBarrelProduce(PailsProduction pailsProduction, ProductionPlan productionPlan) {

		List<PackDetails> packDetailsList = new ArrayList<>();

		PackDetails packDetails = new PackDetails();
		packDetails.setCouponNo(pailsProduction.getCouponCode());
		packDetails.setLabelCode(pailsProduction.getBottleCode());

		packDetailsList.add(packDetails);

		SendingPailsBarrelBean pailsBarrelBean = new SendingPailsBarrelBean();
		pailsBarrelBean.setPlanId(productionPlan.getPlanId());
		pailsBarrelBean.setBatchNo(productionPlan.getProductBatch());
		pailsBarrelBean.setCaseBarcode(pailsProduction.getBottleCode());
		pailsBarrelBean.setLineNo(productionPlan.getLineUuid());
		pailsBarrelBean.setLocCode("" + productionPlan.getLocCode());
		pailsBarrelBean.setPackDetails(packDetailsList);
		pailsBarrelBean.setProdCode(productionPlan.getProductCode());
		pailsBarrelBean.setProdName(productionPlan.getProductName());
		pailsBarrelBean.setProdSize(productionPlan.getSizeCode());
		pailsBarrelBean.setPackType(productionPlan.getPackType());

		String jsonMessage = null;
		try {
			jsonMessage = mapper.writeValueAsString(pailsBarrelBean);
		} catch (JsonProcessingException e) {
			System.out.println(e.getMessage());
		}
try {
	kafkaTemplate.send("secondaryPackDetails", jsonMessage);
}catch (Exception e) {
	// TODO: handle exception
}
		

	}

}
