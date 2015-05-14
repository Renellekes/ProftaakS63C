/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author kay de groot
 */
@Entity
@Table(name = "Cartracker")
public class Cartracker implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @JoinColumn(name = "AutoID",referencedColumnName = "id")
    private Auto auto;
    @Column
    private boolean website;
    @OneToMany
    private Collection<Factuur> facturen;
    private List<File> fileInfo;

    public Cartracker() {
    }

    public Cartracker(Auto auto) {
        facturen = new ArrayList<Factuur>();
        fileInfo = new ArrayList<File>();
        this.auto = auto;
    }

    public int getId() {
        return id;
    }
    
    public void addFactuur(Factuur factuur){
        this.facturen.add(factuur);
    }
    
}
