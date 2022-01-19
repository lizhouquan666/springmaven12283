package com.wanxi.service;


import com.wanxi.entity.ProductModel;
import com.wanxi.entity.User;
import com.wanxi.result.ResultModel;
import com.wanxi.tool.CommonResult;

public interface ProductService extends BaseService<ProductModel>{


    CommonResult addText(ProductModel productModel);

    CommonResult findServiceType(ProductModel product);
}
