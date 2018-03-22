package com.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.modules.blogs.mapper.Article;
import com.modules.sys.dao.PhotoDao;
import com.modules.sys.dao.UserDao;
import com.modules.sys.mapper.LoginLog;
import com.modules.sys.mapper.Photo;
import com.modules.sys.service.PhotoService;
import com.utils.DateUtils;
import com.utils.UUID_Utils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9.
 */
@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private UserDao userDao;


    @Autowired
    private PhotoDao photoDao;


    @Override
    public PageInfo<Photo> findPageList(Photo entity, Integer page, Integer limit) {
        PageHelper.startPage(page, limit, true);
        List<Photo> pageList = photoDao.findPageList(entity);
        PageInfo pageInfo = new PageInfo(pageList);
        return pageInfo;
    }

    @Override
    public Integer insert(Photo entity) {
        entity.setId(UUID_Utils.getUUID());
        entity.setCreateUser((String) SecurityUtils.getSubject().getPrincipal());
        entity.setCreateTime(DateUtils.parseDate(DateUtils.getDateTime()));
        return photoDao.insert(entity);
    }

    @Override
    public Photo get(Photo entity) {
        return photoDao.get(entity);
    }

    @Override
    public Integer delete(Photo entity) {
        return photoDao.delete(entity);
    }

    @Override
    public Integer update(Photo entity) {
        return photoDao.update(entity);
    }
}
