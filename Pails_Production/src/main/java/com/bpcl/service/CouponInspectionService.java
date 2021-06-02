package com.bpcl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpcl.dto.CouponInspection;
import com.bpcl.mapper.CouponInspectionMapper;
import com.bpcl.util.BaseServiceSupport;
import com.bpcl.util.IMapper;
import com.bpcl.util.UUIDGeneratorUtil;

@Service
public class CouponInspectionService extends BaseServiceSupport<CouponInspection> {

	@Autowired
	private CouponInspectionMapper<CouponInspection> couponInspectionMapper;

	@Override
	public IMapper<CouponInspection> getMapper() {
		return couponInspectionMapper;
	}

	@Override
	public String getPK() {
		return "uuid";
	}

	/**
	 * This functionality is used for get the list of CouponInspection
	 * 
	 * @param shParam
	 * @return
	 */
	public List<CouponInspection> getList(Map<String, Object> shParam) {
		return couponInspectionMapper.query(shParam);
	}

	/**
	 * 
	 * @param convertCouponInspectionList
	 * @return
	 */
	public int batchInsert(List<CouponInspection> convertCouponInspectionList) {
		return couponInspectionMapper.batchInsert(convertCouponInspectionList);
	}

	/**
	 * 
	 * @param couponCode
	 * @return
	 */
	public CouponInspection getByCouponCode(String couponCode,int status) {
		Map<String, Object> shParam = new HashMap<>(1);
		shParam.put("couponCode", couponCode);
		shParam.put("status", status);

		return couponInspectionMapper.getInfoByMap(shParam);

	}

	/**
	 * 
	 * @param couponInspection
	 * @return
	 */
	public int insert(CouponInspection couponInspection) {
		couponInspection.setUuid(UUIDGeneratorUtil.longUuidGent());
		return couponInspectionMapper.insert(couponInspection);
	}

	/**
	 * 
	 * @param couponCode
	 * @param status
	 * @return
	 */
	public int updateByCouponCode(String couponCode, Integer status) {

		return couponInspectionMapper.updateByCouponCode(couponCode, status);
	}

}
