package com.modules.sys.service;

import com.github.pagehelper.PageInfo;
import com.modules.sys.mapper.TimeLine;

import java.util.List;

/**
 * Created by Administrator on 2018/3/11.
 */
public interface TimeLineService {

    PageInfo<TimeLine> findPageList(TimeLine entity, Integer page,Integer limit);

    List<TimeLine> findAllList(TimeLine entity);

    Integer insert(TimeLine entity);

    TimeLine get(TimeLine entity);

    Integer delete(TimeLine entity);

    Integer update(TimeLine entity);

}
