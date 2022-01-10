package com.wanxi.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductModel extends Base {
    private BigDecimal marketPrivce;
    private Integer normalPrice;
    private Integer price;
    private String name;
    private String content;
    private String imgHref;
    private Integer isTop;
    private Integer isHot;
    private Integer isRecommend;
    private String service_id;
    private String serviceTypeName;
    private String start;
    private String end;
    private String is_show;
    private String serviceType;
    private String productName;
    private String img;

}
