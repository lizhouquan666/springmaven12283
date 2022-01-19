package com.wanxi.controller;


import com.wanxi.entity.User;
import com.wanxi.service.UserService;
import com.wanxi.tool.CommonResult;
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
//    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;
    private Jedis jedis = new Jedis();

    private UserController(UserService service) {
        this.userService = service;
    }
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public CommonResult login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {


//        String checkCode = (String) session.getAttribute("verifyCodeValue");
        CommonResult commonResult = userService.login(user);
        Jedis jedis  = new Jedis("localhost");
        jedis.set("commonResult", String.valueOf(commonResult));
        if (commonResult.getMessage().equals("success")){
            request.getSession().setAttribute("loginName",user.getUsername());
//            session.setAttribute("loginName",user.getUsername());
            //设置session过期时间，单位s
//            session.setMaxInactiveInterval(60 * 10);

//            Cookie[] cookie = request.getCookies();
//            for (int i = 0; i < cookie.length; i++) {
//                if (cookie[i].getValue() != null) {
//                    logger.info((String) session.getAttribute("loginName"));
//                    logger.info("Cookie:" + cookie[i].getName() + "=" + cookie[i].getValue() + ",i=" + i);
//                    jedis.set(cookie[i].getName(), cookie[i].getValue());
//                    jedis.expire(cookie[i].getName(), 600);
//                }
//            }
//            int i = 10/0;
            commonResult.getMessage();
        }
        //验证码校验
//        if (!checkCode.equals("")) {
//            if (!checkCode.equalsIgnoreCase(user.getCode())) {
//                commonResult.setData("codeErr");
//            }
//        }else{
//            commonResult.setMessage("codeNull");
//        }
        return commonResult;
    }


    @RequestMapping("findAll")
    public CommonResult findAll(User user) {
        int count;
        CommonResult commonResult;
        //判断jedis中的count是否存在 减少sql查询
        if (jedis.get("count")==null){
            count = userService.getCount(user).getCount();
            jedis.set("count",String.valueOf(count),"XX","EX",600);
        }else {
            count=Integer.valueOf(jedis.get("count"));
        }
        //分页
//        PageHelper.startPage(user.getPage(),user.getLimit());
//        commonResult = userService.findAll(user);
//        commonResult.setCount(count);
        return userService.findAll(user);
    }


    @RequestMapping("enable")
    public CommonResult enable(User user) {
        CommonResult commonResult = userService.enable(user);
        return commonResult;
    }
    @RequestMapping("findById")
    public CommonResult findById(User user){
        CommonResult commonResult = userService.findById(user);
        return commonResult;
    }
    @RequestMapping("delete")
    public CommonResult delete(User user){
        CommonResult commonResult = userService.del(user);
        return  commonResult;
    }
    @RequestMapping("add")
    public CommonResult add(User user){
        int count;
        count = userService.getCount(user).getCount();
        jedis.set("count",String.valueOf(count));
        jedis.expire("count",600);
        CommonResult commonResult = userService.add(user);
        return  commonResult;
    }
    @RequestMapping("update")
    public CommonResult edit(User user){
        CommonResult commonResult = userService.update(user);
        return commonResult;
    }
}
