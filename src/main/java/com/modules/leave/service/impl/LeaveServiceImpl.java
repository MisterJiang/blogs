package com.modules.leave.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.modules.leave.dao.LeaveDao;
import com.modules.leave.mapper.Leave;
import com.modules.leave.service.LeaveService;
import com.utils.DateUtils;
import com.utils.UUID_Utils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/2/26.
 */
@Service
public class LeaveServiceImpl implements LeaveService{

    @Autowired
    private LeaveDao leaveDao;

    @Override
    public PageInfo<Leave> findPageList(Leave entity, Integer page, Integer limit) {
        PageHelper.startPage(page, limit, true);
        List<Leave> pageList = leaveDao.findPageList(entity);
        PageInfo pageInfo = new PageInfo(pageList);
        return pageInfo;
    }

    @Override
    public Integer insert(Leave entity) {
        entity.setId(UUID_Utils.getUUID());
        entity.setCreateUser((String) SecurityUtils.getSubject().getPrincipal());
        entity.setCreateTime(DateUtils.parseDate(DateUtils.getDateTime()));
        entity.setState("未提交");  //默认“未提交”
        return leaveDao.insert(entity);
    }

    @Override
    public Leave get(Leave entity) {
        return leaveDao.get(entity);
    }

    @Override
    public Integer delete(Leave entity) {
        return leaveDao.delete(entity);
    }

    @Override
    public Integer update(Leave entity) {
        return leaveDao.update(entity);
    }
}
