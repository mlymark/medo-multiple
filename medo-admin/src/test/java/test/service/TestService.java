package test.service;


import au.com.w4u.medo.model.Role;
import au.com.w4u.medo.model.User;
import au.com.w4u.medo.service.RoleService;
import au.com.w4u.medo.service.UserService;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mly
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@Rollback
public class TestService {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;

    @Before
    public void init(){
        System.out.println("TestService.init()");
    }
    
//    @Test
    public void testAddUser(){
        Role role = roleService.findRoleByName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        User entity = new User();
        entity.setUsername("mlymark");
        entity.setPassword("7514470");
        entity.setStatus(1);
        entity.setDescn("I am a user");
        userService.create(entity);
        
        System.out.println("ID: "+entity.getId());
        
    } 
    
//    @Test
    public void testDeleteUser(){
        User user = userService.findUserByName("mlymark");
        userService.delete(user);
    }
    
    @Test
    public void testFindUsers(){
        User user = userService.findUserByName("admin");
//        System.out.println(user.getPassword());
    }
    
}
