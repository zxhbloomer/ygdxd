package com.ygdxd.spring.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 *
 * 自定义 BaseRepositoryFactoryBean 工场，去替代默认的工厂类
 * <p>
 * 我们需要让 spring 在加载的时候找到我们自定义的工场实现 BaseRepositoryFactoryBean，当我们使用 spring-boot 时，只需要在入口类中加入 EnableJpaRepositories
 * 即可，代码如下：
 * </p>
 * <pre>
 *     // 指定自己自定义的工场
 *     @EnableJpaRepositories(basePackages = {"cn.ts.core.spring.data"}, repositoryFactoryBeanClass =
 *     BaseRepositoryFactoryBean.class)
 * </pre>
 * @author Created by ygdxd_admin at 2017-09-14 下午10:25
 */
public class BaseRepositoryFactoryBean<R extends JpaRepository<T, ID>, T, ID extends Serializable> extends JpaRepositoryFactoryBean<R, T, ID>{


    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return super.createRepositoryFactory(entityManager);
    }

    private static class BaseRepositoryFactory<T, ID extends Serializable> extends JpaRepositoryFactory{

        /**
         * Creates a new {@link JpaRepositoryFactory}.
         *
         * @param entityManager must not be {@literal null}
         */
        public BaseRepositoryFactory(EntityManager entityManager) {
            super(entityManager);
        }

        @Override
        protected Object getTargetRepository(RepositoryInformation information) {
            return super.getTargetRepository(information);
        }
    }
}
