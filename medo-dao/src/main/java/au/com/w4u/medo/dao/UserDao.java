/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.w4u.medo.dao;

import au.com.w4u.medo.dao.common.IOperations;
import au.com.w4u.medo.model.User;


/**
 *
 * @author mly
 */
public interface UserDao extends IOperations<User> {
   User findUserByName(String name);
}
