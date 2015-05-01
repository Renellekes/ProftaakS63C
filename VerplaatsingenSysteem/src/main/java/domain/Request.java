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
    
    @Temporal(value = TemporalType.DATE)
    private Date dateOfRequest;
    
    @Column
    private int cartrackerId;
    
    @Column
    private String requestedBy;
    
    @Column(name="System")
    private String requestedViaSystem;
    
    public Request(){
    }
    
    public Request(String requestedBy, String requestedViaSystem){
        this.dateOfRequest = new Date();
        this.cartrackerId = -1;
        this.requestedBy = requestedBy;
        this.requestedViaSystem = requestedViaSystem;
    }
    
    public Request(int cartrackerId, String requestedBy, String requestedViaSystem){
        this.dateOfRequest = new Date();
        this.cartrackerId = cartrackerId;
        this.requestedBy = requestedBy;
        this.requestedViaSystem = requestedViaSystem;
    }

    public int GetId() {
        return id;
    }

    public Date GetDate() {
        return dateOfRequest;
    }

    public int GetCartrackerId() {
        return cartrackerId;
    }

    public String GetRequestedBy() {
        return requestedBy;
    }  
    
    public String GetRequestedViaSystem(){
        return requestedViaSystem;
    }
}
