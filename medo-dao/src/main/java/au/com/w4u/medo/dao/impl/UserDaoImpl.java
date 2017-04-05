/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.w4u.medo.dao.impl;

import au.com.w4u.medo.dao.UserDao;
import au.com.w4u.medo.dao.common.GenericDaoImpl;
import au.com.w4u.medo.model.User;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mly
 */
@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao{
    
    public UserDaoImpl() {
        super();
        setClazz(User.class);
    }

    @Override
    public User findUserByName(String name) {
        Query query = this.getEntityManager().createQuery("select user from User user where user.username = :username");
        query.setParameter("username", name);
        List users = query.getResultList();
        if(users.size() > 0){
            return (User) users.get(0);
        }else{
            return null;
        }
    }
   
}
