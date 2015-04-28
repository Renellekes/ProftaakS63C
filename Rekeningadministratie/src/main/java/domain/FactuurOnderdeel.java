/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;

/**
 *
 * @author kay de groot
 */
@Entity
@Table(name = "FactuurOnderdeel")
public class FactuurOnderdeel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int FactuurOnderdeelID;
    @Column
    private int CartrakerID;
    @JoinColumn(name = "kilometertariefID", referencedColumnName = "id")
    private Kilometertarief kilometertarief;
    @Column
    @Temporal(DATE)
    private Date beginTijd;
    @Column
    @Temporal(DATE)
    private Date eindTijd;
    @Column
    private String maand;
    @Column
    private long aantalKilometers;
    @Column
    private double bedrag;

    public FactuurOnderdeel() {
//        this.setMonth();
    }

    public FactuurOnderdeel(int CartrakerID, Kilometertarief kilometertarief, Date beginTijd, Date eindTijd, long aantalKilometers) {
        this.CartrakerID = CartrakerID;
        this.kilometertarief = kilometertarief;
        this.beginTijd = beginTijd;
        this.eindTijd = eindTijd;
        this.aantalKilometers = aantalKilometers;
//        this.setMonth();
    }

    public void setMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.eindTijd);
        switch (cal.get(Calendar.MONTH)) {
            case 0:
                maand = "januari";
                break;
            case 1:
                maand = "februari";
                break;
            case 2:
                maand = "maart";
                break;
            case 3:
                maand = "april";
                break;
            case 4:
                maand = "mei";
                break;
            case 5:
                maand = "juni";
                break;
            case 6:
                maand = "juli";
                break;
            case 7:
                maand = "augustus";
                break;
            case 8:
                maand = "september";
                break;
            case 9:
                maand = "oktober";
                break;
            case 10:
                maand = "november";
                break;
            case 11:
                maand = "december";
                break;
        }
    }

    public void calculateAmount() {
        if ("Stads".equals(kilometertarief.getTariefCategorie())) {
            bedrag = aantalKilometers * kilometertarief.getBedrag();
        } else {
            bedrag = aantalKilometers * kilometertarief.getBedrag();
        }
    }

    public Date getEindTijd() {
        return eindTijd;
    }

    public double getBedrag() {
        return bedrag;
    }

    public int getCartrakerID() {
        return CartrakerID;
    }

}
