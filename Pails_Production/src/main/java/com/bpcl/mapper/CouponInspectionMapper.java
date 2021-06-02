package com.bpcl.mapper;

import java.util.List;

import com.bpcl.dto.CouponInspection;
import com.bpcl.util.IMapper;

/**
 * 
 * @author Pradeep
 *
 * @param <T>
 */
public interface CouponInspectionMapper<T extends CouponInspection> extends IMapper<T> {

	int batchInsert(List<CouponInspection> convertCouponInspectionList);

	int updateByCouponCode(String couponCode, Integer status);

}
