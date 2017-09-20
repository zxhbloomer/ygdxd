package com.ygdxd.mybatis.plugins.adapter;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * @author ygdxd_admin
 * @create 2017-09-14 下午12:44
 */
public class MapperPluginAdapter extends PluginAdapter{

    protected Set<String> mappers = new LinkedHashSet<>();

    private static final boolean CREATE_METHOD = false;

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        String mappers = this.properties.getProperty("mappers");
        if (StringUtility.stringHasValue(mappers)){
            for (String mapper : mappers.split(",")){
                this.mappers.add(mapper);
            }
        } else {
            throw new RuntimeException("MapperPluginAdaptor 插件缺少 mappers 属性");
        }
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        //获取实体类
        FullyQualifiedJavaType entityType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        //import接口
        for (String mapper : mappers) {
            interfaze.addImportedType(new FullyQualifiedJavaType(mapper));
            interfaze.addSuperInterface(new FullyQualifiedJavaType(mapper + "<" + entityType.getShortName() + ">"));
        }
        //import实体类
        interfaze.addImportedType(entityType);
        return true;
    }

    private void processEntityClass(TopLevelClass topLevelClass, IntrospectedTable introspectedTable){
        topLevelClass.addImportedType("javax.persistence.*");
        String tableName = introspectedTable.getFullyQualifiedTableNameAtRuntime();
        // 是否忽略大小写，对于区分大小写的数据库，会有用
        if (!topLevelClass.getType().getShortName().equals(tableName)) {
            topLevelClass.addAnnotation("@Table(name = \"" + tableName + "\")");
        } else if (!topLevelClass.getType().getShortName().equalsIgnoreCase(tableName)) {
            topLevelClass.addAnnotation("@Table(name = \"" + tableName + "\")");
        } else {
            topLevelClass.addAnnotation("@Table(name = \"" + tableName + "\")");
        }
    }

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        processEntityClass(topLevelClass, introspectedTable);
        return true;
    }




}
