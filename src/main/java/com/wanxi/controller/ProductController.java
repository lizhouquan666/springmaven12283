package com.wanxi.controller;


import com.github.pagehelper.PageHelper;
import com.wanxi.entity.ProductModel;
import com.wanxi.result.ResultModel;
import com.wanxi.service.ProductService;
import com.wanxi.tool.CommonResult;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping("product")
public class ProductController {
    private ProductService productService;
    private Jedis jedis = new Jedis();

    private ProductController(ProductService service) {
        this.productService = service;
    }

    @RequestMapping("findAll")
    public CommonResult findAll(ProductModel product) {
        int count;
        CommonResult commonResult;
        //判断jedis中的count是否存在 减少sql查询
        if (jedis.get("count") == null) {
            count = productService.getCount(product).getCount();
            jedis.set("count", String.valueOf(count), "XX", "EX", 600);
        } else {
            count = Integer.valueOf(jedis.get("count"));
        }
        //分页
//        PageHelper.startPage(product.getPage(), product.getLimit());
//        commonResult = productService.findAll(product);
//        commonResult.setCount(count);
//        return commonResult;
        return productService.findAll(product);
    }


    @RequestMapping("enable")
    public CommonResult enable(ProductModel product) {
        CommonResult commonResult = productService.enable(product);
        return commonResult;
    }

    @RequestMapping("findById")
    public CommonResult findById(ProductModel product) {
        CommonResult commonResult = productService.findById(product);
        return commonResult;
    }

    @RequestMapping("delete")
    public CommonResult delete(ProductModel product) {
        CommonResult commonResult = productService.del(product);
        return commonResult;
    }

    @RequestMapping("add")
    public CommonResult add(ProductModel product) {
        int count;
        count = productService.getCount(product).getCount();
        jedis.set("count", String.valueOf(count));
        jedis.expire("count", 600);
        CommonResult commonResult = productService.add(product);
        return commonResult;
    }

    @RequestMapping("update")
    public CommonResult edit(ProductModel product) {
        CommonResult commonResult = productService.update(product);
        return commonResult;
    }

    @RequestMapping("findServiceType")
    public CommonResult findServiceType(ProductModel product) {
        CommonResult commonResult = productService.findServiceType(product);
        return commonResult;
    }
}
