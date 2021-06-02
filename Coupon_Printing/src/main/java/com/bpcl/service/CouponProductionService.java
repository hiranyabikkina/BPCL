package com.bpcl.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpcl.dto.CouponProduction;
import com.bpcl.mapper.CouponProductionMapper;
import com.bpcl.util.BaseServiceSupport;
import com.bpcl.util.DateUtil;
import com.bpcl.util.IMapper;

@Service
public class CouponProductionService extends BaseServiceSupport<CouponProduction> {
	@Autowired
	private CouponProductionMapper<CouponProduction> couponProductionMapper;

	@Override
	public IMapper<CouponProduction> getMapper() {
		// TODO Auto-generated method stub
		return couponProductionMapper;
	}

	@Override
	public String getPK() {
		// TODO Auto-generated method stub
		return "uuid";
	}

	public int insert(CouponProduction couponProduction) {
		// TODO Auto-generated method stub
		couponProduction.setCreateTime(DateUtil.getSystemTime());
		return couponProductionMapper.insert(couponProduction);
	}

	public int update(CouponProduction couponProduction) {
		// TODO Auto-generated method stub
		return couponProductionMapper.update(couponProduction);
	}

	public CouponProduction getByDesOrderDate() {

		return couponProductionMapper.getByDesOrderDate();
	}

	public CouponProduction getInfoByMap(Integer status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		return couponProductionMapper.getInfoByMap(map);
	}

	public CouponProduction getInfoByUuid(String couponProdUuid) {

		return couponProductionMapper.getInfoByUuid(couponProdUuid);
	}

}
