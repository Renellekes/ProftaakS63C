/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import contstants.BetaalStatus;
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
    @Column
    private int cartrackerID;
    @Column
    private double totaalBedrag;
    @Column
    private String betaalStatus;

    private List<FactuurOnderdeel> factuuronderdelen;
    @Column
    private String maand;

    public Factuur() {
    }

    /**
     * Constructor to create a Factuur
     *
     * @param cartrackerID used to keep track of all the cartracker.
     * @param totaalBedrag used to see how much it kost.
     * @param maand used to see for whicj month this factuur is.
     */
    public Factuur(int cartrackerID, double totaalBedrag, String maand) {
        this.cartrackerID = cartrackerID;
        this.totaalBedrag = totaalBedrag;
        this.maand = maand;
        factuuronderdelen = new ArrayList<FactuurOnderdeel>();
        this.betaalStatus = BetaalStatus.OPEN;
    }

    /**
     * Constructor to create a Factuur
     *
     * @param nummer used as a identifier.
     * @param cartrackerID used to keep track of all the cartracker.
     * @param totaalBedrag used to see how much it kost.
     * @param maand used to see for whicj month this factuur is.
     */
    public Factuur(int nummer, int cartrakerID, double totaalBedrag, String maand) {
        this.nummer = nummer;
        this.cartrackerID = cartrackerID;
        this.totaalBedrag = totaalBedrag;
        this.betaalStatus = BetaalStatus.OPEN;
        this.maand = maand;
        factuuronderdelen = new ArrayList<FactuurOnderdeel>();
    }

    /**
     * loops through the list of FactuurOnderdelen than calls each
     * factuuronderdeel calculateAmount and add it to totaalbedrag.
     */
    public void calculateAmount() {
        for (FactuurOnderdeel f : factuuronderdelen) {
            f.calculateAmount();
            this.totaalBedrag = this.totaalBedrag + f.getBedrag();
        }
    }

    /**
     * get the nummer
     *
     * @return int
     */
    public int getNummer() {
        return this.nummer;
    }

    /**
     * replace the nummer for a new value
     *
     * @param nummer the new value to replace the old one
     */
    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    /**
     * get the totaalBedrag
     *
     * @return double
     */
    public double getTotaalBedrag() {
        return totaalBedrag;
    }

    /**
     * replace the totaalBedrag for a new value
     *
     * @param totaalBedrag the new value to replace the old one
     */
    public void setTotaalBedrag(double totaalBedrag) {
        this.totaalBedrag = totaalBedrag;
    }

    /**
     * get the List<FactuurOnderdeel>
     *
     * @return List<FactuurOnderdeel>
     */
    public List<FactuurOnderdeel> getFactuuronderdelen() {
        return factuuronderdelen;
    }

    /**
     * replace the List<FactuurOnderdeel> for a new value
     *
     * @param List<FactuurOnderdeel> the new value to replace the old one
     */
    public void setFactuuronderdelen(List<FactuurOnderdeel> factuuronderdelen) {
        this.factuuronderdelen = factuuronderdelen;
    }

    /**
     * get the maand
     *
     * @return String
     */
    public String getMaand() {
        return maand;
    }

    /**
     * replace the maand for a new value
     *
     * @param String the new value to replace the old one
     */
    public void setMaand(String maand) {
        this.maand = maand;
    }

    /**
     * get the size of List<factuuronderdeel>
     *
     * @return list of factuuronderdelen
     */
    public int getSizeOnderdeelList() {
        return factuuronderdelen.size();
    }

    /**
     * add the new factuurOnderdeel to the List<factuurOnderdeel>
     *
     * @param String the new value to replace the old one
     */
    public void addFactuurOnderdelen(FactuurOnderdeel factuurOnderdeel) {
        factuurOnderdeel.setFactuurID(this.nummer);
        this.factuuronderdelen.add(factuurOnderdeel);
    }

    /**
     * get the betaalStatus
     *
     * @return string
     */
    public String getBetaalStatus() {
        return betaalStatus;
    }

    /**
     * replace the betaalStatus for a new value
     *
     * @param String the new value to replace the old one
     */
    public void setBetaalStatus(String betaalStatus) {
        this.betaalStatus = betaalStatus;
    }

    /**
     * get the cartrackerID
     *
     * @return int
     */
    public int getCartrackerID() {
        return cartrackerID;
    }

    /**
     * replace the cartrackerID for a new value
     *
     * @param int the new value to replace the old one
     */
    public void setCartrackerID(int cartrackerID) {
        this.cartrackerID = cartrackerID;
    }

    /**
     *
     * @return string of all the values together
     */
    @Override
    public String toString() {
        return "Factuur{" + "nummer=" + nummer + ", cartrackerID=" + cartrackerID + ", totaalBedrag=" + totaalBedrag + ", betaalStatus=" + betaalStatus + ", maand=" + maand + '}';
    }

}
