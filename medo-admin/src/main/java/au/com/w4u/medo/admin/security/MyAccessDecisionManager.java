/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.w4u.medo.admin.security;

import java.util.Collection;
import java.util.Iterator;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author dell
 */
public class MyAccessDecisionManager  implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if(configAttributes == null) {    
            return;  
        }    
        //所请求的资源拥有的权限(一个资源对多个权限)    
        Iterator<ConfigAttribute> iterator = configAttributes.iterator();    
        while(iterator.hasNext()) {    
            ConfigAttribute configAttribute = iterator.next();    
            //访问所请求资源所需要的权限    
            String needPermission = configAttribute.getAttribute();    
//            System.out.println("访问"+object.toString()+"需要的权限是：" + needPermission);    
            //用户所拥有的权限authentication    
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();  
            for(GrantedAuthority ga : authorities) {    
                if(needPermission.equals(ga.getAuthority())) {    
                    return;  
                }    
            }  
        }  
        //没有权限    
        throw new AccessDeniedException(" 没有权限访问！ "); 
    }

    @Override
    public boolean supports(ConfigAttribute ca) {
        return true;
    }

    @Override
    public boolean supports(Class<?> type) {
        return true;
    }
    
}
