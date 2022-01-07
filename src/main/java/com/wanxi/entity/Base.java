package com.wanxi.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode (callSuper = true)
@Data
public class Base extends Page {
    private Integer id;
    private Integer enable;
    private String createTime;
    private String updateTime;
    private String remark;
    private String infName;
    private String infValue;
    private String infType;
    private String infLink;
    private String infBelongTo;
    private Integer isShow;
    private String img;
    private String code;

}
