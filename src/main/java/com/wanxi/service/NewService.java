package com.wanxi.service;


import com.wanxi.entity.NewModel;
import com.wanxi.tool.CommonResult;

public interface NewService extends BaseService<NewModel>{


    CommonResult addText(NewModel newModel);

    CommonResult findNewId(NewModel newModel);
}
