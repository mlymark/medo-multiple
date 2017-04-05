/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.w4u.medo.dao.common;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author mly
 * @param <T>
 */
public interface  IOperations <T extends Serializable> extends Serializable{

    T findOne(final Integer id);
    
    T get(final Integer id);

    List<T> findAll();

    void create(final T entity);
    
    T update(final T entity);

    void delete(final T entity);

    void deleteById(final Integer entityId);

}
