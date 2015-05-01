/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.rest;

import com.google.gson.Gson;
import domain.Auto;
import domain.Cartracker;
import domain.Eigenaar;
import domain.FactuurOnderdeel;
import domain.Kilometertarief;
import domain.RestTest;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
    
    @GET
    @Path("getAllCars")
    @Produces("application/xml,application/json")
    public List<Auto> getAllCars(){        
        List<Auto> autos = ira.getAutos(0);
        return autos;
    }
    
    
    @PUT
    @Path("addFactuurOnderdeel")
    @Consumes({"application/xml", "application/json"})
    public Boolean addFactuurOnderdeel(FactuurOnderdeel factuurOnderdeel) {
        try {
            this.ira.addFactuurOnderdeel(factuurOnderdeel);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    @GET
    @Path("/")
    @Produces("application/json")
    public RestTest findAll() {
        return new RestTest("a","b","c","d");
    }
    
    @POST
    @Path("addCartraker")
    @Consumes({"application/xml", "application/json"})
    public Boolean addCartraker(Cartracker cartracker) {
        try {
            this.ira.addCartraker(cartracker);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    @PUT
    @Path("modifyCartraker")
    @Consumes({"application/xml", "application/json"})
    public Boolean modifyCartraker(Cartracker cartracker) {
        try {
            this.ira.modifyCartraker(cartracker);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
//    @PUT
//    @Path("WijzigingBetaalStatus/{status}{id}")
//    @Consumes({"application/xml", "application/json"})
//    public Boolean WijzigingBetaalStatus(@PathParam("status") String status,@PathParam("id") int nummer) {
//        //Tweet t = new Tweet
//        try {
//            this.ira.changeStatusFactuur(status, nummer);
//            return true;
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return false;
//        }
//    }
    
    @GET
    @Path("KilometerTarieven/All")
    @Produces("application/json")
    public List<Kilometertarief> getAlleKilometerTarieven(){
        ira.addKilometerTarief(new Kilometertarief("testregio", "Stads", 452));
        List<Kilometertarief> tarieven =  ira.getAlleKilometerTarieven();
        System.out.println(tarieven.size());
        for (Kilometertarief kt : tarieven){
            System.out.println("Bedrag: " + kt.getBedrag());
            System.out.println("Tariefcategorie: " + kt.getTariefCategorie());
        }
        return tarieven;
    }
    
    @GET
    @Path("KilometerTarieven/{id}")
    @Produces("application/xml,application/json")
    public Kilometertarief getKilometerTarief(@PathParam("id") int id){
        Kilometertarief tarief = ira.getKilometerTarief(id);
        return tarief;
    }
    
    @PUT
    @Path("KilometerTarieven/add")
    @Consumes({"application/xml", "application/json"})
    public Boolean addKilometerTarief(Kilometertarief kt) {
        try {
            this.ira.addKilometerTarief(kt);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
 
    @PUT
    @Path("KilometerTarieven/edit")
    @Consumes({"application/xml", "application/json"})
    public Boolean editKilometerTarief(Kilometertarief kt) {
        try {
            this.ira.editKilometerTarief(kt);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    @DELETE
    @Path("KilometerTarieven/{id}")
    public boolean deleteKilometerTarief(@PathParam("id") int id){
        try {
            this.ira.deleteKilometerTarief(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
