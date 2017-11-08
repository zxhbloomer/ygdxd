package com.ygdxd.mybatis.common;

import com.ygdxd.mybatis.provider.MySelectByExampleProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 *
 * 自定义 Mapper 接口
 *
 * @author Created by ygdxd_admin at 2017-09-20 下午4:15
 */
public interface MySelectByExampleMapper<T> {

    /**
     * 根据Example条件进行查询
     *
     * @param example
     * @return
     */
    @SelectProvider(type = MySelectByExampleProvider.class, method = "dynamicSQL")
    List<T> selectByExample(Object example);
}
