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
import javax.ejb.Stateless;
import javax.inject.Inject;
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

    /**
     * Calls the init method from RekeningAdministratie zodat deze kan worden
     * aangemaakt worden.
     *
     * @return string die aangeeft of het is gelukt.
     */
    @GET
    @Path("init")
    @Produces("application/json")
    public String init() {
        return ira.init();
    }

    /**
     * Get's all the cars that are in the database and return them as a json
     * string
     *
     * @return json string.
     */
    @GET
    @Path("getAllCars")
    @Produces("application/json")
    public String getAllCars() {
        List<Auto> autos = ira.getAllAutos();
        return new Gson().toJson(autos);
    }

    /**
     * Get's all Eigenaars out of the database end return them as s json
     *
     * @return json string
     */
    @GET
    @Path("getAllEigenaars")
    @Produces("application/json")
    public List<Eigenaar> getAllEigenaars() {
        List<Eigenaar> eigenaars = ira.getAllEigenaars();
        return eigenaars;
    }

    /**
     * Get's all Cartrakcer out of the database end return them as s json
     *
     * @return json string
     */
    @GET
    @Path("getAllCartrackers")
    @Produces("application/json")
    public String getAllCartrackers() {
        List<Cartracker> cartrackers = ira.getCartrackers();
        return new Gson().toJson(cartrackers);
    }

    /**
     * Gets all facturen out of the database end return them as s json
     *
     * @return json string
     */
    @GET
    @Path("getAllFactuur")
    @Produces("application/json")
    public List<Factuur> getAllFactuur() {
        List<Factuur> facturen = ira.getAlleFacturen();
        return facturen;
    }

    /**
     * Gets the corresponding factuur of the id.
     *
     * @param id id of the factuur you want to get.
     * @return json string.
     */
    @GET
    @Path("Facturen/{id}")
    public Factuur getFactuur(@PathParam("id") int id) {
        Factuur factuur = ira.getFactuur(id);
        return factuur;
    }

    /**
     * sets the corresponding factuur's betaalstatus to 'Betaald'
     *
     * @param id id of the factuur that has to be changed to'Betaald'
     */
    @POST
    @Path("Facturen/{id}/Betaald")
    public void factuurBetaald(@PathParam("id") int id) {
        ira.factuurBetaald(id);
    }

    /**
     * adds a sent factuurOnderdeel to the database
     *
     * @param factuurOnderdeel the new factuurOnderdeel that had to be added
     * @return true if it worked, false if it didn't
     */
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

    /**
     * modify existing cartraker and corresponding auto's with the delivered
     * param
     *
     * @param cartracker the new version of the cartacker
     * @return true if it worked, false if it didn't
     */
    @POST
    @Path("modifyCartracker")
    @Consumes({"application/json"})
    public Boolean modifyCartracker(Cartracker cartracker) {
        System.out.println(cartracker.getId() + " : " + cartracker.getAuto().toString());
        try {
            this.ira.modifyCartracker(cartracker);
            this.ira.modifyAuto(cartracker.getAuto());
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * modify existing auto with the delivered param
     *
     * @param auto the new version of the auto
     * @return true if it worked, false if it didn't
     */
    @POST
    @Path("modifyAuto")
    @Consumes({"application/json"})
    public Boolean modifyAuto(Auto auto) {
        try {
            this.ira.modifyAuto(auto);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * modify existing eigenaar with the delivered param
     *
     * @param eigenaar the new version of the auto
     * @return true if it worked, false if it didn't.
     */
    @POST
    @Path("modifyEigenaar")
    @Consumes({"application/json"})
    public Boolean modifyEigenaar(Eigenaar eigenaar) {
        try {
            this.ira.modifyEigenaar(eigenaar);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Use the param to change the betaalStatus of the factuur
     *
     * @param factuur the new betaalStatus of the factuur
     * @return true if it worked, false if it didn't.
     */
    @PUT
    @Path("WijzigingBetaalStatus")
    @Consumes({"application/json"})
    public Boolean WijzigingBetaalStatus(Factuur factuur) {
        System.out.println("testing van dit " + factuur);
        try {
            this.ira.changeStatusFactuur(factuur.getBetaalStatus(), factuur.getNummer());
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Get's all KilometerTarieven out of the database end return them as s json
     *
     * @return json string
     */
    @GET
    @Path("KilometerTarieven/All")
    public String getAlleKilometerTarieven() {
        ArrayList<Kilometertarief> tarieven = new ArrayList(ira.getAlleKilometerTarieven());
        System.out.println(tarieven.size());
        for (Kilometertarief kt : tarieven) {
            System.out.println("Bedrag: " + kt.getBedrag());
            System.out.println("Tariefcategorie: " + kt.getTariefCategorie());
        }
        return new Gson().toJson(tarieven);
    }

    /**
     * Get th Kilometertarief with the same corresponding id as the one sent
     * with param
     *
     * @param id the id that has to bee the same as a excisting Kilometertarief
     * @return Kilometertarief
     */
    @GET
    @Path("KilometerTarieven/{id}")
    public Kilometertarief getKilometerTarief(@PathParam("id") int id) {
        Kilometertarief tarief = ira.getKilometerTarief(id);
        return tarief;
    }

    /**
     * Get th Eigenaar with the same corresponding id as the one sent with param
     *
     * @param id the id that has to bee the same as a excisting Eigenaar
     * @return Eigenaar
     */
    @GET
    @Path("Eigenaar/{id}")
    public Eigenaar getEigenaar(@PathParam("id") int id) {
        Eigenaar eigenaar = ira.getEigenaar(id);
        return eigenaar;
    }

    /**
     * Get th auto with the same corresponding id as the one sent with param
     *
     * @param id the id that has to bee the same as a excisting auto
     * @return auto
     */
    @GET
    @Path("Car/{id}")
    public Auto getCar(@PathParam("id") int id) {
        Auto auto = ira.getAuto(id);
        return auto;
    }

    /**
     * Get th Cartracker with the same corresponding id as the one sent with
     * param
     *
     * @param id the id that has to bee the same as a excisting Cartracker
     * @return Cartracker
     */
    @GET
    @Path("Cartracker/{id}")
    public Cartracker getCartracker(@PathParam("id") int id) {
        Cartracker cartracker = ira.getCartracker(id);
        return cartracker;
    }

    /**
     * adds a sent Kilometertarief to the database
     *
     * @param Kilometertarief the new Kilometertarief that had to be added
     * @return true if it worked, false if it didn't
     */
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

    /**
     * modify existing Kilometertarief with the delivered param
     *
     * @param Kilometertarief the new version of the cartacker
     * @return true if it worked, false if it didn't
     */
    @POST
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

    /**
     * remove excisting KilometerTarief corresponding with id
     *
     * @param id id of the KilometerTarieven that has to be temoved
     * @return
     */
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

    /**
     * SHow that paypall has worked and print this on the screen.
     * @param post data sent from the test
     */
    @POST
    @Path("PaypalCallback")
    @Consumes({"application/xml", "application/json"})
    public void paypalCallback(String post) {
        System.out.println("AAAAAH PAYPAL STUFF!!! " + post);
    }
}
