package com.bpcl.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpcl.dto.PailsProduction;
import com.bpcl.mapper.PailsProductionMapper;
import com.bpcl.util.BaseServiceSupport;
import com.bpcl.util.IMapper;

@Service
public class PailsProductionService extends BaseServiceSupport<PailsProduction> {

	@Autowired
	private PailsProductionMapper<PailsProduction> pailsProductionMapper;

	@Override
	public IMapper<PailsProduction> getMapper() {
		return pailsProductionMapper;
	}

	@Override
	public String getPK() {
		return "uuid";
	}

	/**
	 * 
	 * @param pailsProduction
	 * @return
	 */
	public int insert(PailsProduction pailsProduction) {

		pailsProduction.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
		return pailsProductionMapper.insert(pailsProduction);
//		return 0;
	}

	/**
	 * 
	 * @param couponCode
	 * @return
	 */
	public PailsProduction getByCouponCode(String couponCode) {
		Map<String, Object> shParam = new HashMap<>(1);
		shParam.put("couponCode", couponCode);
		shParam.put("status", 0);

		return pailsProductionMapper.getInfoByMap(shParam);

	}

	/**
	 * 
	 * @param bottleCode
	 * @return
	 */
	public PailsProduction getByBottleCode(String bottleCode) {
		Map<String, Object> shParam = new HashMap<>(1);
		shParam.put("bottleCode", bottleCode);

		return pailsProductionMapper.getInfoByMap(shParam);

	}

	/**
	 * 
	 * @param bottleCode
	 * @param couponCode
	 * @return
	 */
	public PailsProduction getByBottleCodeCouponCode(String bottleCode, String couponCode) {
		Map<String, Object> shParam = new HashMap<>(1);
		shParam.put("bottleCode", bottleCode);
		shParam.put("couponCode", couponCode);

		return pailsProductionMapper.getInfoByMap(shParam);
	}

	public int getPailsComp(Integer planId) {
		Map<String, Object> shParam = new HashMap<>();
		shParam.put("planId", planId);

		return pailsProductionMapper.count(shParam);
	}

}
