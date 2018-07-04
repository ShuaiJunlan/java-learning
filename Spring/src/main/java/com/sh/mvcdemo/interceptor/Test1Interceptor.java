package com.sh.mvcdemo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Mr SJL on 2016/11/15.
 */
public class Test1Interceptor implements HandlerInterceptor
{
    /**
     *
     * @param request
     * @param response
     * @param handler   表示的是被拦截的请求的目标对象
     * @return  表示我们是否需要将当前的请求拦截下来
     *              如果返回true，请求将会继续进行
     *              如果返回false，请求将会被拦截
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        System.out.println("执行到了preHandle()方法");
        return true;
    }

    /**
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView  可以改变参数来改变显示的视图，或修改发往视图的方法
     * @throws Exception
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {
        System.out.println("postHandle()方法");

    }


    /**
     * 类似于析构方法，销毁一些对象或流之类的
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {
        System.out.println("afterCompletion()方法");

    }
}
