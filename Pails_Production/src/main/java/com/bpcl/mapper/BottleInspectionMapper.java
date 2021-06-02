package com.bpcl.mapper;

import java.util.List;

import com.bpcl.dto.BottleInspection;
import com.bpcl.util.IMapper;

/**
 * 
 * @author Pradeep
 *
 * @param <T>
 */
public interface BottleInspectionMapper<T extends BottleInspection> extends IMapper<T> {

	int batchInsert(List<BottleInspection> bottleInspections);

	int updateByBottleCode(String bottleCode, int status);

}
