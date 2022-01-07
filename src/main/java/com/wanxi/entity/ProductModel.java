package com.wanxi.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductModel extends Base {
    private BigDecimal marketPrivce;
    private BigDecimal normalPrice;
    private String name;
    private String content;
    private String imgHref;
    private Integer isTop;
    private Integer isHot;
    private Integer isRecommend;
    private Integer cuisineId;
    private String cuisineName;

}
