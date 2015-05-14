/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Request;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Daan
 */

@Stateless
public class RequestDAO_JPAImpl implements RequestDAO{

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void Create(Request request) {
        em.persist(request);
    }
    
}
