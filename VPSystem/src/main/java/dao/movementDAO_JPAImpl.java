/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.CartrackerMovement;
import domain.Request;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

/**
 *
 * @author Daan
 */

@Stateless
public class movementDAO_JPAImpl implements movementDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createRequest(Request request) {
        em.persist(request);
    }

    @Override
    public boolean requestAllowed(String requestedBy) {
        Date oneHourAgo = new Date(new Date().getTime() - 3600 * 1000);
        List<Request> requests = em.createQuery("SELECT r FROM Request r WHERE r.dateOfRequest > :Date")
                .setParameter("Date", oneHourAgo)
                .getResultList();
        
        if (requests.size() < 5 ){
            return true;
        }
        
        return false;
    }

    @Override
    public CartrackerMovement getCartrackerMovement(int id) {
        return em.find(CartrackerMovement.class, id);
    }

    @Override
    public void createCartrackerMovement(CartrackerMovement movement) {
        em.persist(movement);
    }

    @Override
    public void edit(CartrackerMovement movement) {
        em.merge(movement);
    }

    @Override
    public List<CartrackerMovement> getAllMovementsForId(int id) {
        List<CartrackerMovement> movements = em.createQuery("SELECT u FROM CARTRACKERMOVEMENT u WHERE u.CARTRACKERID = :Id")
                .setParameter("Id", id)
                .getResultList();
        return movements;
    }

    @Override
    public List<CartrackerMovement> getAllMovements(Date start, Date end) {
        List<CartrackerMovement> movements = em.createQuery("SELECT u FROM CARTRACKERMOVEMENT u WHERE u.DATEOFMOVEMENT BETWEEN :start AND :end")
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList();
        return movements;
    }

    @Override
    public List<CartrackerMovement> getAllMovementsForUser(String cartrackerId, Date start, Date end) {
                List<CartrackerMovement> movements = em.createQuery("SELECT u FROM CARTRACKERMOVEMENT u WHERE u.CARTRACKERID = :id AND u.DATEOFMOVEMENT BETWEEN :start AND :end")
                .setParameter("id", cartrackerId)
                .setParameter("start", start, TemporalType.DATE)
                .setParameter("end", end, TemporalType.DATE)
                .getResultList();
        return movements;
    }

    @Override
    public CartrackerMovement getLastMovement(String id) {
        CartrackerMovement m = (CartrackerMovement) em.createQuery("SELECT MAX(u.CARTRACKERID) FROM CARTRACKERMOVEMENT u WHERE u.CARTRACKERID = :Id")
            .setParameter("Id", id)
            .getSingleResult();
        return m;
    }
}
