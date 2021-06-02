package com.bpcl.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpcl.dto.LineInformation;
import com.bpcl.mapper.LineInformationMapper;
import com.bpcl.util.BaseServiceSupport;
import com.bpcl.util.DateUtil;
import com.bpcl.util.IMapper;

@Service
public class LineInformationService extends BaseServiceSupport<LineInformation> {

	@Autowired
	private LineInformationMapper<LineInformation> lineInformationMapper;

	@Override
	public IMapper<LineInformation> getMapper() {
		return lineInformationMapper;
	}

	@Override
	public String getPK() {
		return "uuid";
	}

	/**
	 * 
	 * @param lineInformation
	 * @return
	 */
	public int insert(LineInformation lineInformation) {

		lineInformation.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
		lineInformation.setCreateTime(DateUtil.getSystemTime());
		return lineInformationMapper.insert(lineInformation);
	}

	public LineInformation getByMacId(String macId) {

		Map<String, Object> shParam = new HashMap<>();
		shParam.put("macId", macId);

		return lineInformationMapper.getInfoByMap(shParam);
	}

	public int update(LineInformation lineInformation) {
		
		return lineInformationMapper.update(lineInformation);
	}

}
