package com.wanxi.result;

import lombok.Data;

@Data
public class ServiceChan {
    private String title;
    private String desp;
    private String channel;
    ServiceChan(String title,String desp, String channel){
        this.channel=channel;
        this.desp=desp;
        this.title=title;
    }
}
