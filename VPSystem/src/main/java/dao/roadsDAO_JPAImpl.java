/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Road;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Daan
 */

@Singleton
@Startup
public class roadsDAO_JPAImpl implements roadsDAO {
    
    @PersistenceContext
    private EntityManager em;
    
    @PostConstruct
    private void initRoads(){
        Road r = new Road("Test",1000,100,3.1,4.2,5.3,6.4, new ArrayList<Road>());
        create(r);
    }

    @Override
    public void create(Road r) {
        em.persist(r);
    }

    @Override
    public List<Road> getAllRoads() {
        return em.createQuery("SELECT r FROM Road r").getResultList();
    }
    
    
}
