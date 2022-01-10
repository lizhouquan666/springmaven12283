package com.wanxi.service.impl;

import com.wanxi.entity.NewModel;
import com.wanxi.mapper.NewDao;
import com.wanxi.result.ResultModel;
import com.wanxi.service.NewService;
import com.wanxi.tool.Date;
import org.springframework.stereotype.Service;

import java.util.List;


@Service ("NewService")
public class NewServiceImpl implements NewService {
    private final NewDao dao;

    public NewServiceImpl(NewDao dao) {
        this.dao = dao;
    }

    @Override
    public ResultModel findAll(NewModel newModel) {
        List<NewModel> newModels = dao.findAll(newModel);
        return ResultModel.getModel(newModels);
    }

    @Override
    public ResultModel del(NewModel model) {
        int count = dao.del(model);
        return ResultModel.getModel(count);
    }

    @Override
    public ResultModel add(NewModel model) {
        int count = dao.add(model);
        return ResultModel.getModel(count);
    }

    @Override
    public ResultModel findById(NewModel newModel) {
        NewModel model = dao.findById(newModel);
        return ResultModel.getModel(model);
    }

    @Override
    public ResultModel update(NewModel model) {
        Date date = new Date();
        int count = dao.update(model);
        model.setUpdateTime(String.valueOf(date));
        return ResultModel.getModel(count);
    }

    @Override
    public ResultModel getCount(NewModel model) {
        int count = dao.getCount(model);
        return ResultModel.getModel(count);
    }

    @Override
    public ResultModel enable(NewModel newModel) {
        int i = dao.enable (newModel);
        return ResultModel.getModel (i);
    }

    @Override
    public ResultModel addText(NewModel newModel) {
        return ResultModel.getModel (dao.addText (newModel));
    }

    @Override
    public ResultModel findNewId(NewModel newModel) {
        List<NewModel> newModels = dao.findNewId(newModel);
        return ResultModel.getModel(newModels);
    }
}
