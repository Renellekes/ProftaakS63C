/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.File;
import java.io.Serializable;
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
public class Auto implements Serializable {
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

    public Auto(String kenteken, String voertuig, String eersteKleur, int zitplaatsen) {
        this.kenteken = kenteken;
        this.voertuig = voertuig;
        this.eersteKleur = eersteKleur;
        this.zitplaatsen = zitplaatsen;
    }

    
    
    public Auto(String kenteken, Eigenaar eigenaar, String voertuig, String eersteKleur, int zitplaatsen) {
        this.kenteken = kenteken;
        this.eigenaar = eigenaar;
        this.voertuig = voertuig;
        this.eersteKleur = eersteKleur;
        this.zitplaatsen = zitplaatsen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKenteken() {
        return kenteken;
    }

    public void setKenteken(String kenteken) {
        this.kenteken = kenteken;
    }

    public Eigenaar getEigenaar() {
        return eigenaar;
    }

    public void setEigenaar(Eigenaar eigenaar) {
        this.eigenaar = eigenaar;
    }

    public File getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(File fileInfo) {
        this.fileInfo = fileInfo;
    }

    public boolean isGestolen() {
        return gestolen;
    }

    public void setGestolen(boolean gestolen) {
        this.gestolen = gestolen;
    }

    public String getVoertuig() {
        return voertuig;
    }

    public void setVoertuig(String voertuig) {
        this.voertuig = voertuig;
    }

    public String getEersteKleur() {
        return eersteKleur;
    }

    public void setEersteKleur(String eersteKleur) {
        this.eersteKleur = eersteKleur;
    }

    public int getZitplaatsen() {
        return zitplaatsen;
    }

    public void setZitplaatsen(int zitplaatsen) {
        this.zitplaatsen = zitplaatsen;
    }

    
    
    @Override
    public String toString() {
        return "Auto{" + "id=" + id + ", kenteken=" + kenteken + ", eigenaar=" + eigenaar + ", fileInfo=" + fileInfo + ", gestolen=" + gestolen + ", voertuig=" + voertuig + ", eersteKleur=" + eersteKleur + ", zitplaatsen=" + zitplaatsen + '}';
    }

}
