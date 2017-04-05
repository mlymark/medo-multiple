/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.w4u.medo.api.controller;


import au.com.w4u.medo.model.User;
import au.com.w4u.medo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author mly
 * 调用的url:http://localhost:8080/项目名/上面web.xml配置(api)/user/list
 */
@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    @RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE,  method = RequestMethod.GET)
    public String getAllUsersJSON(Model model) {
        model.addAttribute("users", userService.findAll());
        return "jsonTemplate";
    }
    
    @RequestMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE,  method = RequestMethod.GET)
    public String getUserJSON(@PathVariable("id") int id,Model model) {
        model.addAttribute("user", userService.findOne(id));
        return "jsonTemplate";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userService.create(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) 
    {
        userService.update(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) 
    {
        userService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
