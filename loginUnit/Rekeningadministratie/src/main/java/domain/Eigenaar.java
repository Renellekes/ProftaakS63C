/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author kay de groot
 */
@Entity
@Table(name = "Eigenaar")
public class Eigenaar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToMany
    private Collection<Auto> autos;
    @Column
    private String naam;
    @Column
    private String adres;
    @Column
    private String woonplaats;
    @Column
    private boolean website;

    public Eigenaar() {
    }

    public Eigenaar(String naam, String adres, String woonplaats) {
        this.naam = naam;
        this.adres = adres;
        this.woonplaats = woonplaats;
    }
    
    
}
