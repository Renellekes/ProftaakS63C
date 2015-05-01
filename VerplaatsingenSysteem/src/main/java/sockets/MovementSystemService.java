/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import com.google.gson.Gson;
import dao.CartrackerDAO;
import dao.RequestDAO;
import domain.CartrackerMovement;
import domain.Request;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.websocket.Session;

/**
 *
 * @author Daan
 */

@Stateless
public class MovementSystemService {
    
    @Inject
    private CartrackerDAO cartrackerDAO;
    
    @Inject
    private RequestDAO requestDAO;
    
    private static final Logger LOG = Logger.getLogger(MovementSystemService.class.getName());
    private static final Gson gson = new Gson();
    
    private void SendMessage(Session client, Object message){
        try{
            if (client.isOpen()){
                client.getAsyncRemote().sendObject(message);
            }
        }
        catch(Throwable t){
            LOG.log(Level.SEVERE, t.getMessage());
        }
    }
    
    public void GetAllMovement(Session client, Date start, Date end){
        List<CartrackerMovement> movements = cartrackerDAO.GetAllMovement(start, end);
        SendMessage(client, gson.toJson(movements));
    }
    
    public void GetMovementForUser(Session client, int cartrackerID, Date start, Date end){
        List<CartrackerMovement> movements = cartrackerDAO.GetAllMovementForUser(cartrackerID, start, end); 
        SendMessage(client, gson.toJson(movements));
    }
    
    public void GetLastMovementForUser(Session client, int cartrackerId){
        CartrackerMovement m = cartrackerDAO.GetLastMovement(cartrackerId);
        SendMessage(client, gson.toJson(m));
    }
    
    public void ProcessData(int cartrackerId, String XML){
        //todo
    }
    
    public void RegisterRequest(Session client){
        //todo : create appropriate request
        Request r = new Request(client.getId(),client.getRequestURI().toString());
        requestDAO.Create(r);
    }
}
