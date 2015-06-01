/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Daan
 */
@Entity
@Table(name="Road")
public class Road implements Serializable{
    
    @Id
    @GeneratedValue
    private int id;
    
    @Column
    private String name;
    
    @Column
    private int roadLength;
    
    @Column
    private int maxSpeed;
    
    @Column
    private double startX;
    
    @Column
    private double startY;
    
    @Column
    private double endX;
    
    @Column
    private double endY;
    
    @ManyToMany
    private List<Road> connectedTo;
    
    public Road(){
        //empty constructor
    }

    public Road(String name, int roadLength, int maxSpeed, double startX, double startY, double endX, double endY, List<Road> connectedTo) {
        this.name = name;
        this.roadLength = roadLength;
        this.maxSpeed = maxSpeed;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.connectedTo = connectedTo;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRoadLength() {
        return roadLength;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public double getStartX() {
        return startX;
    }

    public double getStartY() {
        return startY;
    }

    public double getEndX() {
        return endX;
    }

    public double getEndY() {
        return endY;
    }
    
    public List<Road> getConnectedTo(){
        return connectedTo;
    }
    
    public void addConnection(Road road){
        this.connectedTo.add(road);
    }
}
