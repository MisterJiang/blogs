package com.modules.sys.service;

import com.github.pagehelper.PageInfo;
import com.modules.sys.mapper.Photo;

/**
 * Created by Administrator on 2018/3/9.
 */
public interface PhotoService {

    PageInfo<Photo> findPageList(Photo entity, Integer page,Integer limit);

    Integer insert(Photo entity);

    Photo get(Photo entity);

    Integer delete(Photo entity);

    Integer update(Photo entity);
}
