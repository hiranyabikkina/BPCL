package com.bpcl.mapper;

import com.bpcl.dto.CouponProduction;
import com.bpcl.util.IMapper;

public interface CouponProductionMapper<T extends CouponProduction> extends IMapper<T> {	

	CouponProduction getByDesOrderDate();

	
	
}
