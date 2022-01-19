package com.wanxi.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wanxi.entity.ProductModel;
import com.wanxi.entity.User;
import com.wanxi.mapper.ProductDao;
import com.wanxi.result.ResultModel;
import com.wanxi.service.ProductService;
import com.wanxi.tool.CommonResult;
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
    public CommonResult findAll(ProductModel productModel) {
        //分页
        Page page= PageHelper.startPage(productModel.getPage(), productModel.getLimit());
        List<ProductModel> productModels = dao.findAll(productModel);
        PageInfo info =  new PageInfo<>(page.getResult());
        return CommonResult.success(productModels, Math.toIntExact(info.getTotal()));
//        List<ProductModel> productModels = dao.findAll(productModel);
//        return CommonResult.success(productModels);
    }

    @Override
    public CommonResult del(ProductModel model) {
        int count = dao.del(model);
        return CommonResult.success(count);
    }

    @Override
    public CommonResult add(ProductModel model) {
        int count = dao.add(model);
        return CommonResult.success(count);
    }

    @Override
    public CommonResult findById(ProductModel productModel) {
        ProductModel model = dao.findById(productModel);
        return CommonResult.success(model);
    }

    @Override
    public CommonResult update(ProductModel model) {
        Date date = new Date();
        int count = dao.update(model);
        model.setUpdateTime(String.valueOf(date));
        return CommonResult.success(count);
    }

    @Override
    public CommonResult getCount(ProductModel model) {
        int count = dao.getCount(model);
        return CommonResult.success(count);
    }

    @Override
    public CommonResult enable(ProductModel productModel) {
        int i = dao.enable (productModel);
        return CommonResult.success (i);
    }

    @Override
    public CommonResult addText(ProductModel productModel) {
        return CommonResult.success (dao.addText (productModel));
    }

    @Override
    public CommonResult findServiceType(ProductModel productModel) {
        List<ProductModel> productModels = dao.findServiceType(productModel);
        return CommonResult.success(productModels);
    }
}
