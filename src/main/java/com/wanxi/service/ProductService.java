package com.wanxi.service;


import com.wanxi.entity.ProductModel;
import com.wanxi.entity.User;
import com.wanxi.result.ResultModel;

public interface ProductService extends BaseService<ProductModel>{


    ResultModel addText(ProductModel productModel);
}
