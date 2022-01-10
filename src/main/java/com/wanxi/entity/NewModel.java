package com.wanxi.entity;

import lombok.Data;


@Data
public class NewModel extends Base {
    private String news_id;
    private String name;
    private String content;
    private String newsTypeName;
    private String start;
    private String end;
    private  String imgHref;
    private String is_show;
    private  String update_time;

}
