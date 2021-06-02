package com.bpcl.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpcl.dto.ProductionPlan;
import com.bpcl.mapper.ProductionPlanMapper;
import com.bpcl.util.BaseServiceSupport;
import com.bpcl.util.DateUtil;
import com.bpcl.util.IMapper;

@Service
public class ProductionPlanService extends BaseServiceSupport<ProductionPlan> {

	@Autowired
	private ProductionPlanMapper<ProductionPlan> productionPlanMapper;

	@Override
	public IMapper<ProductionPlan> getMapper() {
		return productionPlanMapper;
	}

	@Override
	public String getPK() {
		return "uuid";
	}

	public int insert(ProductionPlan productionPlan) {
		productionPlan.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
		productionPlan.setCreateTime(DateUtil.getSystemTime());
		return productionPlanMapper.insert(productionPlan);
	}

	public ProductionPlan getByPlanId(Integer planId) {

		Map<String, Object> shParam = new HashMap<>(1);
		shParam.put("planId", planId);
		return productionPlanMapper.getInfoByMap(shParam);
	}

	public int update(ProductionPlan productionPlan) {
		return productionPlanMapper.update(productionPlan);

	}

	public int statusUpdatedByPlanId(Integer planId, int status) {
		Map<String, Object> shParam = new HashMap<>(1);
		shParam.put("status", status);
		shParam.put("planId", planId);

		return productionPlanMapper.statusUpdatedByPlanId(shParam);
	}

	public ProductionPlan getByStatus(int status) {
		Map<String, Object> shParam = new HashMap<>(1);
		shParam.put("status", status);
		ProductionPlan productionPlan = productionPlanMapper.getInfoByMap(shParam);

		return productionPlan;
	}

}
