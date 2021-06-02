package com.bpcl.mapper;

import java.util.List;
import java.util.Map;

import com.bpcl.dto.CouponInspections;
import com.bpcl.util.IMapper;

public interface CouponInspectionsMapper  <T extends CouponInspections> extends IMapper<T>{

	void batchUpdateStatus(List<CouponInspections> couponLeaserPrintingListGet);

	int getCountOfLotCode();

	List<CouponInspections> getInfoByLotCode();

	List<CouponInspections> getByLimit(Map<String, Object> map);

	List<CouponInspections> getByPrintingStatusChange(Map<String, Object> map);

	int deleteAll();

}
