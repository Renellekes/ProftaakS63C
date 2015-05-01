/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Date;
import javax.ejb.Stateless;
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
@Stateless
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
    private int bedrag;
    

    public Kilometertarief() {
    }

    public Kilometertarief( String regio, String tariefCategorie, int bedrag) {
        this.regio = regio;
        this.tariefCategorie = tariefCategorie;
        this.bedrag = bedrag;
    }

    public String getTariefCategorie() {
        return tariefCategorie;
    }

    public int getBedrag() {
        return bedrag;
    }
    
    
    
    
}
