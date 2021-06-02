package com.bpcl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpcl.dto.BottleInspection;
import com.bpcl.mapper.BottleInspectionMapper;
import com.bpcl.util.BaseServiceSupport;
import com.bpcl.util.IMapper;

@Service
public class BottleInspectionService extends BaseServiceSupport<BottleInspection> {

	@Autowired
	private BottleInspectionMapper<BottleInspection> bottleInspectionMapper;

	@Override
	public IMapper<BottleInspection> getMapper() {
		return bottleInspectionMapper;
	}

	@Override
	public String getPK() {
		return "uuid";
	}

	/**
	 * This function used for get the BottleInspection list
	 * 
	 * @param map
	 * @return
	 */
	public List<BottleInspection> getList(Map<String, Object> map) {
		return bottleInspectionMapper.query(map);
	}

	/**
	 * 
	 * @param bottleInspections
	 * @return
	 */
	public int batchInsert(List<BottleInspection> bottleInspections) {
		return bottleInspectionMapper.batchInsert(bottleInspections);
	}

	/**
	 * 
	 * @param bottleCode
	 * @return
	 */
	public BottleInspection getByBottleCode(String bottleCode,int status) {
		Map<String, Object> shParam = new HashMap<>(1);
		shParam.put("bottleCode", bottleCode);
		shParam.put("status", status);

		return bottleInspectionMapper.getInfoByMap(shParam);

	}

	/**
	 * 
	 * @param bottleInspection
	 * @return
	 */
	public int insert(BottleInspection bottleInspection) {

		bottleInspection.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));

		return bottleInspectionMapper.insert(bottleInspection);

	}

	/**
	 * 
	 * @param bottleCode
	 * @param status
	 * @return
	 */
	public int updateByBottleCode(String bottleCode, int status) {
		return bottleInspectionMapper.updateByBottleCode(bottleCode, status);
	}

}
