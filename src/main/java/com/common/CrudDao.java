package com.common;

import java.util.List;

/**
 * Created by Administrator on 2018/2/8.
 */
public interface CrudDao<T> extends BaseDao{


    List<T> findPageList(T entity);

    List<T> findAllList(T entity);

    T get(String var1);

    T get(T var1);

    int insert(T var1);

    int update(T var1);

    int delete(String var1);

    int delete(T var1);

}
