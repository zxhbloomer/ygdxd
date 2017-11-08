package com.ygdxd.mybatis;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ygdxd.mybatis.core.CoreMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 基于 Mybatis Mapper 的 Service 接口实现类
 * @author Created by ygdxd_admin at 2017-11-03 下午4:51
 */
public abstract class BaseServiceImpl<Record, Example> implements BaseService<Record, Example> {

    @Autowired
    private CoreMapper<Record> mapper;


    @Override
    public List<Record> select(Record record) {
        return mapper.select(record);
    }

    @Override
    public Record selectByPrimaryKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    public List<Record> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public Record selectOne(Record record) {
        return mapper.selectOne(record);
    }

    @Override
    public int selectCount(Record record) {
        return mapper.selectCount(record);
    }

    @Override
    public int insert(Record record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(Record record) {
        return mapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKey(Record record) {
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Record record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(Record record) {
        return mapper.delete(record);
    }

    @Override
    public int deleteByPrimaryKey(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    public List<Record> selectByExample(Example example) {
        return mapper.selectByExample(example);
    }

    @Override
    public int selectCountByExample(Example example) {
        return mapper.selectCountByExample(example);
    }

    @Override
    public int updateByExample(Record record, Example example) {
        return mapper.updateByExample(record, example);
    }

    @Override
    public int updateByExampleSelective(Record record, Example example) {
        return mapper.updateByExampleSelective(record, example);
    }

    @Override
    public int deleteByExample(Example example) {
        return mapper.deleteByExample(example);
    }

    @Override
    public Page<Record> select(Record record, int pageNum, int pageSize) {
        return this.select(record, pageNum, pageSize);
    }

    @Override
    public Page<Record> select(Record record, int pageNum, int pageSize, boolean count) {
        PageHelper.startPage(pageNum, pageSize, count);
        return (Page<Record>) this.select(record);
    }

    @Override
    public Page<Record> selectByExample(Example example, int pageNum, int pageSize) {
        return this.selectByExample(example, pageNum, pageSize);
    }

    @Override
    public Page<Record> selectByExample(Example example, int pageNum, int pageSize, boolean count) {
        PageHelper.startPage(pageNum, pageSize, count);
        return this.selectByExample(example, pageNum, pageSize, count);
    }
}
