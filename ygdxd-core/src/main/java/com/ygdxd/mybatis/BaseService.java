package com.ygdxd.mybatis;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 基于 Mybatis Mapper 的 Service 接口基类
 * @author Created by ygdxd_admin at 2017-11-03 下午4:25
 */
public interface BaseService<Record, Example> {

    /**
     * 根据实体类中的属性进行查询,查询条件使用等号
     * @param record 实体类
     * @return 实体类列表
     */
    List<Record> select(Record record);

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     * @param key 主键
     * @return 实体类
     */
    Record selectByPrimaryKey(Object key);


    /**
     * 查询所有实体
     * @return 所有实体列表
     */
    List<Record> selectAll();

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
     *
     * @param record 实体类
     */
    Record selectOne(Record record);

    /**
     * 根据实体中的属性查询总数，查询条件使用等号
     *
     * @param record 实体类
     */
    int selectCount(Record record);

    /**
     * 保存一个实体，null的属性也会保存，不会使用数据库默认值
     *
     * @param record 实体类
     */
    int insert(Record record);

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param record 实体类
     */
    int insertSelective(Record record);

    /**
     * 根据主键更新实体全部字段，null值会被更新
     *
     * @param record 实体类
     */
    int updateByPrimaryKey(Record record);

    /**
     * 根据主键更新属性不为null的值
     *
     * @param record 实体类
     */
    int updateByPrimaryKeySelective(Record record);

    /**
     * 根据实体属性作为条件进行删除，查询条件使用等号
     *
     * @param record 实体类
     */
    int delete(Record record);

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     *
     * @param key 主键
     */
    int deleteByPrimaryKey(Object key);

    /*------------------------------------- Example方法 -------------------------------------*/

    /**
     * 根据Example条件进行查询
     * <p>重点：这个查询支持通过Example类指定查询列，通过selectProperties方法指定查询列</p>
     *
     * @param example 查询类
     */
    List<Record> selectByExample(Example example);

    /**
     * 根据Example条件进行查询总数
     *
     * @param example 查询类
     */
    int selectCountByExample(Example example);

    /**
     * 根据Example条件更新实体record包含的全部属性，null值会被更新
     *
     * @param record  实体类
     * @param example 查询类
     */
    int updateByExample(@Param("record") Record record, @Param("example") Example example);

    /**
     * 根据Example条件更新实体record包含的不是null的属性值
     *
     * @param record  实体类
     * @param example 查询类
     */
    int updateByExampleSelective(@Param("record") Record record, @Param("example") Example example);

    /**
     * 根据Example条件删除数据
     *
     * @param example 查询类
     */
    int deleteByExample(Example example);

    /*------------------------------------- 自定义分页查询 -------------------------------------*/

    /**
     * 自定义方法：根据实体中的属性值进行分页查询，查询条件使用等号，默认返回总数
     * @param record 实体类
     * @param pageNum 页码 从1开始
     * @param pageSize 页面大小
     * @return 分页对象
     */
    Page<Record> select(Record record, int pageNum, int pageSize);

    /**
     * 自定义方法：根据实体中的属性值进行分页查询，查询条件使用等号
     * 移除？
     *
     * @param record 实体类
     * @param pageNum 页码 从1开始
     * @param pageSize 页面大小
     * @param count 是否返回总数
     * @return
     */
    Page<Record> select(Record record, int pageNum, int pageSize, boolean count);

    /**
     * 自定义方法：根据Example条件进行分页查询，默认返回总数
     * @param example 条件
     * @param pageNum 页码 从1开始
     * @param pageSize 页面大小
     * @return
     */
    Page<Record> selectByExample(Example example, int pageNum, int pageSize);


    /**
     *
     * @param example
     * @param pageNum
     * @param pageSize
     * @param count
     * @return
     */
    Page<Record> selectByExample(Example example, int pageNum, int pageSize, boolean count);


}
