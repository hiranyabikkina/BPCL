package com.bpcl.mapper;

import java.util.Map;

import com.bpcl.dto.ProductionPlan;
import com.bpcl.util.IMapper;

public interface ProductionPlanMapper<T extends ProductionPlan> extends IMapper<T> {

	int statusUpdatedByPlanId(Map<String, Object> shParam);

}
