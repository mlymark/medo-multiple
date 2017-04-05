/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.w4u.medo.admin.utils;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mly
 */
public class JsfUtil {
    
    public static void redirect(String path){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            context.responseComplete();      
            HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
            response.sendRedirect(path);
        } catch (IOException ex) {
            Logger.getLogger(JsfUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
