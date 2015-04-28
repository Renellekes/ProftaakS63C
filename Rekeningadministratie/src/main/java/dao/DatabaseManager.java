/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Auto;
import domain.Cartracker;
import domain.Factuur;
import domain.FactuurOnderdeel;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

/**
 *
 * @author kay de groot
 */
@Stateless
public class DatabaseManager implements IDataDistributer {

    @PersistenceContext(unitName = "com.kaydegroot_Rekeningadministratie_war_1.0PU")
    private EntityManager em;

    public DatabaseManager() {
    }

    @Override
    public List<Cartracker> findAllCartraker() {
        Query query = em.createQuery("SELECT c FROM Cartracker c");
        List<Cartracker> cartrackers = query.getResultList();
        return cartrackers;
    }

    @Override
    public List<FactuurOnderdeel> findOnderdelenForMonth(String Maand) {
        Query query = em.createQuery("SELECT c FROM FactuurOnderdeel c WHERE c.MAAND = " + Maand);
        List<Factuur> facturen =query.getResultList();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addFactuur(Factuur factuur) {
        em.persist(factuur);
    }

    @Override
    public void mergeCartraker(Cartracker c) {
        em.merge(c);
    }

    @Override
    public void addOnderdeel(FactuurOnderdeel fo) {
        em.persist(fo);
    }

    @Override
    public Factuur findFactuurWithID(int nummer) {
        Query query = em.createQuery("SELECT c FROM Factuur c WHERE c.nummer = " + nummer);
        List<Factuur> facturen =query.getResultList();
        if (facturen.size() >0){
            return facturen.get(0);
        }else{
            return null;
        }
    }

    @Override
    public void mergeFactuur(Factuur factuur) {
        em.merge(factuur);
    }

    @Override
    public void addCartraker(Cartracker cartracker) {
        em.persist(cartracker);
    }

    @Override
    public Cartracker findCartrakerWithId(int nummer) {
        Query query = em.createQuery("SELECT c FROM Factuur c WHERE c.nummer = " + nummer);
        List<Cartracker> cartrackers =query.getResultList();
        if (cartrackers.size() >0){
            return cartrackers.get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<Auto> getAutos(int i) {
        Query query = em.createQuery("SELECT c FROM Auto c WHERE c.id = " + i);
        List<Auto> autos = query.getResultList();
        return autos;
    }

}
