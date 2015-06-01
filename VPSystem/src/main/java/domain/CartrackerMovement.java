/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Daan
 */
@Entity
@Table(name="CartrackerMovement")
public class CartrackerMovement implements Serializable {
    
    @Id
    @GeneratedValue
    private int movementId;
    
    @Column
    private String cartrackerId;
    
    @Column
    private double speed;
    
    @Column
    private double position;
    
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dateOfMovement;
    
    @Column
    private int roadId;
    
    public CartrackerMovement(){
        //empty constructor
    }
    
    public CartrackerMovement(String cartrackerId, int speed, int position, Date dateOfMovement, int roadId){
        this.cartrackerId = cartrackerId;
        this.speed = speed;
        this.position = position;
        this.dateOfMovement = dateOfMovement;
        this.roadId = roadId;
    }

    public int getMovementId() {
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

    public void setMovementId(int movementId) {
        this.movementId = movementId;
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
