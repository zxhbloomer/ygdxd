package com.ygdxd.resources;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Created by ygdxd_admin at 2017-09-14 下午2:54
 */
public abstract class BaseErrorBuilder {

    private Integer code;

    private String message;

    private String request;

    public BaseErrorBuilder(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public BaseErrorBuilder setRequest(RequestMethod method, String url){
        this.request = String.format("%s %s",method.name(), url);
        return this;
    }

    public JSONObject build(){
        JSONObject result=new JSONObject();
        result.put("code", this.code);
        result.put("message", this.message);
        if (StringUtils.isNotBlank(request)) {
            result.put("request", request);
        }
        return result;
    }
}
