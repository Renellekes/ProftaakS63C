/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author kay de groot
 */
@Entity
@Table(name = "Factuur")
public class Factuur {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int nummer;
    @JoinColumn(name = "cartrakerID",referencedColumnName = "id")
    private Cartracker cartraker;
    @Column
    private double totaalBedrag;
    @Column
    private String betaalStatus;
    @ManyToOne(targetEntity = FactuurOnderdeel.class)
    private List<FactuurOnderdeel> factuuronderdelen;
    @Column
    private String maand;

    public Factuur() {
    }

    public Factuur(Cartracker cartraker, double totaalBedrag, String maand) {
        this.cartraker = cartraker;
        this.totaalBedrag = totaalBedrag;
        this.maand = maand;
        factuuronderdelen = new ArrayList<FactuurOnderdeel>();
    }

    public Factuur(int nummer, Cartracker cartraker, double totaalBedrag, String betaalStatus, String maand) {
        this.nummer = nummer;
        this.cartraker = cartraker;
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
    
    
}