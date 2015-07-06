/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import domain.CartrackerMovement;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

/**
 *
 * @author Daan
 */

public class MovementSystemSockets {
    
    private WebSocketClient client;
    
    private static final Gson gson = new Gson();
    private static int callId = 1;
    private static int receivingFor = -1;
    private static HashMap<Integer, List<CartrackerMovement>> receivedValues = new HashMap<>();
    private static HashMap<Integer, Boolean> areValuesReceived = new HashMap<>();
    
    private static MovementSystemSockets instance;
            
    public MovementSystemSockets(String uri) throws URISyntaxException{
        client = new WebSocketClient(new URI(uri)) {

            @Override
            public void onOpen(ServerHandshake sh) {
                System.out.println("opened connection");
            }

            @Override
            public void onMessage(String string) {
                if (string.contains("__start")){
                    receivingFor = Integer.parseInt(string.substring(8));
                    return;
                }
                if (string.contains("__end")){
                    areValuesReceived.put(receivingFor, Boolean.TRUE);
                    receivingFor = -1;
                    return;
                }
                
                CartrackerMovement m = gson.fromJson(string, CartrackerMovement.class);
                receivedValues.get(receivingFor).add(m);
                
            }

            @Override
            public void onClose(int i, String string, boolean bln) {
                System.out.println("closed connection");
            }

            @Override
            public void onError(Exception excptn) {
                excptn.printStackTrace();
            }
        };
        
        client.connect();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(MovementSystemSockets.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getAllMovement(Date start, Date end){
        
        JsonObject obj = new JsonObject();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        obj.addProperty("method", "GetAllMovement");
        obj.addProperty("callId", callId);
        obj.addProperty("start", df.format(start));
        obj.addProperty("end", df.format(end));
        if (client.getConnection().isOpen()){
            receivedValues.put(callId, new ArrayList<CartrackerMovement>());
            areValuesReceived.put(callId, Boolean.FALSE);
            callId++;
            client.send(gson.toJson(obj));
            return callId -1;
        }
        
        return -1;
    }
    
    public int getMovementForUser(String cartrackerId, Date start, Date end){
        JsonObject obj = new JsonObject();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        obj.addProperty("method", "GetMovementForUser");
        obj.addProperty("callId", callId);
        obj.addProperty("cartrackerId", cartrackerId);
        obj.addProperty("start", df.format(start));
        obj.addProperty("end", df.format(end));
        if (client.getConnection().isOpen()){
            receivedValues.put(callId, new ArrayList<CartrackerMovement>());
            areValuesReceived.put(callId, Boolean.FALSE);
            callId++;
            client.send(gson.toJson(obj));
            return callId - 1;
        }
        
        return -1;
    }
    
    public List<CartrackerMovement> getCartrackersForCallId(int callId){
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){

            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MovementSystemSockets.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, 2000);
        return receivedValues.get(callId);
    }
    
    public boolean isCallIdReady(int callId){
        return areValuesReceived.get(callId);
    }
    
    public static MovementSystemSockets getInstance(){
        if (instance == null){
            try {
                instance = new MovementSystemSockets("http://localhost:7071/VerplaatsingenSysteem/MovementSystemEndpoint");
            } catch (URISyntaxException ex) {
                Logger.getLogger(MovementSystemSockets.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return instance;
    }
}
