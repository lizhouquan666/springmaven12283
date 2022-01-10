package com.wanxi.service;


import com.wanxi.entity.NewModel;
import com.wanxi.result.ResultModel;

public interface NewService extends BaseService<NewModel>{


    ResultModel addText(NewModel newModel);

    ResultModel findNewId(NewModel newModel);
}
