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
import service.IRekeningAdministratie;

/**
 *
 * @author kay de groot
 */
@Path("RekAdmin")
public class restRekeningAdministratie {

    IRekeningAdministratie ira = lookupRekeningAdministratieLocal();

//    @Inject
//    private IRekeningAdministratie ira;
    @GET
    @Path("getAllCars")
    @Produces("application/json")
    public String getAllCars() {
        List<Auto> autos = ira.getAutos(0);
        if (autos.size() < 0) {

        }
        autos.add(new Auto("testing1", null, "testing1", 5));
        autos.add(new Auto("testing2", null, "testing2", 3));
        return new Gson().toJson(autos);
    }

    @GET
    @Path("getAllFactuur")
    @Produces("application/json")
    public List<Factuur> getAllFactuur() {
        List<Factuur> factuur = ira.getAlleFacturen(0);
        factuur.add(new Factuur(null, 325, "Maart"));
        return factuur;
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
    @Path("addCartraker")
    @Consumes({"application/json"})
    public Boolean addCartraker(Auto auto) {
        try {
            if (auto.getEigenaar() != null) {
                List<Cartracker> cartrakers = this.ira.getCartraker();
                for (Cartracker cartraker : cartrakers) {
                    if (auto.getId() == cartraker.getAuto().getId()) {
                        cartraker.setAuto(auto);
                        this.ira.modifyCartraker(cartraker);
                         return true;
                    }
                }
                return false;
            } else {
                Eigenaar e = auto.getEigenaar();
                if (e == null) {
                    e = new Eigenaar("test", "test", "test");
                }
                Auto nieuweAuto = new Auto(auto.getKenteken(), e, auto.getVoertuig(), auto.getEersteKleur(), auto.getZitplaatsen());
                Cartracker tracker = new Cartracker(nieuweAuto);
                this.ira.addCartraker(tracker);
                return true;
            }
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
    public List<Kilometertarief> getAlleKilometerTarieven() {
        List<Kilometertarief> tarieven = ira.getAlleKilometerTarieven();
        return tarieven;
    }

    @GET
    @Path("KilometerTarieven/{id}")
    public Kilometertarief getKilometerTarief(@PathParam("id") int id) {
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
    public boolean deleteKilometerTarief(@PathParam("id") int id) {
        try {
            this.ira.deleteKilometerTarief(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private IRekeningAdministratie lookupRekeningAdministratieLocal() {
        try {
            Context c = new InitialContext();
            return (IRekeningAdministratie) c.lookup("java:global/Rekeningadministratie/RekeningAdministratie!service.IRekeningAdministratie");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
