package com.ygdxd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ygdxd_admin
 * @create 2017-09-13 下午10:47
 */
@Controller
public class TestController {

    @RequestMapping("/")
    public String index(ModelMap map){
        System.out.println("");

        map.addAttribute("host","baidu.com");
        return "index";
    }
}
