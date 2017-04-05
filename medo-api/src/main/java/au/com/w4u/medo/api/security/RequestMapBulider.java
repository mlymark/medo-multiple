/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.w4u.medo.api.security;


import au.com.w4u.medo.model.Resc;
import au.com.w4u.medo.model.Role;
import au.com.w4u.medo.service.RescService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;



/**
 *
 * @author dell
 */
public class RequestMapBulider {
    
    @Autowired
    private RescService rescService;

    public void setRescService(RescService rescService) {
        this.rescService = rescService;
    }
    
    
    //拼接RequestMap  
    public LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> buildRequestMap() {  
        
        LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = new LinkedHashMap<>();
        
        List<Resc> rescs = rescService.findAll();
        for(Resc resc : rescs){
            String needRole = "[";
            String rescUrl = resc.getRes_string();
            AntPathRequestMatcher requestMatcher = new AntPathRequestMatcher(rescUrl);
            Set<Role> roles = resc.getRoles();
            List<ConfigAttribute> list = new ArrayList<ConfigAttribute>(); 
            for(Role role : roles){
                needRole = needRole+role.getName();
                list.add(new SecurityConfig(role.getName()));
            }
            needRole = needRole + "]";
            System.out.println("RequestMatcher: "+rescUrl+", needed roles is: "+needRole);
            requestMap.put(requestMatcher, list);
        }
        return requestMap;  
    }  
}
