package com.bpcl.util;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface IBase<T> {
    int insert(T var1);

    int update(T var1);

    int delete(T var1);

    int count(Map var1);

    T getInfoByMap(Map var1);

    T getInfoByUuid(String var1);

    List<T> query(Map var1);

    PageList<T> queryPage(Map var1, PageBounds var2);
}
