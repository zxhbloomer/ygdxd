package com.ygdxd.mybatis.plugins.adapter;

import org.mybatis.generator.api.PluginAdapter;

import java.util.List;

/**
 * @author ygdxd_admin
 * @create 2017-09-14 下午12:44
 */
public class MapperCommentPluginAdapter extends PluginAdapter{

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }
}
