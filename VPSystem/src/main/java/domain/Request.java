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
@Table(name="RequestLog")
public class Request implements Serializable {
    
    @Id
    @GeneratedValue
    private int id;
    
    @Column
    private int cartrackerId;
    
    @Column
    private String requestedBy;
    
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dateOfRequest;
    
    public Request(){
        //empty constructor
    }
    
    public Request(String requestedBy){
        this.requestedBy = requestedBy;
        this.dateOfRequest = new Date();
    }
    
    public Request(int cartrackerId, String requestedBy){
        this.cartrackerId = cartrackerId;
        this.requestedBy = requestedBy;
        this.dateOfRequest = new Date();
    }

    public int getId() {
        return id;
    }

    public int getCartrackerId() {
        return cartrackerId;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public Date getDateOfRequest() {
        return dateOfRequest;
    }
}
