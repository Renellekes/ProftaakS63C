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
    
    @Id @GeneratedValue
    private int movementId;
    
    @Column
    private int cartrackerId;
    
    @Column
    private int speed;
    
    @Column
    private int position;
    
    @Temporal(value = TemporalType.DATE)
    private Date dateOfMovement;

    public CartrackerMovement() {
    }
    
    public CartrackerMovement(int cartrackerId, int speed, int position, Date dateOfMovement){
        this.cartrackerId = cartrackerId;
        this.speed = speed;
        this.position = position;
        this.dateOfMovement = dateOfMovement;        
    }
    
    public int GetCartrackerId(){
        return cartrackerId;
    }
    
    public int GetSpeed(){
        return speed;
    }
    
    public int GetPosition(){
        return position;
    }
    
    public Date GetDateOfMovement(){
        return dateOfMovement;
    }
    
    public void SetCartrackerId(int cartrackerId){
        this.cartrackerId = cartrackerId;
    }
    
    public void SetSpeed(int speed){
        this.speed = speed;
    }
    
    public void SetPosition(int position){
        this.position = position;
    }
    
    public void SetDateOfMovement(Date dateOfMovement){
        this.dateOfMovement = dateOfMovement;
    }
}
