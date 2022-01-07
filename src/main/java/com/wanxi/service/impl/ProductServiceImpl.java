package com.wanxi.service.impl;

import com.wanxi.entity.ProductModel;
import com.wanxi.mapper.ProductDao;
import com.wanxi.result.ResultModel;
import com.wanxi.service.ProductService;
import com.wanxi.tool.Date;
import org.springframework.stereotype.Service;
import java.util.List;


@Service ("ProductService")
public class ProductServiceImpl implements ProductService {
    private final ProductDao dao;

    public ProductServiceImpl(ProductDao dao) {
        this.dao = dao;
    }

    @Override
    public ResultModel findAll(ProductModel productModel) {
        List<ProductModel> productModels = dao.findAll(productModel);
        return ResultModel.getModel(productModels);
    }

    @Override
    public ResultModel del(ProductModel model) {
        int count = dao.del(model);
        return ResultModel.getModel(count);
    }

    @Override
    public ResultModel add(ProductModel model) {
        int count = dao.add(model);
        return ResultModel.getModel(count);
    }

    @Override
    public ResultModel findById(ProductModel productModel) {
        ProductModel model = dao.findById(productModel);
        return ResultModel.getModel(model);
    }

    @Override
    public ResultModel update(ProductModel model) {
        Date date = new Date();
        int count = dao.update(model);
        model.setUpdateTime(String.valueOf(date));
        return ResultModel.getModel(count);
    }

    @Override
    public ResultModel getCount(ProductModel model) {
        int count = dao.getCount(model);
        return ResultModel.getModel(count);
    }

    @Override
    public ResultModel enable(ProductModel productModel) {
        int i = dao.enable (productModel);
        return ResultModel.getModel (i);
    }

    @Override
    public ResultModel addText(ProductModel productModel) {
        return ResultModel.getModel (dao.addText (productModel));
    }
}
