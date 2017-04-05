/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.w4u.medo.admin.view;


import au.com.w4u.medo.model.User;
import au.com.w4u.medo.service.UserService;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author lihui
 */
@ManagedBean(name = "userView")
@ViewScoped
public class UserView implements Serializable {

    // Private Constants
    private List<User> users;
    
    private List<User> filteredUsers;
    
    private User selectUser;
    
    @ManagedProperty(value = "#{userService}")
    private UserService userService;

    /**
     * *
     * init news information page
     */
    @PostConstruct
    public void setup() {
        users = userService.findAll();
    }
    
    
    public void editUser(User user){
        String result  = "/medo-demo/views/content/editUser.xhtml?userid="+user.getId();
        System.out.println("result: "+result);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(result);
        } catch (IOException ex) {
            Logger.getLogger(UserView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public List<User> getFilteredUsers() {
        return filteredUsers;
    }

    public void setFilteredUsers(List<User> filteredUsers) {
        this.filteredUsers = filteredUsers;
    }

    public User getSelectUser() {
        return selectUser;
    }

    public void setSelectUser(User selectUser) {
        this.selectUser = selectUser;
    }
    
}
