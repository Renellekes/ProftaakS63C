/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Auto;
import domain.Cartracker;
import domain.Eigenaar;
import domain.Factuur;
import domain.FactuurOnderdeel;
import domain.Kilometertarief;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kay de groot
 */
@Singleton
@Startup
public class DatabaseManager {

    //HIER MOETEN WE ECHT WAT AAN DOEN! CENTRALE DB ERGENS?
    @PersistenceContext(unitName = "MooieUnit")

    private EntityManager em;

    @PostConstruct
    private void init() {


        //this.addKilometerTarief(new Kilometertarief("testregio", "Stads", 4522));
    }     

    public DatabaseManager() {
    }

    public List<Cartracker> findAllCartracker() {
        Query query = em.createQuery("SELECT c FROM Cartracker c");
        List<Cartracker> cartrackers = query.getResultList();
        return cartrackers;
    }

    public List<FactuurOnderdeel> findOnderdelenForMonth(String Maand) {
        Query query = em.createQuery("SELECT c FROM FactuurOnderdeel c WHERE c.maand = '" + Maand + "'");
        List<FactuurOnderdeel> facturen = query.getResultList();
        for (FactuurOnderdeel fo : facturen) {
            fo.setMonth();
        }
        return facturen;
    }

    public void addFactuur(Factuur factuur) {
        em.persist(factuur);
    }

    public void mergeCartracker(Cartracker c) {
        em.merge(c);
    }

    public void addOnderdeel(FactuurOnderdeel fo) {
        em.persist(fo);
    }

    public Factuur findFactuurWithID(int nummer) {
        Query query = em.createQuery("SELECT c FROM Factuur c WHERE c.nummer = " + nummer);
        List<Factuur> facturen = query.getResultList();
        if (facturen.size() > 0) {
            return facturen.get(0);
        } else {
            return null;
        }
    }

    public void mergeFactuur(Factuur factuur) {
        em.merge(factuur);
    }

    public void addCartracker(Cartracker cartracker) {
        System.out.println(cartracker);
        em.persist(cartracker);
    }

    public Cartracker findCartrackerWithId(int nummer) {
        Query query = em.createQuery("SELECT c FROM Cartracker c WHERE c.id = " + nummer);
        List<Cartracker> cartrackers = query.getResultList();
        if (cartrackers.size() > 0) {
            return cartrackers.get(0);
        } else {
            return null;
        }
    }

    public List<Auto> getAllAutos() {
        Query query = em.createQuery("SELECT c FROM Auto c ");//"SELECT c FROM Auto c WHERE c.id = " + i
        List<Auto> autos = query.getResultList();
        return autos;
    }

    public List<Auto> getAuto(int i) {
        Query query = em.createQuery("SELECT c FROM Auto c WHERE c.id = " + i);
        List<Auto> autos = query.getResultList();
        return autos;
    }

    public List<Kilometertarief> getAlleKilometerTarieven() {
        Query query = em.createQuery("SELECT c FROM Kilometertarief c");
        List<Kilometertarief> tarieven = query.getResultList();
        return tarieven;
    }

    public Kilometertarief getKilometerTarief(int id) {
        Query query = em.createQuery("SELECT c FROM Kilometertarief c WHERE c.id = " + id);
        List<Kilometertarief> tarieven = query.getResultList();
        if (tarieven.size() > 0) {
            return tarieven.get(0);
        } else {
            return null;
        }
    }

    public void addKilometerTarief(Kilometertarief kt) {
        em.persist(kt);
    }

    public void editKilometerTarief(Kilometertarief kt) {
        em.merge(kt);
    }

    public void deleteKilometerTarief(int id) {
        em.remove(id);
    }

    public List<Factuur> getAlleFacturen() {
        Query query = em.createQuery("SELECT c FROM Factuur c");
        List<Factuur> factuurs = query.getResultList();
        return factuurs;
    }

    public void addAuto(Auto nieuweAuto) {
        em.persist(nieuweAuto);
    }

    public List<Cartracker> getCartracker() {
        Query query = em.createQuery("SELECT c FROM Cartracker c");
        List<Cartracker> c = query.getResultList();
        return c;
    }

    public void modifyAuto(Auto a) {
        em.merge(a);

    }

    public Eigenaar getEigenaar(int id) {
        Query query = em.createQuery("SELECT c FROM Eigenaar c WHERE c.id = " + id);
        List<Eigenaar> eigenaar = query.getResultList();
        if (eigenaar.size() > 0) {
            return eigenaar.get(0);
        } else {
            return null;
        }
    }

    public void mergeAuto(Auto auto) {
        em.merge(auto);
    }

    public List<Eigenaar> getAllEigenaars() {
        Query query = em.createQuery("SELECT c FROM Eigenaar c");
        List<Eigenaar> c = query.getResultList();
        return c;
    }

}
