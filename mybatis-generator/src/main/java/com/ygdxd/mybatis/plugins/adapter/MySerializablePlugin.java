package com.ygdxd.mybatis.plugins.adapter;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;

import java.util.List;
import java.util.Properties;

/**
 * @author Created by ygdxd_admin at 2017-09-19 下午3:48
 */
public class MySerializablePlugin extends PluginAdapter{

    private FullyQualifiedJavaType serializable;

    private FullyQualifiedJavaType GWTSerializable;

    private boolean addGWTInterface;

    private boolean suppressJavaInterface;

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        this.addGWTInterface = Boolean.valueOf(properties.getProperty("addGWTInterface", "false")).booleanValue();
        this.suppressJavaInterface = Boolean.valueOf(properties.getProperty("suppressJavaInterface", "false")).booleanValue();
    }

    public MySerializablePlugin() {
        super();
        this.addGWTInterface = false;
        this.serializable = new FullyQualifiedJavaType("java.io.Serializable");
        this.GWTSerializable = new FullyQualifiedJavaType("com.google.gwt.user.client.rpc.IsSerializable");
    }

    public MySerializablePlugin(boolean addGWTInterface) {
        super();
        this.addGWTInterface = addGWTInterface;
        this.serializable = new FullyQualifiedJavaType("java.io.Serializable");
        this.GWTSerializable = new FullyQualifiedJavaType("com.google.gwt.user.client.rpc.IsSerializable");
    }


    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        makeSerializable(topLevelClass, introspectedTable);
        return true;
    }

    @Override
    public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        makeSerializable(topLevelClass, introspectedTable);
        return true;
    }

    @Override
    public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        makeSerializable(topLevelClass, introspectedTable);
        return true;
    }

    protected void makeSerializable(TopLevelClass clazz, IntrospectedTable introspectedTable){
        if (this.addGWTInterface){
            clazz.addImportedType(this.GWTSerializable);
            clazz.addSuperInterface(this.GWTSerializable);
        }

        if (!this.suppressJavaInterface){
            clazz.addImportedType(this.serializable);
            clazz.addSuperInterface(this.serializable);
            Field field = new Field();
            field.setFinal(true);
            field.setStatic(true);
            field.setInitializationString("1L");
            field.setName("serialVersionUID");
            field.setType(new FullyQualifiedJavaType("long"));
            field.setVisibility(JavaVisibility.PRIVATE);
            this.context.getCommentGenerator().addFieldComment(field, introspectedTable);
            clazz.addField(field);
        }
    }

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        makeSerializable(topLevelClass, introspectedTable);
        for (InnerClass clazz : topLevelClass.getInnerClasses()){
            if ("GeneratedCriteria".equals(clazz.getType().getShortName())) {
                clazz.addSuperInterface(serializable);
            }
            if ("Criteria".equals(clazz.getType().getShortName())) {
                clazz.addSuperInterface(serializable);
            }
            if ("Criterion".equals(clazz.getType().getShortName())) {
                clazz.addSuperInterface(serializable);
            }
        }
        return true;
    }
}
