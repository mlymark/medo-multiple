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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author mly
 */
@ManagedBean(name = "editUserView")
@ViewScoped
public class EditUserView implements Serializable {
  
    private User editUser;
    
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
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Integer id = Integer.parseInt(request.getParameter("userid"));
        System.out.println("au.com.w4u.medo.demo.view.editUserView.setup() id="+id);
        editUser = userService.findOne(id);
        roleId = editUser.getRole().getId();
    }
    
    public void updateUser(){
        Role role = roleService.findOne(roleId);
        editUser.setRole(role);
        userService.update(editUser);
        String result  = "/medo-demo/views/content/users.xhtml";
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(result);
        } catch (IOException ex) {
            Logger.getLogger(UserView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public User getEditUser() {
        return editUser;
    }

    public void setEditUser(User editUser) {
        this.editUser = editUser;
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
    
}
