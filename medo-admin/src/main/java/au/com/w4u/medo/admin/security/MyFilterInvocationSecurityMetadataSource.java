/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.w4u.medo.admin.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;



/**
 *
 * @author dell
 */
public class MyFilterInvocationSecurityMetadataSource  implements  
        FilterInvocationSecurityMetadataSource, InitializingBean {
    
    private final static List<ConfigAttribute> NULL_CONFIG_ATTRIBUTE = null;  
    // 资源权限集合  
    private Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;  
      
    //查找数据库权限和资源关系  
    private RequestMapBulider builder;  
    
    
    public void refreshResuorceMap() {  
        this.requestMap = builder.buildRequestMap();  
    }  
    
    @Override
    public void afterPropertiesSet() throws Exception {
         this.requestMap = builder.buildRequestMap();
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        final HttpServletRequest request = ((FilterInvocation) o)  
                .getRequest();
        Collection<ConfigAttribute> attrs = NULL_CONFIG_ATTRIBUTE;  
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap  
                .entrySet()) {  
            if (entry.getKey().matches(request)) {  
                attrs = entry.getValue();  
                break;  
            }  
        }  
        return attrs;  
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet<>();  
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap  
                .entrySet()) {  
            System.out.println(entry.getValue());
            allAttributes.addAll(entry.getValue());  
        }  
        System.out.println("总共有这些权限："+allAttributes.toString());  
        return allAttributes;
    }

    @Override
    public boolean supports(Class<?> clazz) {
       return FilterInvocation.class.isAssignableFrom(clazz);  
    }

    public Map<RequestMatcher, Collection<ConfigAttribute>> getRequestMap() {
        return requestMap;
    }

    public void setRequestMap(Map<RequestMatcher, Collection<ConfigAttribute>> requestMap) {
        this.requestMap = requestMap;
    }

    public RequestMapBulider getBuilder() {
        return builder;
    }

    public void setBuilder(RequestMapBulider builder) {
        this.builder = builder;
    }
    
}
