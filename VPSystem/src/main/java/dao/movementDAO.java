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

/**
 *
 * @author Daan
 */
public interface movementDAO {
    
    //requests
    void createRequest(Request request);
    boolean requestAllowed(String requestedBy);
    
    //cartracker
    CartrackerMovement getCartrackerMovement(int id);
    CartrackerMovement getLastMovement(String id);
    void createCartrackerMovement(CartrackerMovement movement);
    void edit(CartrackerMovement movement);
    List<CartrackerMovement> getAllMovementsForId(int id);
    List<CartrackerMovement> getAllMovements(Date start, Date end);
    List<CartrackerMovement> getAllMovementsForUser(String cartrackerId, Date start, Date end);
    
}
