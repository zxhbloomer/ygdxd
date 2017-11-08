package com.ygdxd.mybatis.provider;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

/**
 * @author Created by ygdxd_admin at 2017-09-20 下午4:25
 */
public class MySelectByExampleProvider extends MapperTemplate{


    public MySelectByExampleProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     * 根据Example查询
     *
     * @param ms
     * @return
     */
    public String selectByExample(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        //将返回值修改为实体类型
        setResultType(ms, entityClass);
        StringBuilder sql = new StringBuilder("SELECT ");
        if (isCheckExampleEntityClass()) {
            sql.append(SqlHelper.exampleCheck(entityClass));
        }
        sql.append("<if test=\"distinct\">distinct</if>");
        //支持查询指定列
        sql.append(exampleSelectColumns(entityClass));
        sql.append(SqlHelper.fromTable(entityClass, tableName(entityClass)));
        sql.append(SqlHelper.exampleWhereClause());
        sql.append(SqlHelper.exampleOrderBy(entityClass));
        // sql.append(exampleForUpdate());
        return sql.toString();
    }

    private static String exampleSelectColumns(Class<?> entityClass) {
        StringBuilder sql = new StringBuilder();
        sql.append("<if test=\"_parameter.selectColumns != null and selectColumns.size() > 0\">");
        sql.append("<foreach collection=\"_parameter.selectColumns\" item=\"selectColumn\" separator=\",\">");
        sql.append("${selectColumn}");
        sql.append("</foreach>");
        sql.append("</if>");
        sql.append("<if test=\"_parameter.selectColumns == null\">");
        sql.append(SqlHelper.getAllColumns(entityClass));
        sql.append("</if>");
        return sql.toString();
    }



}
