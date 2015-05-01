/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Daan
 */

@ServerEndpoint(value = "/MovementSystemEndpoint")
public class MovementSystemEndpoint {
    
    @Inject
    MovementSystemService service;
    
    private static final HashMap<String, Session> clients = new HashMap<String, Session>();
    private static final Logger LOG = Logger.getLogger(MovementSystemEndpoint.class.getName());
    private static final JsonParser parser = new JsonParser();
   
    
    @OnOpen
    public void OnOpen(Session client){
        clients.put(client.getId(), client);
    }
    
    @OnClose
    public void OnClose(Session client){
        clients.remove(client.getId());
    }
    
    @OnMessage
    public void OnMessage(final Session client, final String message){
        JsonObject request = parser.parse(message).getAsJsonObject();
        
        switch(request.get("method").getAsString()){
            case "GetAllMovement" :
                {
                    try {
                        service.GetAllMovement(client, new SimpleDateFormat("yyyy-MM-dd").parse(request.get("start").getAsString()), new SimpleDateFormat("yyyy-MM-dd").parse(request.get("end").getAsString()));
                    } catch (ParseException ex) {
                        Logger.getLogger(MovementSystemEndpoint.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                
            case "GetMovementForUser" :
                {
                    try {
                        service.GetMovementForUser(client, request.get("cartrackerId").getAsInt(),new SimpleDateFormat("yyyy-MM-dd").parse(request.get("start").getAsString()), new SimpleDateFormat("yyyy-MM-dd").parse(request.get("end").getAsString()));
                    } catch (ParseException ex) {
                        Logger.getLogger(MovementSystemEndpoint.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                
            case "GetLastMovementForUser" :
                service.GetLastMovementForUser(client, request.get("cartrackerId").getAsInt());
                break;
            
            case "SendData" :
                service.ProcessData(request.get("cartrackerId").getAsInt(), request.get("XML").getAsString());
                break;
        }
        
    }
    
    @OnError
    public void OnError(Throwable t){
        LOG.log(Level.SEVERE, t.getMessage());
    }
}
