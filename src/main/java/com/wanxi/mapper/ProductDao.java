package com.wanxi.mapper;


import com.wanxi.entity.ProductModel;

import java.util.List;

public interface ProductDao extends BaseDao<ProductModel> {
     int addText(ProductModel productModel);

    List<ProductModel> findServiceType(ProductModel product);
}
