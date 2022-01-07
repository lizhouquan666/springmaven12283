package com.wanxi.mapper;


import java.util.List;

public interface BaseDao<T> {
    List<T> findAll(T t);

    int getCount(T t);

    int add(T t);

    int update(T t);

    int del(T t);

    T findById(T t);
    int enable(T t);
}
