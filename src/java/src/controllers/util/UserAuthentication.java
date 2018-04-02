/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.controllers.util;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;

/**
 *
 * @author christop.hirschi
 */
@Named(value = "userAuthentication")
@Dependent
public class UserAuthentication {

    /**
     * Creates a new instance of UserAuthentication
     */
    public UserAuthentication() {
    }
    
    public static void logout() throws IOException {
     	FacesContext context = FacesContext.getCurrentInstance();
     	context.getExternalContext().invalidateSession();
        context.getExternalContext().redirect("/PoetrySandwich/");
    }
}
