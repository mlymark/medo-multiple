/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.w4u.medo.admin.session;

import java.io.Serializable;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author lihui
 */
@ManagedBean(name = "authoritySession")
@SessionScoped
public class AuthoritySession implements Serializable {
    
    private UserDetails userDetails;
    
    private String currentRole;
    
    private String userName;

    /**
     * *
     * init news information page
     */
    @PostConstruct
    public void setup() {
        currentRole = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if( authentication== null || "anonymousUser".equals(authentication.getPrincipal())){
            return;
        }
        userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        userName = userDetails.getUsername();
        
        Collection<? extends GrantedAuthority>  auths = userDetails.getAuthorities();
        
        //这里就只考虑一个用户只有一种角色的情况了
        for(GrantedAuthority auth : auths){
                currentRole = auth.getAuthority();
                System.out.println("Current user role is: "+currentRole);
        }
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public String getCurrentRole() {
        return currentRole;
    }

    public void setCurrentRole(String currentRole) {
        this.currentRole = currentRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    

}
