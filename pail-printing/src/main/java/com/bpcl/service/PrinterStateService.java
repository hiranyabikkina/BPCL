package com.bpcl.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpcl.dto.PrinterState;
import com.bpcl.mapper.PrinterStateMapper;
import com.bpcl.util.BaseServiceSupport;
import com.bpcl.util.IMapper;

@Service
public class PrinterStateService extends BaseServiceSupport<PrinterState> {
	@Autowired
	private PrinterStateMapper<PrinterState> printerStateMapper;

	public PrinterStateService() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public IMapper<PrinterState> getMapper() {
		// TODO Auto-generated method stub
		return printerStateMapper;
	}

	@Override
	public String getPK() {
		// TODO Auto-generated method stub
		return "uuid";
	}

	public PrinterState getInfoByUuid(String uuid) {

		PrinterState printerState = printerStateMapper.getInfoByUuid(uuid);

		return printerState;

	}
	
	public int update(PrinterState printerState) {
		// TODO Auto-generated method stub
		
		int result = printerStateMapper.update(printerState);

		return result;
	}
	
	public PrinterState getInfoByMap(int uuid) {
		// TODO Auto-generated method stub
		Map<String,Object> map=new HashMap<>();
		
		map.put("uuid", uuid);
		return printerStateMapper.getInfoByMap(map);
		
	}

}