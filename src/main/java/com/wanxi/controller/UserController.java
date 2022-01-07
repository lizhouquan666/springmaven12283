package com.wanxi.controller;


import com.wanxi.entity.User;
import com.wanxi.result.ResultModel;
import com.wanxi.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
@RequestMapping("user")
public class UserController {
    private UserService userService;
    private Jedis jedis = new Jedis();

    private UserController(UserService service) {
        this.userService = service;
    }
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResultModel login(@RequestBody User user, HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        String checkCode = (String) session.getAttribute("verifyCodeValue");
        ResultModel resultModel = userService.login(user);
        Jedis jedis  = new Jedis("localhost");
        jedis.set("resultModel", String.valueOf(resultModel));
        if (resultModel.getMsg().equals("success")){
            session.setAttribute("loginName",user.getUsername());
        }
        //验证码校验
        if (!checkCode.equals("")) {
            if (!checkCode.equalsIgnoreCase(user.getCode())) {
                resultModel.setData("codeErr");
            }
        }else{
            resultModel.setMsg("codeNull");
        }
        return resultModel;
    }


    @RequestMapping("findAll")
    public ResultModel findAll(User user) {
        int count;
        ResultModel resultModel;
        //判断jedis中的count是否存在 减少sql查询
        if (jedis.get("count")==null){
            count = userService.getCount(user).getCount();
            jedis.set("count",String.valueOf(count),"XX","EX",600);
        }else {
            count=Integer.valueOf(jedis.get("count"));
        }
        //分页
        PageHelper.startPage(user.getPage(),user.getLimit());
        resultModel = userService.findAll(user);
        resultModel.setCount(count);
        return resultModel;
    }


    @RequestMapping("enable")
    public ResultModel enable(User user) {
        ResultModel resultModel = userService.enable(user);
        return resultModel;
    }
    @RequestMapping("findById")
    public ResultModel findById(User user){
        ResultModel resultModel = userService.findById(user);
        return resultModel;
    }
    @RequestMapping("delete")
    public ResultModel delete(User user){
        ResultModel resultModel = userService.del(user);
        return  resultModel;
    }
    @RequestMapping("add")
    public ResultModel add(User user){
        int count;
        count = userService.getCount(user).getCount();
        jedis.set("count",String.valueOf(count));
        jedis.expire("count",600);
        ResultModel resultModel = userService.add(user);
        return  resultModel;
    }
    @RequestMapping("update")
    public ResultModel edit(User user){
        ResultModel resultModel = userService.update(user);
        return resultModel;
    }
}
