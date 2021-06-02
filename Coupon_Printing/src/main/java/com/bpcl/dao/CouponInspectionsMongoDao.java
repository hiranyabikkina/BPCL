package com.bpcl.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.bpcl.dto.CouponInspectionsMongo;

@Repository
public class CouponInspectionsMongoDao {
	@Autowired
	private MongoTemplate mongoTemplate;

	public void insert(CouponInspectionsMongo couponInspectionsMongo) {
		// TODO Auto-generated method stub
		//System.out.println(">>>>>>>>>>>>>>>   MONGO DB INSERT IN Repository CLASS INSERT METHODE");
		mongoTemplate.save(couponInspectionsMongo, "couponInspections");
	}

	public List<CouponInspectionsMongo> getByStatus(Integer status,String couponSize) {

		Query query = new Query();
		query.addCriteria(Criteria.where("status").is(status));
		query.addCriteria(Criteria.where("couponSize").is(couponSize));
		List<CouponInspectionsMongo> list = mongoTemplate.find(query, CouponInspectionsMongo.class,
				"couponInspections");
		return list;
	}

	public void update(CouponInspectionsMongo couponInspectionsMongo) {
		// TODO Auto-generated method stub
	
		Query query = new Query();
        query.addCriteria(Criteria.where("couponCode").is(couponInspectionsMongo.getCouponCode()));  

        

        Update update = new Update();
        update.set("lotCode", couponInspectionsMongo.getLotCode());
        update.set("status",couponInspectionsMongo.getStatus());
        mongoTemplate.updateFirst(query, update, CouponInspectionsMongo.class,"couponInspections");

		
	}

//	public int countCouponInspection() {
//		Query query = new Query();
//		query.addCriteria(Criteria.where("status").is(4));
//		return (int) mongoTemplate.count(query, "couponInspections");
//		
//	}

}
