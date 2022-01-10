package com.wanxi.controller;


import com.github.pagehelper.PageHelper;
import com.wanxi.entity.NewModel;
import com.wanxi.result.ResultModel;
import com.wanxi.service.NewService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

//String[] allowedHeaders:
//        跨域请求中允许的请求头中的字段类型，
//        该值对应跨域预请求 Response 头中的 'Access-Control-Allow-Headers' 字段值。
//        不设置确切值默认支持所有的header字段
//        （Cache-Controller、Content-Language、Content-Type、Expires、Last-Modified、Pragma）跨域访问。
//String allowCredentials:
//        该值对应的是是跨域请求 Response 头中的 'Access-Control-Allow-Credentials' 字段值。
//        浏览器是否将本域名下的 cookie 信息携带至跨域服务器中。
//        默认携带至跨域服务器中，但要实现 cookie 共享还需要前端在 AJAX 请求中打开 withCredentials 属性。
//https://zhuanlan.zhihu.com/p/66789473
@CrossOrigin(allowCredentials="true", allowedHeaders="*")
@RestController
@RequestMapping("news")
public class NewController {
    private NewService newService;
    private Jedis jedis = new Jedis();

    private NewController(NewService service) {
        this.newService = service;
    }

    @RequestMapping("findAll")
    public ResultModel findAll(NewModel model) {
        int count;
        ResultModel resultModel;
        //判断jedis中的count是否存在 减少sql查询
        if (jedis.get("count") == null) {
            count = newService.getCount(model).getCount();
            jedis.set("count", String.valueOf(count), "XX", "EX", 600);
        } else {
            count = Integer.valueOf(jedis.get("count"));
        }
        //分页
        PageHelper.startPage(model.getPage(), model.getLimit());
        resultModel = newService.findAll(model);
        resultModel.setCount(count);
        return resultModel;
    }


    @RequestMapping("enable")
    public ResultModel enable(NewModel model) {
        ResultModel resultModel = newService.enable(model);
        return resultModel;
    }

    @RequestMapping("findById")
    public ResultModel findById(NewModel model) {
        ResultModel resultModel = newService.findById(model);
        return resultModel;
    }

    @RequestMapping("delete")
    public ResultModel delete(NewModel model) {
        ResultModel resultModel = newService.del(model);
        return resultModel;
    }

    @RequestMapping("add")
    public ResultModel add(NewModel model) {
        int count;
        count = newService.getCount(model).getCount();
        jedis.set("count", String.valueOf(count));
        jedis.expire("count", 600);
        ResultModel resultModel = newService.add(model);
        return resultModel;
    }

    @RequestMapping("update")
    public ResultModel edit(NewModel model) {
        ResultModel resultModel = newService.update(model);
        return resultModel;
    }

    @RequestMapping("findNewId")
    public ResultModel findNewId(NewModel model) {
        ResultModel resultModel = newService.findNewId(model);
        return resultModel;
    }
}
