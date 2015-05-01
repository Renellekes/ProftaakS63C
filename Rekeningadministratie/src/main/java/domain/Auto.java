/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.File;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author kay de groot
 */
@Entity
@Table(name = "Auto")
public class Auto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String kenteken;
    @JoinColumn(name = "eigenaarID", referencedColumnName = "id")
    private Eigenaar eigenaar;
    @Column
    private File fileInfo;
    @Column
    private boolean gestolen;
    @Column
    private String voertuig;
    @Column
    private String eersteKleur;
    @Column
    private int zitplaatsen;

    public Auto() {
    }

    public Auto(String kenteken, Eigenaar eigenaar) {
        this.kenteken = kenteken;
        this.eigenaar = eigenaar;
    }

    public Auto(String kenteken, Eigenaar eigenaar, String voertuig, String eersteKleur, int zitplaatsen) {
        this.kenteken = kenteken;
        this.eigenaar = eigenaar;
        this.voertuig = voertuig;
        this.eersteKleur = eersteKleur;
        this.zitplaatsen = zitplaatsen;
    }

    public void setEigenaar(Eigenaar eigenaar) {
        this.eigenaar = eigenaar;
    }

    public String getKenteken() {
        return this.kenteken;
    }

}
