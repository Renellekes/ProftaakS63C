/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author kay de groot
 */
@Entity
@Table(name = "Kilometertarief")
public class Kilometertarief {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String regio;
    @Column
    private String tariefCategorie;
    @Column
    private double bedrag;

    public Kilometertarief() {
    }

    public Kilometertarief( String regio, String tariefCategorie, double bedrag) {
        this.regio = regio;
        this.tariefCategorie = tariefCategorie;
        this.bedrag = bedrag;
    }

    public String getTariefCategorie() {
        return tariefCategorie;
    }

    public double getBedrag() {
        return bedrag;
    }
    
    
    
    
}