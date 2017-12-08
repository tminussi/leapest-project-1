package com.leapest.project1.dal.repository;


import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    public List<T> listAll(String sql) {
        logger.info("Listing all {} objects", entityClass.getSimpleName());
        List<T> objects = null;
        objects = entityManager.createQuery(sql, entityClass).getResultList();
        return objects;
    }

    @Transactional
    public void delete(T object) throws HibernateException  {
        entityManager.remove(object);
    }

    @Transactional
    public void delete(Integer id) throws HibernateException  {
        Optional<T> object = findOne(id);
        if(object.isPresent())
            delete(object.get());
    }

    public Optional<T> findOne(Integer id) {
        return Optional.of(entityManager.find(entityClass, id));
    }
}
