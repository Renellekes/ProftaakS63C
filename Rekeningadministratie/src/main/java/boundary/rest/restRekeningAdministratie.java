/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.rest;

import domain.Cartracker;
import domain.FactuurOnderdeel;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import service.IRekeningAdministratie;

/**
 *
 * @author kay de groot
 */
@Path("RekAdmin")
@Stateless
public class restRekeningAdministratie {
    
    @Inject
    private IRekeningAdministratie ira;
    
    @PUT
    @Path("addFactuurOnderdeel/{factuurOnderdeel}")
    @Consumes({"application/xml", "application/json"})
    public Boolean addFactuurOnderdeel(@PathParam("factuuronderdel") FactuurOnderdeel factuurOnderdeel) {
        //Tweet t = new Tweet
        try {
            this.ira.addFactuurOnderdeel(factuurOnderdeel);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    @PUT
    @Path("addCartraker/{cartraker}")
    @Consumes({"application/xml", "application/json"})
    public Boolean addCartraker(@PathParam("cartraker") Cartracker cartracker) {
        //Tweet t = new Tweet
        try {
            this.ira.addCartraker(cartracker);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    @PUT
    @Path("modifyCartraker/{cartraker}/{id}")
    @Consumes({"application/xml", "application/json"})
    public Boolean modifyCartraker(@PathParam("cartraker") Cartracker cartracker,@PathParam("id") int nummer) {
        try {
            this.ira.modifyCartraker(nummer,cartracker);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    @PUT
    @Path("WijzigingBetaalStatus/{status}{id}")
    @Consumes({"application/xml", "application/json"})
    public Boolean WijzigingBetaalStatus(@PathParam("status") String status,@PathParam("id") int nummer) {
        //Tweet t = new Tweet
        try {
            this.ira.changeStatusFactuur(status, nummer);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    
}
