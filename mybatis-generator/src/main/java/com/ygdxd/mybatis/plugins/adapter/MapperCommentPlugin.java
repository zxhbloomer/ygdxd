package com.ygdxd.mybatis.plugins.adapter;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * @author ygdxd_admin
 * @create 2017-09-14 下午12:55
 */
public class MapperCommentPlugin extends PluginAdapter{

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean clientCountByExampleMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        method.addJavaDocLine("/**");
        method.addJavaDocLine(" * 通过条件统计总数");
        method.addJavaDocLine(" */");
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        return true;
    }

    @Override
    public boolean clientDeleteByExampleMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        method.addJavaDocLine("/**");
        method.addJavaDocLine(" * 通过条件删除数据");
        method.addJavaDocLine(" */");
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        return true;
    }

    @Override
    public boolean clientInsertMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        method.addJavaDocLine("/**");
        method.addJavaDocLine(" * 插入数据");
        method.addJavaDocLine(" */");
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        return true;
    }

    @Override
    public boolean clientDeleteByExampleMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        method.addJavaDocLine("/**");
        method.addJavaDocLine(" * 根据例子删除");
        method.addJavaDocLine(" */");
        context.getCommentGenerator().addClassComment(topLevelClass, introspectedTable);
        return true;
    }

    @Override
    public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        method.addJavaDocLine("/**");
        method.addJavaDocLine(" * 根据主键删除");
        method.addJavaDocLine(" */");
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        return true;
    }

    @Override
    public boolean clientSelectByExampleWithBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        method.addJavaDocLine("/**");
        method.addJavaDocLine(" * 根据条件查询(包括Blob、Clob字段)");
        method.addJavaDocLine(" */");
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        return true;
    }

    @Override
    public boolean clientUpdateByExampleSelectiveMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        method.addJavaDocLine("/**");
        method.addJavaDocLine(" * 根据条件更新");
        method.addJavaDocLine(" */");
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        return true;
    }

    @Override
    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        method.addJavaDocLine("/**");
        method.addJavaDocLine(" * 根据主键查找");
        method.addJavaDocLine(" */");
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        return true;
    }

    @Override
    public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        method.addJavaDocLine("/**");
        method.addJavaDocLine(" * 更加条件查找 不能用blob字段");
        method.addJavaDocLine(" */");
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        return true;
    }

    @Override
    public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        method.addJavaDocLine("/**");
        method.addJavaDocLine(" * 根据主键更新 不包括blob字段");
        method.addJavaDocLine(" */");
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        return true;
    }

    @Override
    public boolean clientSelectAllMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        method.addJavaDocLine("/**");
        method.addJavaDocLine(" * 查询所有数据");
        method.addJavaDocLine(" */");
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        return true;
    }
}
