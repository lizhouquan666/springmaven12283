package com.wanxi.controller;


import com.github.pagehelper.PageHelper;
import com.wanxi.entity.TeamModel;
import com.wanxi.service.TeamService;
import com.wanxi.tool.CommonResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping("team")
public class TeamController {
    private TeamService teamService;
    private Jedis jedis = new Jedis();

    private TeamController(TeamService service) {
        this.teamService = service;
    }

    @RequestMapping("findAll")
    public CommonResult findAll(TeamModel team) {
        int count;
        CommonResult resultModel;
        //判断jedis中的count是否存在 减少sql查询
        if (jedis.get("count") == null) {
            count = teamService.getCount(team).getCount();
            jedis.set("count", String.valueOf(count), "XX", "EX", 600);
        } else {
            count = Integer.valueOf(jedis.get("count"));
        }

        return teamService.findAll(team);
    }


    @RequestMapping("enable")
    public CommonResult enable(TeamModel team) {
        CommonResult commonResult = teamService.enable(team);
        return commonResult;
    }

    @RequestMapping("findById")
    public CommonResult findById(TeamModel team) {
        CommonResult commonResult = teamService.findById(team);
        return commonResult;
    }

    @RequestMapping("delete")
    public CommonResult delete(TeamModel team) {
        CommonResult commonResult = teamService.del(team);
        return commonResult;
    }

    @RequestMapping("add")
    public CommonResult add(TeamModel team) {
        int count;
        count = teamService.getCount(team).getCount();
        jedis.set("count", String.valueOf(count));
        jedis.expire("count", 600);
        CommonResult commonResult = teamService.add(team);
        return commonResult;
    }

    @RequestMapping("update")
    public CommonResult edit(TeamModel team) {
        CommonResult commonResult = teamService.update(team);
        return commonResult;
    }

    @RequestMapping(value = "findTeamId",method = RequestMethod.GET )
    public CommonResult findServiceType(TeamModel team) {
        CommonResult commonResult = teamService.findTeamId(team);
        return commonResult;
    }
}
