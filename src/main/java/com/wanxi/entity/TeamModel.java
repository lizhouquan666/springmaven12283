package com.wanxi.entity;

import lombok.Data;


@Data
public class TeamModel extends Base {

    private String name;
    private String content;
    private String imgHref;

    private String team_Id;
    private String teamTypeName;
    private String start;
    private String end;
    private String is_show;
    private String teamType;



}