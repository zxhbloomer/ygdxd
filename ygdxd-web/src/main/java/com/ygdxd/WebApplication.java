package com.ygdxd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author ygdxd_admin
 * @create 2017-09-13 下午1:06
 */
@SpringBootApplication
public class WebApplication extends SpringBootServletInitializer{
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WebApplication.class);
    }
}
