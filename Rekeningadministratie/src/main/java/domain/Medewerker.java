/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.List;

/**
 *
 * @author kay de groot
 */
public class Medewerker {
    private int id;
    private String naam;
    private String email;
    private String adres;
    private List<String> rechten;

    public Medewerker(String naam, String email, String adres) {
        this.naam = naam;
        this.email = email;
        this.adres = adres;
    }
    
    
}
