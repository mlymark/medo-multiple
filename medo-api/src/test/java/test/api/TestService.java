package test.api;



import au.com.w4u.medo.model.Role;
import au.com.w4u.medo.model.User;
import au.com.w4u.medo.service.RoleService;
import au.com.w4u.medo.service.UserService;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mly
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@Rollback
public class TestService {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;

    @Before
    public void init(){
        System.out.println("TestService.init()");
    }
    
     
    @Test
    public void testApi(){
        
    }
    
//    @Test
    public void testRestApi(){
        HttpClient httpclient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("http://192.168.0.181:8088/medo-api/api/user/list");
        try {
            String userPassword = "admin:admin";
            String encoding = Base64.encodeBase64String(userPassword.getBytes());
            System.out.println("encoding: "+encoding);
            request.setHeader("Authorization", "Basic " + encoding);
            HttpResponse response = httpclient.execute(request);
            // Check the response: if the body is empty then an error occurred
            System.out.println(response.getStatusLine().getStatusCode());
            if(response.getStatusLine().getStatusCode() != 200){
                throw new Exception("Error: '" + response.getStatusLine().getReasonPhrase() + "' - Code: " +
                        response.getStatusLine().getStatusCode());
            }
            
            // 获取验证状态
            String result = EntityUtils.toString(response.getEntity());
            
            //解析json
            Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            Type collectionType = new TypeToken<Map<String,Collection<User>>>(){}.getType();
            Map<String,Collection<User>> users = gson.fromJson(result, collectionType);
            List<User> userList = (List<User>) users.get("users");
            System.out.println(userList.size());
            
        } catch (Exception ex) {
            Logger.getLogger(TestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
