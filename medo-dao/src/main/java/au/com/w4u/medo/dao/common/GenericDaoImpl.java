/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.w4u.medo.dao.common;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author mly
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class GenericDaoImpl<T extends Serializable> implements IOperations<T> {
    
    private Class<T> clazz ;

    @PersistenceContext
    private EntityManager entityManager;

    protected final void setClazz(final Class<T> clazzToSet) {
        this.clazz = Preconditions.checkNotNull(clazzToSet);
    }
    
    @Override
    public final T findOne(final Integer id) {
        return (T)entityManager.find(clazz, id);
    }
    
    @Override
    public final T get(final Integer id) {
        return (T)entityManager.find(clazz, id);
    }

    @Override
    public final List<T> findAll() {
        Query query = entityManager.createQuery("from " + clazz.getName());
        return query.getResultList();
    }

    @Override
    public final void create(final T entity) {
        Preconditions.checkNotNull(entity);
        entityManager.persist(entity);
    }

    @Override
    public final T update(final T entity) {
        Preconditions.checkNotNull(entity);
        entityManager.merge(entity);
        return entity;
    }

    @Override
    public final void delete(final T entity) {
        Preconditions.checkNotNull(entity);
        entityManager.remove(getEntityManager().merge(entity));
    }

    @Override
    public final void deleteById(final Integer entityId) {
        final T entity = findOne(entityId);
        Preconditions.checkState(entity != null);
        delete(entity);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    
}
