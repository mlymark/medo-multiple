/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.w4u.medo.admin.custom.exception;

/**
 *
 * @author mly
 */
public class NullRoleException extends Exception{

    String message;

    public NullRoleException(String msg) {
        message = msg;
    }

    @Override
    public String getMessage() {
        return message;
    }
    
}
