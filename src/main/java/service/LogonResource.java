/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Mathijs Cox
 */
@Path("/")
@Stateless
public class LogonResource {
    
    @Inject
    LogonService logonService;

    @POST
    @Path("/login")
    @Consumes({"application/xml", "application/json"})
    public int login(String username, String hash, String systeem) {
        return logonService.login(username, hash, systeem);
    }
    
    @PUT
    @Path("/register")
    @Consumes({"application/xml", "application/json"})
    public int register(String username, String hash) {
        return logonService.register(username, hash);
    }
    
    @PUT
    @Path("/addRight")
    @Consumes({"application/xml", "application/json"})
    public void addRight(String username, String right) {
        logonService.addRight(username,right);
    }

    @DELETE
    @Path("/removeRight")
    @Consumes({"application/xml", "application/json"})
    public void removeRight(String username, String right) {
        logonService.removeRight(username,right);
    }

    @DELETE
    @Path("/remove")
    @Consumes({"application/xml", "application/json"})
    public void remove(String username) {
        logonService.remove(username);
    }

    @GET
    @Path("/count")
    @Produces("application/json")
    public int count() {
        return logonService.count();
    }
}
