package com.wanxi.service;


import com.wanxi.result.ResultModel;
import com.wanxi.tool.CommonResult;

public interface BaseService<T> {
    CommonResult findAll(T t);

    CommonResult del(T t);

    CommonResult add(T t);

    CommonResult findById(T t);

    CommonResult update(T t);

    CommonResult getCount(T t);
    CommonResult enable(T t);
}
