package com.ygdxd.spring.data;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Created by ygdxd_admin at 2017-09-15 上午10:30
 */
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID>{

    private final JpaEntityInformation<T, ?> entityInformation;

    private final EntityManager entityManager;

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.entityManager = entityManager;
    }

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em, JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
//        super(domainClass, em);
//        this.entityInformation = entityInformation;
//        this.entityManager = entityManager;
        this(JpaEntityInformationSupport.getEntityInformation(domainClass, entityManager), entityManager);
    }

    @Override
    public List<Map<String, Object>> listBySQL(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);//object[] 转 map集合
        return query.getResultList();
    }

    @Override
    public List<Map<String, Object>> listBySQL(String sql, Object[] vals) {
        Query query = entityManager.createNativeQuery(sql);
        // 将Object[] 转换为 Map 集合
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        // 处理参数
        if (vals != null) {
            for (int i = 0; i < vals.length; i++) {
                // 从 1 开始
                query.setParameter(i + 1, vals[i]);
            }
        }
        return query.getResultList();
    }

    @Override
    public Page<Map<String, Object>> listBySQL(String sql, Object[] vals, int page, int size) {
        Query query = entityManager.createNativeQuery(sql);
        // 将Object[] 转换为 Map 集合
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        // 处理参数
        if (vals != null) {
            for (int i = 0; i < vals.length; i++) {
                query.setParameter(i + 1, vals[i]);
            }
        }
        if (page <= 0 || size <= 0) {
            return new PageImpl(query.getResultList());
        }
        // 使用这 setFirstResult 和 setMaxResults 进行分页屏蔽了底层数据库的差异性
        query.setFirstResult(page);
        query.setMaxResults(size);

//        Long total = executeCountQuery(this.getCountQuery(spec, domainClass));
//        List content = total.longValue() > (long) pageable.getOffset() ? query.getResultList() : Collections
//                 .emptyList();
//        return new PageImpl(content, pageable, total.longValue());
        return null;
    }
}
