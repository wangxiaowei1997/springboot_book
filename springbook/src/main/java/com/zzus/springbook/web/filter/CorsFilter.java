package com.zzus.springbook.web.filter;


import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wang wei
 * 2020/5/11 15:52
 */
@WebFilter(filterName = "CorsFilter",
        urlPatterns = {
                "/**",
        })
@Slf4j
public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        response.setHeader("Access-Control-Allow-Origin","*");
        //如果是预检请求
        if(request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpStatus.SC_OK); //200
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, OPTIONS, DELETE");//当判定为预检请求后，设定允许请求的方法
            response.setHeader("Access-Control-Allow-Headers", "Content-Type, x-requested-with, Token"); //当判定为预检请求后，设定允许请求的头部类型
            response.addHeader("Access-Control-Max-Age", "100");  // 预检有效保持时间
            return;
        }
        log.info("CorsFilter执行了");
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
