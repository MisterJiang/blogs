package com.modules.leave.service;

import com.github.pagehelper.PageInfo;
import com.modules.leave.mapper.Leave;

/**
 * Created by Administrator on 2018/2/26.
 */
public interface LeaveService {

    PageInfo<Leave> findPageList(Leave entity, Integer page,Integer limit);

    Integer insert(Leave entity);

    Leave get(Leave entity);

    Integer delete(Leave entity);

    Integer update(Leave entity);



}
