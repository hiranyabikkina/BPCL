package com.bpcl.service;

import java.util.HashMap;
import java.util.List;
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

	public int insert(LineInformation lineInformation) {
		lineInformation.setUuid(UUID.randomUUID().toString().replace("-", ""));
		lineInformation.setCreateTime(DateUtil.getSystemTime());
		int result = lineInformationMapper.insert(lineInformation);

		return result;

	}

	public List<LineInformation> getAll() { // TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();

		List<LineInformation> list = lineInformationMapper.query(map);

		return list;
	}

	public LineInformation getInfoByUuid(String uuid) {

		LineInformation lineInformation = lineInformationMapper.getInfoByUuid(uuid);

		return lineInformation;

	}

	public int update(LineInformation lineInformation) {
		// TODO Auto-generated method stub

		int result = lineInformationMapper.update(lineInformation);

		return result;
	}

	public LineInformation orderByCreateTime() {
		// TODO Auto-generated method stub

		LineInformation lineInformation = lineInformationMapper.orderByCreateTime();
		return lineInformation;
	}

}
