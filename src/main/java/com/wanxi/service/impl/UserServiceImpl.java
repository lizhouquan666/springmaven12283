package com.wanxi.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wanxi.entity.TeamModel;
import com.wanxi.entity.User;
import com.wanxi.mapper.UserDao;
import com.wanxi.result.ResultModel;
import com.wanxi.service.UserService;
import com.wanxi.tool.CommonResult;
import com.wanxi.tool.Date;
import org.springframework.stereotype.Service;

import java.util.List;

@Service ("UserService")
public class UserServiceImpl implements UserService {
    private final UserDao dao;

    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public CommonResult login (User user) {
        String msg;
        User i= dao.findByUsernameAndPassword (user);
        if (i==null){
            msg="error";
        }else{
            msg="success";
        }
        return CommonResult.success(msg);
    }

    @Override
    public CommonResult findAll (User user) {
        //分页
        Page page= PageHelper.startPage(user.getPage(), user.getLimit());
        List<User> users = dao.findAll(user);
        PageInfo info =  new PageInfo<>(page.getResult());
        return CommonResult.success(users, Math.toIntExact(info.getTotal()));
//        List<User> users= dao.findAll(user);
//        return CommonResult.success(users);
    }

    @Override
    public CommonResult del (User user) {
        int count = dao.del (user);
        if (count!=1){
            return CommonResult.failed();
        }
        return CommonResult.success();
    }

    @Override
    public CommonResult add (User user) {
        int count = dao.add (user);
        if (count!=1){
            return CommonResult.failed();
        }
        return CommonResult.success();
    }

    @Override
    public CommonResult findById (User user) {
        User model = dao.findById (user);
        return CommonResult.success(model);
    }

    @Override
    public CommonResult update (User user) {
        Date date = new Date();
        int count = dao.update (user);
        if (count!=1){
            return CommonResult.failed();
        }
        user.setUpdateTime(String.valueOf(date));
        return CommonResult.success (count);
    }

    @Override
    public CommonResult getCount (User user) {
        int count = dao.getCount (user);
        if (count!=1){
            return CommonResult.failed();
        }
        return CommonResult.success (count);
    }

    @Override
    public CommonResult enable (User user) {
        int i = dao.enable (user);
        return CommonResult.success (i);
    }

    @Override
    public CommonResult addText(User user) {
        return CommonResult.success (dao.addText (user));
    }
}
