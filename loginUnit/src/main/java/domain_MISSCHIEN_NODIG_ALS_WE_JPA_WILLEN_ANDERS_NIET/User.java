/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain_MISSCHIEN_NODIG_ALS_WE_JPA_WILLEN_ANDERS_NIET;

import java.util.ArrayList;

/**
 *
 * @author Mathijs Cox
 */
public class User {
    
    private String username;
    private String password;
    private ArrayList<Right> rights;

    public User(String username) {
        this.username = username;
    }

    public void setRights(ArrayList<Right> rights) {
        this.rights = rights;
    }
    
    public ArrayList<Right> getRights() {
        return rights;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
