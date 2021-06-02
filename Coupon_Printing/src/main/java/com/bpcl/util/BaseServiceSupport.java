package com.bpcl.util;

import java.util.List;
import java.util.Map;


public abstract class BaseServiceSupport<T> {
	private IMapper<T> mapper;

	public BaseServiceSupport() {
	}

	public abstract IMapper<T> getMapper();

	public abstract String getPK();

	public List<T> list(Map<String, Object> paramMap) {
		return this.getMapper().query(paramMap);
	}

	public int count(Map<String, Object> paramMap) {
		return this.getMapper().count(paramMap);
	}

	public void resetMapper(IMapper<T> mapper) {
		this.mapper = mapper;
	}

	public T add(T srcEntity) {
		int count = this.getMapper().insert(srcEntity);
		return count > 0 ? srcEntity : null;
	}

	public T edit(T srcEntity) {
		int count = this.getMapper().update(srcEntity);
		return count > 0 ? srcEntity : null;
	}

	public int del(T srcEntity) {
		return this.getMapper().delete(srcEntity);
	}

	public T findByMap(Map<String, Object> paramMap) {
		return this.getMapper().getInfoByMap(paramMap);
	}

	public T findByUuid(String uuid) {
		return this.getMapper().getInfoByUuid(uuid);
	}

}