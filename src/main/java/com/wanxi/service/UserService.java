package com.wanxi.service;


import com.wanxi.entity.User;
import com.wanxi.result.ResultModel;

public interface UserService extends BaseService<User>{

    ResultModel login(User user);
    ResultModel enable(User user);
    ResultModel addText(User user);
}
