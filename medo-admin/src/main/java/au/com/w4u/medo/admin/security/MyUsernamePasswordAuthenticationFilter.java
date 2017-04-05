/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.w4u.medo.admin.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author dell
 */
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
    public static final String USERNAME = "username";  
    public static final String PASSWORD = "password";  
    /**
     * @param request
     * @param response
     * @Description:用户登录验证方法入口 
     * @return 
     */  
    @Override  
    public Authentication attemptAuthentication(HttpServletRequest request,  
            HttpServletResponse response) throws AuthenticationException {  
  
        if (!request.getMethod().equals("POST")) {  
            throw new AuthenticationServiceException(  
                    "Authentication method not supported: "  
                            + request.getMethod());  
        }  
        String username = this.obtainUsername(request);  
        String password = this.obtainPassword(request);
        
        if (username == null) {  
            username = "";  
        }  
  
        if (password == null) {  
            password = "";  
        }  
  
        username = username.trim();  
  
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(  
                username, password);  
  
        // Allow subclasses to set the "details" property  
        setDetails(request, authRequest);  
  
        return this.getAuthenticationManager().authenticate(authRequest);  
  
    }  
  
      
  
    /**
     * @param request
     * @Description:获取密码 
     * @return 
     */  
    @Override  
    protected String obtainPassword(HttpServletRequest request) {  
        // TODO Auto-generated method stub  
        Object obj = request.getParameter(PASSWORD);  
        return null == obj ? "" : obj.toString();  
    }  
  
    /**
     * @param request
     * @Description:获取用户名 
     * @return 
     */  
    @Override  
    protected String obtainUsername(HttpServletRequest request) {  
        // TODO Auto-generated method stub  
        Object obj = request.getParameter(USERNAME); 
        return null == obj ? "" : obj.toString().trim().toLowerCase();  
    }  
  
}
