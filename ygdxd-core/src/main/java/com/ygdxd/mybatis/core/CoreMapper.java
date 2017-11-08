package com.ygdxd.mybatis.core;

import com.ygdxd.mybatis.common.MySelectByExampleMapper;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ExampleMapper;

/**
 * @author Created by ygdxd_admin at 2017-11-03 下午4:08
 */
public interface CoreMapper<Record> extends BaseMapper<Record>,
        ExampleMapper<Record>,MySelectByExampleMapper<Record>{

}
