package com.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.modules.sys.dao.TimeLineDao;
import com.modules.sys.dao.UserDao;
import com.modules.sys.mapper.TimeLine;
import com.modules.sys.service.TimeLineService;
import com.utils.DateUtils;
import com.utils.UUID_Utils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/3/11.
 */
@Service
public class TimeLineServiceImpl implements TimeLineService{
    @Autowired
    private UserDao userDao;

    @Autowired
    private TimeLineDao timeLineDao;

    @Override
    public PageInfo<TimeLine> findPageList(TimeLine entity, Integer page, Integer limit) {
        PageHelper.startPage(page, limit, true);
        List<TimeLine> pageList = timeLineDao.findPageList(entity);
        PageInfo pageInfo = new PageInfo(pageList);
        return pageInfo;
    }

    @Override
    public List<TimeLine> findAllList(TimeLine entity) {
       return timeLineDao.findAllList(entity);
    }

    @Override
    public Integer insert(TimeLine entity) {
        entity.setId(UUID_Utils.getUUID());
        entity.setCreateUser((String) SecurityUtils.getSubject().getPrincipal());
        entity.setCreateTime(DateUtils.parseDate(DateUtils.getDateTime()));
        return timeLineDao.insert(entity);
    }

    @Override
    public TimeLine get(TimeLine entity) {
        return timeLineDao.get(entity);
    }

    @Override
    public Integer delete(TimeLine entity) {
        return timeLineDao.delete(entity);
    }

    @Override
    public Integer update(TimeLine entity) {
        return timeLineDao.update(entity);
    }
}
