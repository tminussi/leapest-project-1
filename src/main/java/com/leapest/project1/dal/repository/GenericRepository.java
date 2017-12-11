package com.leapest.project1.dal.repository;


import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class GenericRepository<T> {

    private Class<T> entityClass;
    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger logger = LoggerFactory.getLogger(GenericRepository.class);

    public GenericRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Transactional
    public void save(T object) throws HibernateException {
        logger.info("Saving {} object", entityClass.getSimpleName());
        entityManager.persist(object);
    }

    @Transactional
    public void update(T object) throws HibernateException  {
        logger.info("Updating {} object", entityClass.getSimpleName());
        entityManager.merge(object);
    }

    public List<T> listAll(String field) {
        logger.info("Listing all {} objects", entityClass.getSimpleName());

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(entityClass);
        Root<T> from = query.from(entityClass);
        query.orderBy(cb.asc(from.get(field)));

        return entityManager.createQuery(query.select(from)).getResultList();
    }

    @Transactional
    public void delete(T object) throws HibernateException  {
        entityManager.remove(object);
    }

    @Transactional
    public void delete(Long id) throws HibernateException  {
        Optional<T> object = findOne(id);
        if(object.isPresent())
            delete(object.get());
    }

    public Optional<T> findOne(Long id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }
}
