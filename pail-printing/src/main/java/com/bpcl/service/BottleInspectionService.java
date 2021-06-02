package com.bpcl.service;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bpcl.bean.PailBean;
import com.bpcl.dto.BottleInspection;
import com.bpcl.dto.LineInformation;
import com.bpcl.mapper.BottleInspectionMapper;
import com.bpcl.util.BaseServiceSupport;
import com.bpcl.util.DateUtil;
import com.bpcl.util.IMapper;
import com.bpcl.util.MacId;
import com.bpcl.util.PailPrintingThread;

@Service
public class BottleInspectionService extends BaseServiceSupport<BottleInspection> {
	@Autowired
	private BottleInspectionMapper<BottleInspection> bottleInspectionMapper;
	@Autowired
	private LineInformationService lineInformationService;	
	@Autowired
	private ProductionPlanService productionPlanService;
	@Autowired
	private PrinterStateService printerStateService;
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	@Override
	public IMapper<BottleInspection> getMapper() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPK() {
		// TODO Auto-generated method stub
		return null;
	}

	public int insert(BottleInspection bottleInspection) {
		bottleInspection.setUuid(UUID.randomUUID().toString().replace("-", ""));
		bottleInspection.setCreatedTime(DateUtil.getSystemTime());
		int result = bottleInspectionMapper.insert(bottleInspection);

		return result;

	}

	public List<BottleInspection> getAll() { // TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();

		List<BottleInspection> list = bottleInspectionMapper.query(map);

		return list;
	}

	public BottleInspection getInfoByUuid(String uuid) {

		BottleInspection bottleInspection = bottleInspectionMapper.getInfoByUuid(uuid);

		return bottleInspection;

	}

	public int update(BottleInspection pail) {

		int result = bottleInspectionMapper.update(pail);

		return result;
	}

	public String callingThreadMethode(PailBean pailBean) throws SocketException {

		if (null != pailBean.getTotalQty() && 0 != pailBean.getTotalQty()) {
			String pailBarCodeWithString = pailQuantityWiseCreateAndInsert(pailBean);
			if (null != pailBarCodeWithString) {

				LineInformation lineInformation = lineInformationService.orderByCreateTime(MacId.macId());

				if (null != lineInformation) {
					PailPrintingThread pailPrintingThread = new PailPrintingThread(pailBean.getTotalQty(),
							lineInformation, printerStateService,productionPlanService, pailBarCodeWithString);
					pailPrintingThread.start();

					return "success";
				} else {
					return "Line Information Missing";
				}
			}
			return "Refresh Page After Run";
		}
		return "Pail Printing Qty Missing";
	}

	public String pailQuantityWiseCreateAndInsert(PailBean pailBean) {

		System.out.println(pailBean.getTotalQty());
		String convertCouponBarcodeString = null;
		List<String> pailBarCodeList = new ArrayList<String>();

		int loop = 1;
		while (loop <= pailBean.getTotalQty()) {
			System.out.println(loop);
			BottleInspection bottleInspection = new BottleInspection();
			BeanUtils.copyProperties(pailBean, bottleInspection);

			bottleInspection.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
			bottleInspection.setBottleCode("W" + DateUtil.yymmdd() + "P" + randomAlphaNumeric(8));
			bottleInspection.setPlantLocation(pailBean.getPlantLocation());
			bottleInspection.setLineUuid(pailBean.getLineUuid());
			bottleInspection.setCreator("local");
			bottleInspection.setCreatedTime(DateUtil.getSystemTime());
			bottleInspection.setStatus(1);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("bottleCode", bottleInspection.getBottleCode());
			BottleInspection pail1 = bottleInspectionMapper.getInfoByMap(map);
			if (pail1 == null) {
				bottleInspectionMapper.insert(bottleInspection);

				pailBarCodeList.add(bottleInspection.getBottleCode());
				loop++;
			}

		}
		if (pailBarCodeList.size() > 0) {
			System.out.println(pailBarCodeList.size() + "  size  ");
			convertCouponBarcodeString = pailBarCodeList.stream().map(e -> e + "|").reduce("|", String::concat);
		}

		return convertCouponBarcodeString;

	}

	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}

		return builder.toString();
	}

}
