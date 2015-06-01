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
import domain.Factuur;
import domain.FactuurOnderdeel;
import domain.Kilometertarief;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import service.RekeningAdministratie;

/**
 *
 * @author kay de groot
 */
@Path("RekAdmin")
@Stateless
public class restRekeningAdministratie {

    //IRekeningAdministratie ira = lookupRekeningAdministratieLocal();

    @Inject
    RekeningAdministratie ira;
    
    @GET
    @Path("getAllCars")
    @Produces("application/json")
    public String getAllCars() {
        List<Auto> autos = ira.getAllAutos();
        Auto a = new Auto("testing1", null, "testing1", 5);
        a.setId(88);
        autos.add(a);
        return new Gson().toJson(autos);
    }
    
    @GET
    @Path("getAllCartracker")
    @Produces("application/json")
    public String getAllCartracker() {
        List<Cartracker> cartrackers = ira.getCartracker();
        return new Gson().toJson(cartrackers);
    }

    @GET
    @Path("getAllFactuur")
    @Produces("application/json")
    public List<Factuur> getAllFactuur() {
        List<Factuur> facturen = ira.getAlleFacturen();
        Factuur factuur= new Factuur(5, 325, "Maart");
        factuur.setBetaalStatus("Open");
        facturen.add(factuur);
        return facturen;
    }

    @PUT
    @Path("addFactuurOnderdeel")
    @Consumes({"application/xml", "application/json"})
    public Boolean addFactuurOnderdeel(FactuurOnderdeel factuurOnderdeel) {
        //Tweet t = new Tweet
        try {
            this.ira.addFactuurOnderdeel(factuurOnderdeel);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @POST
    @Path("addCartracker")
    @Consumes({"application/json"})
    public Boolean addCartraker(Auto auto) {
        System.out.println(auto.toString());
        try {
            if (auto.getEigenaar() != null) {
                List<Cartracker> cartrackers = this.ira.getCartracker();
                for (Cartracker cartracker : cartrackers) {
                    if (auto.getId() == cartracker.getAuto().getId()) {
                        cartracker.setAuto(auto);
                        this.ira.modifyCartracker(cartracker);
                         return true;
                    }
                }
                return false;
            } else {
                Eigenaar e = auto.getEigenaar();
                if (e == null) {
                    e = new Eigenaar("test", "test", "test");
                }
                auto.setEigenaar(e);
                Cartracker tracker = new Cartracker(auto);
                this.ira.addCartracker(tracker);
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @POST
    @Path("modifyCartraker")
    @Consumes({"application/json"})
    public Boolean modifyCartraker(Cartracker cartracker) {
        System.out.println(cartracker.getId() +  " : " + cartracker.getAuto().toString());
        try {
            this.ira.modifyCartracker(cartracker);
            this.ira.modifyAuto(cartracker.getAuto());
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @PUT
    @Path("WijzigingBetaalStatus")
    @Consumes({"application/json"})
    public Boolean WijzigingBetaalStatus(Factuur factuur) {
        System.out.println("testing van dit " + factuur);
        try {
            //this.ira.changeStatusFactuur(status, nummer);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @GET
    @Path("KilometerTarieven/All")
    public String getAlleKilometerTarieven(){
        ArrayList<Kilometertarief> tarieven = new ArrayList(ira.getAlleKilometerTarieven());
        System.out.println(tarieven.size());
        for (Kilometertarief kt : tarieven){
            System.out.println("Bedrag: " + kt.getBedrag());
            System.out.println("Tariefcategorie: " + kt.getTariefCategorie());
        }
        return new Gson().toJson(tarieven);
    }

    @GET
    @Path("KilometerTarieven/{id}")
    public Kilometertarief getKilometerTarief(@PathParam("id") int id) {
        Kilometertarief tarief = ira.getKilometerTarief(id);
        return tarief;
    }
    
    @GET
    @Path("Eigenaar/{id}")
    public Eigenaar getEigenaar(@PathParam("id") int id) {
        Eigenaar eigenaar = ira.getEigenaar(id);
        return eigenaar;
    }

    @POST
    @Path("KilometerTarieven/Add")
    @Consumes({"application/json"})
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
    @Path("KilometerTarieven/Edit")
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
    public boolean deleteKilometerTarief(@PathParam("id") int id) {
        try {
            this.ira.deleteKilometerTarief(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
