package com.ygdxd.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ygdxd_admin
 * @create 2017-09-13 下午10:47
 */
@RestController
@RequestMapping("ygdxd")
public class TestController {

    @RequestMapping("/Test")
    @ResponseBody
    public String index(){
        System.out.println("-------------");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "index";
    }
}
