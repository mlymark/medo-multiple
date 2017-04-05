/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.w4u.medo.service.common;

import au.com.w4u.medo.dao.common.IOperations;
import java.io.Serializable;
import java.util.List;


/**
 *
 * @author mly
 * @param <T>
 */
public abstract class GenericService <T extends Serializable> implements IOperations<T> {
    
    protected abstract IOperations<T> getDao();

    @Override
    public T findOne(final Integer id) {
        return getDao().findOne(id);
    }
    
    @Override
    public T get(final Integer id){
        return getDao().get(id);
    }

    @Override
    public List<T> findAll() {
        return getDao().findAll();
    }

    @Override
    public void create(final T entity) {
        getDao().create(entity);
    }

    @Override
    public T update(final T entity) {
        return getDao().update(entity);
    }

    @Override
    public void delete(final T entity) {
        getDao().delete(entity);
    }

    @Override
    public void deleteById(Integer entityId) {
        getDao().deleteById(entityId);
    }
    
}
