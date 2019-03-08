package cn.shuaijunlan.design.patterns.mvcdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Mr SJL on 2016/11/5.
 */

@Controller
@RequestMapping("/hello")
public class HelloMvcController
{
    @RequestMapping( value = "/mvc", method = RequestMethod.GET)
    public String helloMvc()
    {
        return "home";
    }
}
