package com.wanxi.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wanxi.entity.NewModel;
import com.wanxi.entity.ProductModel;
import com.wanxi.mapper.NewDao;
import com.wanxi.result.ResultModel;
import com.wanxi.service.NewService;
import com.wanxi.tool.CommonResult;
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
    public CommonResult findAll(NewModel newModel) {
        //分页
        Page page= PageHelper.startPage(newModel.getPage(), newModel.getLimit());
        List<NewModel> newModels = dao.findAll(newModel);
        PageInfo info =  new PageInfo<>(page.getResult());
        return CommonResult.success(newModels, Math.toIntExact(info.getTotal()));
//        List<NewModel> newModels = dao.findAll(newModel);
//        return CommonResult.success(newModels);
    }

    @Override
    public CommonResult del(NewModel model) {
        int count = dao.del(model);
        return CommonResult.success(count);
    }

    @Override
    public CommonResult add(NewModel model) {
        int count = dao.add(model);
        return CommonResult.success(count);
    }

    @Override
    public CommonResult findById(NewModel newModel) {
        NewModel model = dao.findById(newModel);
        return CommonResult.success(model);
    }

    @Override
    public CommonResult update(NewModel model) {
        Date date = new Date();
        int count = dao.update(model);
        model.setUpdateTime(String.valueOf(date));
        return CommonResult.success(count);
    }

    @Override
    public CommonResult getCount(NewModel model) {
        int count = dao.getCount(model);
        return CommonResult.success(count);
    }

    @Override
    public CommonResult enable(NewModel newModel) {
        int i = dao.enable (newModel);
        return CommonResult.success (i);
    }

    @Override
    public CommonResult addText(NewModel newModel) {
        return CommonResult.success (dao.addText (newModel));
    }

    @Override
    public CommonResult findNewId(NewModel newModel) {
        List<NewModel> newModels = dao.findNewId(newModel);
        return CommonResult.success(newModels);
    }
}
