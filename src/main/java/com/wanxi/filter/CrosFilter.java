package com.wanxi.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author LiJinHai
 * @Date 2021/12/27
 */
public class CrosFilter implements Filter {

    private final int time = 20*24*60*60;

    /**

     * @see Filter#init(FilterConfig)

     */

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        // TODO Auto-generated method stub

    }
    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)

     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        // 添加参数，允许任意domain访问

        resp.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));

        // 这个allow-headers要配为*，这样才能允许所有的请求头

//        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With" +
                ", If-Modified-Since, Pragma, Last-Modified, Cache-Control" +
                ", Expires, Content-Type, X-E4M-With,userId,token");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("XDomainRequestAllowed","1");
        resp.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");

        resp.setHeader("Access-Control-Max-Age", time+"");

        chain.doFilter(request, resp);

    }


    /**

     * @see Filter#destroy()

     */

    @Override

    public void destroy() {

        // TODO Auto-generated method stub
    }


}