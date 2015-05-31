/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author kay de groot
 */
@Entity
@Table(name = "Factuur")
public class Factuur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int nummer;
    private int cartrakerID;
    @Column
    private double totaalBedrag;
    @Column
    private String betaalStatus;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<FactuurOnderdeel> factuuronderdelen;
    @Column
    private String maand;

    public Factuur() {
    }

    public Factuur(int cartrakerID, double totaalBedrag, String maand) {
        this.cartrakerID = cartrakerID;
        this.totaalBedrag = totaalBedrag;
        this.maand = maand;
        factuuronderdelen = new ArrayList<FactuurOnderdeel>();
    }

    public Factuur(int nummer, int cartrakerID, double totaalBedrag, String betaalStatus, String maand) {
        this.nummer = nummer;
        this.cartrakerID = cartrakerID;
        this.totaalBedrag = totaalBedrag;
        this.betaalStatus = betaalStatus;
        this.maand = maand;
        factuuronderdelen = new ArrayList<FactuurOnderdeel>();
    }

    public void calculateAmount() {
        for (FactuurOnderdeel f : factuuronderdelen) {
            f.calculateAmount();
            this.totaalBedrag = this.totaalBedrag + f.getBedrag();
        }
    }

    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    public double getTotaalBedrag() {
        return totaalBedrag;
    }

    public void setTotaalBedrag(double totaalBedrag) {
        this.totaalBedrag = totaalBedrag;
    }

    public List<FactuurOnderdeel> getFactuuronderdelen() {
        return factuuronderdelen;
    }

    public void setFactuuronderdelen(List<FactuurOnderdeel> factuuronderdelen) {
        this.factuuronderdelen = factuuronderdelen;
    }

    public String getMaand() {
        return maand;
    }

    public void setMaand(String maand) {
        this.maand = maand;
    }

    public int getSizeOnderdeelList(){
        return factuuronderdelen.size();
    }
    
    public void addFactuurOnderdelen(FactuurOnderdeel factuurOnderdeel){
        this.factuuronderdelen.add(factuurOnderdeel);
    }

    public String getBetaalStatus() {
        return betaalStatus;
    }

    public void setBetaalStatus(String betaalStatus) {
        this.betaalStatus = betaalStatus;
    }

    public int getCartrakerID() {
        return cartrakerID;
    }

    public void setCartrakerID(int cartrakerID) {
        this.cartrakerID = cartrakerID;
    }

    @Override
    public String toString() {
        return "Factuur{" + "nummer=" + nummer + ", cartrakerID=" + cartrakerID + ", totaalBedrag=" + totaalBedrag + ", betaalStatus=" + betaalStatus + ", maand=" + maand + '}';
    }
    
    
}
