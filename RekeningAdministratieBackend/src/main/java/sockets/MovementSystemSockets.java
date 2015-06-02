/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private static final JsonParser parser = new JsonParser();
    
    public MovementSystemSockets(String uri) throws URISyntaxException{
        client = new WebSocketClient(new URI(uri)) {

            @Override
            public void onOpen(ServerHandshake sh) {
                System.out.println("opened connection");
            }

            @Override
            public void onMessage(String string) {
                System.out.println(" RECEIVED" + string);
                string += "";
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
    
    public void getAllMovement(Date start, Date end){
        
        JsonObject obj = new JsonObject();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        obj.addProperty("method", "GetAllMovement");
        obj.addProperty("start", df.format(start));
        obj.addProperty("end", df.format(end));
        if (client.getConnection().isOpen()){
            client.send(gson.toJson(obj));
        }
    }
    
    public void getMovementForUser(String cartrackerId, Date start, Date end){
        JsonObject obj = new JsonObject();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        obj.addProperty("method", "GetMovementForUser");
        obj.addProperty("cartrackerId", cartrackerId);
        obj.addProperty("start", df.format(start));
        obj.addProperty("end", df.format(end));
        client.send(gson.toJson(obj));
    }
}
