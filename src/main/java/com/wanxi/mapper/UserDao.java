package com.wanxi.mapper;


import com.wanxi.entity.User;

public interface UserDao extends BaseDao<User> {
     User findByUsernameAndPassword(User user);
     int addText(User user);
}
