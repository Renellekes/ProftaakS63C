/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author kay de groot
 */
public class Account {
    private int id;
    private String wachtwoord;
    private Medewerker medewerker;

    public Account(String wachtwoord, Medewerker medewerker) {
        this.wachtwoord = wachtwoord;
        this.medewerker = medewerker;
    }
    
    
}
