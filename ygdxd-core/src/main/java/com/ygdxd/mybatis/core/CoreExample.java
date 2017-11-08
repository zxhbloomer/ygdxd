package com.ygdxd.mybatis.core;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 条件查询
 * <p>
 *     selectColumns: 配置查询的表字段名（不是配置JavaBean的属性名），其他返回hull
 * </p>
 * @author Created by ygdxd_admin at 2017-11-03 下午4:12
 */
public class CoreExample {

    public Set<String> getSelectColumns() {
        return selectColumns;
    }

    public void setSelectColumns(Set<String> selectColumns) {
        this.selectColumns = selectColumns;
    }

    protected Set<String> selectColumns;

    /**
     * 这里要配置表字段名，不是JavaBean的属性名，一般把 ID 主键配置上，有了 ID 后可以做其他一些操作
     * @param columns
     */
    public void setSelectColumns(String... columns){
        if (columns != null && columns.length > 0){
            if (selectColumns == null){
                selectColumns = new LinkedHashSet<>();
            }
            for (String column : columns){
                selectColumns.add(column);
            }
        }
    }


}
