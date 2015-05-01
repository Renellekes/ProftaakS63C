/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.CartrackerMovement;
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
public class CartrackerDAO_JPAImpl implements CartrackerDAO {

    @PersistenceContext
    private EntityManager em; 
    
    @Override
    public CartrackerMovement GetCartrackerMovement(int id) {
        return em.find(CartrackerMovement.class, id);
    }

    @Override
    public void Create(CartrackerMovement movement) {
        em.persist(movement);
    }

    @Override
    public List<CartrackerMovement> GetAllMovementForId(int id) {
        List<CartrackerMovement> movements = em.createQuery("SELECT u FROM CARTRACKERMOVEMENT u WHERE u.CARTRACKERID = " + id).getResultList();
        return movements;
    }

    @Override
    public void Edit(CartrackerMovement movement) {
        em.merge(movement);
    }

    @Override
    public CartrackerMovement GetLastMovement(int id) {
        CartrackerMovement m = (CartrackerMovement) em.createQuery("SELECT MAX(u.CARTRACKERID) FROM CARTRACKERMOVEMENT u WHERE u.CARTRACKERID = " + id).getSingleResult();
        return m;
    }

    @Override
    public List<CartrackerMovement> GetAllMovement(Date start, Date end) {
        List<CartrackerMovement> movements = em.createQuery("SELECT u FROM CARTRACKERMOVEMENT u WHERE u.DATEOFMOVEMENT BETWEEN :start AND :end")
                .setParameter("start", start, TemporalType.DATE)
                .setParameter("end", end, TemporalType.DATE)
                .getResultList();
        return movements;
    }

    @Override
    public List<CartrackerMovement> GetAllMovementForUser(int cartrackerId, Date start, Date end) {
        List<CartrackerMovement> movements = em.createQuery("SELECT u FROM CARTRACKERMOVEMENT u WHERE u.CARTRACKERID = :id AND u.DATEOFMOVEMENT BETWEEN :start AND :end")
                .setParameter("id", cartrackerId)
                .setParameter("start", start, TemporalType.DATE)
                .setParameter("end", end, TemporalType.DATE)
                .getResultList();
        return movements;
    }
    
}
