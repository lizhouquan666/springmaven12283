package com.wanxi.mapper;


import com.wanxi.entity.ProductModel;

public interface ProductDao extends BaseDao<ProductModel> {
     int addText(ProductModel productModel);
}
