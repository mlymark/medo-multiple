/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.w4u.medo.admin.view;


import au.com.w4u.medo.model.Role;
import au.com.w4u.medo.model.User;
import au.com.w4u.medo.service.RoleService;
import au.com.w4u.medo.service.UserService;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 *
 * @author mly
 */
@ManagedBean(name = "addUserView")
@ViewScoped
public class AddUserView implements Serializable {
  
    private User user;
    
    private Integer roleId;
    
    @ManagedProperty(value = "#{userService}")
    private UserService userService;
    
    @ManagedProperty(value = "#{roleService}")
    private RoleService roleService;

    /**
     * init news information page
     */
    @PostConstruct
    public void setup() {
        user = new User();
        user.setType(0);
        user.setStatus(0);
    }
    
    public void saveUser(){
        //先弄个默认的，不然记不住;以后这里要生成随机的发到用户邮箱中
        String randomPwd = "123456";
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        encoder.setEncodeHashAsBase64(true);
        randomPwd = encoder.encodePassword(randomPwd, user.getUsername());
        user.setPassword(randomPwd);
        user.setCreateDate(new Date());
        Role role = roleService.findOne(roleId);
        user.setRole(role);
        userService.create(user);
        String result  = "/medo-demo/views/content/users.xhtml";
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(result);
        } catch (IOException ex) {
            Logger.getLogger(UserView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
