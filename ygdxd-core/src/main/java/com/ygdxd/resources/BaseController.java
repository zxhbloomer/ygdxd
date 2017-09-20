package com.ygdxd.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Created by ygdxd_admin at 2017-09-15 下午8:27
 */
public abstract class BaseController {

    private final Logger _log = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler
    public String exceptionHandle(HttpServletRequest request, HttpServletResponse response, Exception e){
        _log.error("统一异常处理", e);
        request.setAttribute("exception", e);
        if (null != request.getHeader("X-Requested-With") && request.getHeader("X-Requested-With").equalsIgnoreCase
                ("XMLHttpRequest")) {
            request.setAttribute("requestHeader", "ajax");
        }
        return "/error.html";
    }
}
