/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.w4u.medo.service.impl;


import au.com.w4u.medo.dao.RoleDao;
import au.com.w4u.medo.dao.common.IOperations;
import au.com.w4u.medo.model.Role;
import au.com.w4u.medo.service.RoleService;
import au.com.w4u.medo.service.common.GenericService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author dell
 */
public class RoleServiceImpl extends GenericService<Role> implements RoleService{
    @Autowired
    private RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
    
    public RoleServiceImpl() {
        super();
    }

    @Override
    protected IOperations<Role> getDao() {
        return this.roleDao;
    }

    @Override
    public Role findRoleByName(String name) {
        return roleDao.findRoleByName(name);
    }
    
}
