/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.w4u.medo.admin.view;

import au.com.w4u.medo.admin.constants.CommonConstants;
import au.com.w4u.medo.admin.custom.exception.NullRoleException;

import au.com.w4u.medo.model.Role;
import au.com.w4u.medo.model.User;
import au.com.w4u.medo.service.RoleService;
import au.com.w4u.medo.service.UserService;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 *
 * @author mly
 */
@ManagedBean(name = "signUpView")
@RequestScoped
public class SignUpView implements Serializable {
    
    @ManagedProperty(value = "#{userService}")
    private UserService userService;
    
    @ManagedProperty(value = "#{roleService}")
    private RoleService roleService;
    
    private String username;
    
    private String email;
    
    private Integer type = 0;
    
    private String password;
    
    private String password1;
    
    private String address;
    
    private String descn;
    
    @PostConstruct
    public void init(){
        System.out.println("au.com.w4u.medo.demo.view.SignUpView.init()");
        
    }
    
    public void signUp() throws NullRoleException, IOException {
        if(!checkInput()){
            return;
        }
        User user = new User();
        user.setUsername(username);
        // 加密密码(根据“密码{用户名})进行加密    
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        encoder.setEncodeHashAsBase64(true);
        password = encoder.encodePassword(password, username);
        System.out.println("Password:"+password);
        user.setPassword(password);
        user.setType(type);
        user.setEmail(email);
        user.setAddress(address);
        user.setDescn(descn);
        user.setStatus(0);
        user.setCreateDate(new Date());
        Role role = roleService.findRoleByName(CommonConstants.ROLE_USER);
        if(role != null){
            user.setRole(role);
        }
        userService.create(user);
        FacesContext.getCurrentInstance().getExternalContext().redirect("medo-demo/views/content/home.xhtml");
    }
    
    public boolean checkInput(){
        if(username.isEmpty()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "错误!", "用户名不能为空!"));
            return false;
        }else if(password.isEmpty()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "错误!", "用户名不能为空!"));
            return false;
        }else if(address.isEmpty()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "错误!", "用户名不能为空!"));
            return false;
        }
        
        if(!username.isEmpty()){
            User user = userService.findUserByName(username);
            if(user != null){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "错误!", "用户名已存在!"));
                return false;
            }
        }
        
        return true;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescn() {
        return descn;
    }

    public void setDescn(String descn) {
        this.descn = descn;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
    
}
