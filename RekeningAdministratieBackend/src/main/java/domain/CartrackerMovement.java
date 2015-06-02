/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Date;

/**
 *
 * @author Daan
 */
public class CartrackerMovement {
    
    private int movementId;
    private String cartrackerId;
    private double speed;
    private double position;
    private Date dateOfMovement;
    private int roadId;
    
    public CartrackerMovement(){
        //empty constructor
    }
    
    public CartrackerMovement(int movementId, String cartrackerId, double speed, double position, Date dateOfMovement, int roadId){
        this.movementId = movementId;
        this.cartrackerId = cartrackerId;
        this.speed = speed;
        this.position = position;
        this.dateOfMovement = dateOfMovement;
        this.roadId = roadId;
    }

    public int getMovementId(){
        return movementId;
    }
    
    public String getCartrackerId() {
        return cartrackerId;
    }

    public double getSpeed() {
        return speed;
    }

    public double getPosition() {
        return position;
    }

    public Date getDateOfMovement() {
        return dateOfMovement;
    }
    
    public int getRoadId(){
        return roadId;
    }

    public void setCartrackerId(String cartrackerId) {
        this.cartrackerId = cartrackerId;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setPosition(double position) {
        this.position = position;
    }

    public void setDateOfMovement(Date dateOfMovement) {
        this.dateOfMovement = dateOfMovement;
    }
    
    public void setRoadId(int roadId){
        this.roadId = roadId;
    }
}
