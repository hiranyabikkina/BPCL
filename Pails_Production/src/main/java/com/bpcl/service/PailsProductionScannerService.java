package com.bpcl.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpcl.dto.PailsProductionScanner;
import com.bpcl.mapper.PailsProductionScannerMapper;
import com.bpcl.util.BaseServiceSupport;
import com.bpcl.util.IMapper;

@Service
public class PailsProductionScannerService extends BaseServiceSupport<PailsProductionScanner> {

	@Autowired
	PailsProductionScannerMapper<PailsProductionScanner> pailsProductionScannerMapper;

	@Override
	public IMapper<PailsProductionScanner> getMapper() {
		return pailsProductionScannerMapper;
	}

	@Override
	public String getPK() {
		return "uuid";
	}

	/**
	 * 
	 * @param bottleCode
	 * @return
	 */
	public int updateBottleCode(String bottleCode,Integer bottleCodeStatus) {
		PailsProductionScanner pailsProductionScanner = new PailsProductionScanner();
		pailsProductionScanner.setBottleCode(bottleCode);
		pailsProductionScanner.setBottleCodeStatus(bottleCodeStatus);
		return pailsProductionScannerMapper.update(pailsProductionScanner);
	}

	/**
	 * 
	 * @param couponCode
	 * @return
	 */
	public int updateCouponCode(String couponCode,Integer couponCodeStatus) {
		PailsProductionScanner pailsProductionScanner = new PailsProductionScanner();
		pailsProductionScanner.setCouponCode(couponCode);
		pailsProductionScanner.setCouponCodeStatus(couponCodeStatus);
		return pailsProductionScannerMapper.update(pailsProductionScanner);
	}
	

	/**
	 * 
	 * @return
	 */
	public int updateToEmpty() {
		PailsProductionScanner pailsProductionScanner = new PailsProductionScanner();
		pailsProductionScanner.setBottleCode("");
		pailsProductionScanner.setCouponCode("");	
		pailsProductionScanner.setCouponCodeStatus(2);
		pailsProductionScanner.setBottleCodeStatus(2);
		return pailsProductionScannerMapper.update(pailsProductionScanner);
	}

	/**
	 * 
	 * @return
	 */
	public PailsProductionScanner get() {
		Map<String, Object> shParam = new HashMap<>();

		return pailsProductionScannerMapper.getInfoByMap(shParam);
	}

}
