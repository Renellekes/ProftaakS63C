/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import com.google.gson.Gson;
import dao.movementDAO;
import domain.CartrackerMovement;
import domain.Request;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import xml.XMLReader;

/**
 *
 * @author Daan
 */
@ServerEndpoint("/MovementSystemEndpoint")
public class MovementSystemEndpoint {
    
    @Inject
    private movementDAO movementdao;
    
    @OnMessage
    public void onMessage(Session client, String message) {
        if (registerRequest(client)){
            JsonReader jsonreader = Json.createReader(new StringReader(message));
            JsonObject request = jsonreader.readObject();
            switch(request.get("method").toString()){
                case "GetAllMovement":
                    try {
                            GetAllMovement(client, new SimpleDateFormat("yyyy-MM-dd").parse(request.get("start").toString()), new SimpleDateFormat("yyyy-MM-dd").parse(request.get("end").toString()), request.getInt("callId"));
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                    break;
                case "GetMovementForUser":
                    try {
                            GetMovementForUser(client, request.get("cartrackerId").toString(),new SimpleDateFormat("yyyy-MM-dd").parse(request.get("start").toString()), new SimpleDateFormat("yyyy-MM-dd").parse(request.get("end").toString()), request.getInt("callId"));
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                    break;
                case "GetLastMovementForUser":
                    GetLastMovementForUser(client, request.get("cartrackerId").toString());
                    break;
                case "SendData":
                    ProcessData(request.get("authorisation").toString(), request.get("serialnumber").toString(), request.get("XML").toString());
                    break;
            }
        }
    }
    
    @OnOpen
    public void onOpen(Session peer){
        
    }
    
    @OnClose
    public void onClose(Session peer){
        
    }
    
    @OnError
    public void OnError(Throwable t){
        t.printStackTrace();
    }
    
    public boolean registerRequest(Session client){
        if (movementdao.requestAllowed(client.getId())){
            Request r = new Request(client.getId());
            movementdao.createRequest(r);
            return true;
        }
        
        return false;
    }
    
    public void ProcessData(String authorisation, String serialnumber, String xml){
        XMLReader r = new XMLReader();
        for (CartrackerMovement m : r.readXML(xml)){
            movementdao.createCartrackerMovement(m);
        }
    }
    
    public void GetAllMovement(Session client, Date start, Date end, int callId){
        List<CartrackerMovement> movements = movementdao.getAllMovements(start, end);
        Gson gson = new Gson();
        sendMessage(client, "__start " + callId);
        for (CartrackerMovement m : movements){
            sendMessage(client, gson.toJson(m));
        }
        sendMessage(client, "__end");
    }
    
    public void GetMovementForUser(Session client, String cartrackerId, Date start, Date end, int callId){
        List<CartrackerMovement> movements = movementdao.getAllMovementsForUser(cartrackerId, start, end); 
        Gson gson = new Gson();
        sendMessage(client, "__start " + callId);
        for (CartrackerMovement m : movements){
            sendMessage(client, gson.toJson(m));
        }
        sendMessage(client, "__end");
    }
    
    @Deprecated
    public void GetLastMovementForUser(Session client, String cartrackerId){
        CartrackerMovement m = movementdao.getLastMovement(cartrackerId);
        String json = "";
        JsonArrayBuilder builder = Json.createArrayBuilder();
        builder.add(m.getMovementId());
        builder.add(m.getCartrackerId());
        builder.add(m.getSpeed());
        builder.add(m.getPosition());
        builder.add(m.getDateOfMovement().toString());
        json += builder.build();
        sendMessage(client, json);
    }
    
    public void sendMessage(Session client, String message){
        try{
            if (client.isOpen()){
                client.getAsyncRemote().sendObject(message);
            }
        }
        catch(Throwable t){
            t.printStackTrace();
        }
    }
}
