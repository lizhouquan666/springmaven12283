package com.wanxi.service;


import com.wanxi.entity.User;
import com.wanxi.result.ResultModel;
import com.wanxi.tool.CommonResult;

public interface UserService extends BaseService<User>{

    CommonResult login(User user);
    CommonResult enable(User user);

    CommonResult addText(User user);
}
