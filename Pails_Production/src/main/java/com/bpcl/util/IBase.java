package com.bpcl.util;

import java.util.List;
import java.util.Map;

public interface IBase<T> {

	int insert(T var1);

	int update(T var1);

	int delete(T var1);

	int count(Map<String, Object> var1);

	T getInfoByMap(Map<String, Object> var1);

	T getInfoByUuid(String var1);

	List<T> query(Map<String, Object> var1);

}