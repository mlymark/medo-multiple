/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.w4u.medo.admin.security;

import au.com.w4u.medo.model.User;
import au.com.w4u.medo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 可以自己定义UserDetailsService，但是个人觉得没必要，这会忽略一些异常
 * @author dell
 */
public class MyUserDetailsService implements UserDetailsService {

    @Autowired  
    private UserService userService; 
    
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 获取用户信息  
        User user = userService.findUserByName(username);  
        if (user != null) {  
            //这里业务环境只考虑一个用户对应一种角色的情况；如果一个用户对应多个角色的话这里需要修改
            if(user.getRole() != null){
                boolean enabled = user.getStatus() != 0;
                // 设置角色  
                return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                        enabled, true, true, true, AuthorityUtils.createAuthorityList(user.getRole().getName()));  
            }else{
                System.out.println("用户没有权限!");
                throw new UsernameNotFoundException("用户没有权限!");
            }
        }  
  
        throw new UsernameNotFoundException("User '" + username  
                    + "' not found.");  
    }

    public MessageSourceAccessor getMessages() {
        return messages;
    }

    public void setMessages(MessageSourceAccessor messages) {
        this.messages = messages;
    }
    
}
