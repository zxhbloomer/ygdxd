package com.ygdxd.spring.data;

import com.google.common.collect.Lists;
import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Created by ygdxd_admin at 2017-09-17 下午8:54
 */
@Data
public class BaseSecificationWraper<T> {

    protected Root<T> root;

    protected CriteriaQuery<?> query;

    protected CriteriaBuilder builder;

    protected List<Predicate> predicates;

    public BaseSecificationWraper(){
        if (predicates.isEmpty()){
            predicates = Lists.newArrayList();
        }
    }


    public BaseSecificationWraper(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> predicates) {
        this.root = root;
        this.query = query;
        this.builder = builder;
        if (predicates.isEmpty()){
            predicates = Lists.newArrayList();
        }
        this.predicates = predicates;
    }
}
