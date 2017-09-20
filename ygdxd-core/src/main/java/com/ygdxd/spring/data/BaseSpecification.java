package com.ygdxd.spring.data;

import com.google.common.collect.Lists;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Created by ygdxd_admin at 2017-09-16 下午4:55
 */
public class BaseSpecification<T> implements Specification<T> {


    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = Lists.newArrayList();
        predicates.add(cb.isNull(root.get("")));
        predicates.add(cb.isNotNull(root.get("")));

        return predicates.isEmpty() ? cb.isTrue(cb.literal(Boolean.TRUE)) : predicates.size() == 1 ? predicates.iterator().next() : cb.and((Predicate[]) predicates.toArray(new Predicate[predicates.size()]));
    }
}
