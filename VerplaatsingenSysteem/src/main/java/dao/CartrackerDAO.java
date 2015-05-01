/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.CartrackerMovement;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Daan
 */
public interface CartrackerDAO {
    
    CartrackerMovement GetCartrackerMovement(int id);
    void Create(CartrackerMovement movement);
    List<CartrackerMovement> GetAllMovementForId(int id);
    void Edit(CartrackerMovement movement);
    CartrackerMovement GetLastMovement(int id);
    List<CartrackerMovement> GetAllMovement(Date start,Date end);
    List<CartrackerMovement> GetAllMovementForUser(int cartrackerId, Date start, Date end);
}
