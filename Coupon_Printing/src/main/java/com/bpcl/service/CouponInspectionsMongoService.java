package com.bpcl.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bpcl.dao.CouponInspectionsMongoDao;
import com.bpcl.dto.CouponInspectionsMongo;
import com.bpcl.util.DateUtil;

@Service
public class CouponInspectionsMongoService {

	@Autowired
	private CouponInspectionsMongoDao couponInspectionsMongoDao;

	public void insert(String couponCode, String lotCode, String couponSize) {
		// TODO Auto-generated method stub
		CouponInspectionsMongo couponInspectionsMongo = new CouponInspectionsMongo();
		couponInspectionsMongo.setCouponCode(couponCode);
		couponInspectionsMongo.setCouponSize(couponSize);
		couponInspectionsMongo.setStatus(3);
		couponInspectionsMongo.setCreateTime(DateUtil.getSystemTime());
		couponInspectionsMongo.setUpdatedTime(DateUtil.getSystemTime());
		couponInspectionsMongo.setLotCode(lotCode);
		couponInspectionsMongoDao.insert(couponInspectionsMongo);
	}

	public List<CouponInspectionsMongo> getbyStatus(Integer status, String couponSize) {
		// TODO Auto-generated method stub

		return couponInspectionsMongoDao.getByStatus(status, couponSize);
	}

	public void update(CouponInspectionsMongo couponInspectionsMongo) {

		couponInspectionsMongoDao.update(couponInspectionsMongo);
	}

}
