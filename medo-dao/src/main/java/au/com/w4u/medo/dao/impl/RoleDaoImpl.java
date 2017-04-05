/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.w4u.medo.dao.impl;


import au.com.w4u.medo.dao.RoleDao;
import au.com.w4u.medo.dao.common.GenericDaoImpl;
import au.com.w4u.medo.model.Role;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mly
 */
@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao{
    
    public RoleDaoImpl() {
        super();
        setClazz(Role.class);
    }

    @Override
    public Role findRoleByName(String name) {
        Query query = getEntityManager().createQuery("select role from Role role where role.name = :rolename");
        query.setParameter("rolename", name);
        List roles = query.getResultList();
        if(roles.size() > 0){
            return (Role) roles.get(0);
        }else{
            return null;
        }
    }
}
