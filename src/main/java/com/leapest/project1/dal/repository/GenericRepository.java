package com.leapest.project1.dal.repository;


import com.leapest.project1.exception.EntityNotFoundException;
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

/**
 * Generic repository with default methods to handle data on database
 * @author Diego Antonelli
 * @param <T>
 */
public class GenericRepository<T> {

    private Class<T> entityClass;
    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger logger = LoggerFactory.getLogger(GenericRepository.class);

    public GenericRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Save an object on database
     * @param object
     */
    @Transactional
    public void save(T object) throws HibernateException {
        logger.info("Saving {} object", entityClass.getSimpleName());
        entityManager.persist(object);
    }

    /**
     * Update an object on database
     * @param object
     */
    @Transactional
    public void update(T object) throws HibernateException  {
        logger.info("Updating {} object", entityClass.getSimpleName());
        entityManager.merge(object);
    }

    /**
     * List all objects from database with sort field
     * @param field
     * @return List of objects
     */
    public List<T> listAll(String field) {
        logger.info("Listing all {} objects", entityClass.getSimpleName());

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(entityClass);
        Root<T> from = query.from(entityClass);
        query.orderBy(cb.asc(from.get(field)));

        return entityManager.createQuery(query.select(from)).getResultList();
    }

    /**
     * Delete an object from database
     * @param object
     * @throws HibernateException
     */
    @Transactional
    public void delete(T object) throws HibernateException  {
        entityManager.remove(object);
    }

    /**
     * Delete an object from database by id
     * @param id
     * @throws HibernateException
     * @throws EntityNotFoundException
     */
    @Transactional
    public void delete(Long id) throws HibernateException, EntityNotFoundException {
        Optional<T> object = findOne(id);
        if(object.isPresent())
            delete(object.get());
        else
            throw new EntityNotFoundException(String.format("Entity of class %s with id %s not found!", entityClass, id));
    }

    /**
     * Find an object by id
     * @param id
     * @return Optional object
     */
    public Optional<T> findOne(Long id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }
}
