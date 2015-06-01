/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Auto;
import domain.Cartracker;
import domain.Eigenaar;
import domain.FactuurOnderdeel;
import domain.Kilometertarief;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.inject.Inject;
import service.RekeningAdministratie;

/**
 *
 * @author kay de groot
 */
@Singleton
@Startup
public class DataInit {
    
    @Inject
    DatabaseManager idd;
    
    @PostConstruct
    private void init() {
        idd.addKilometerTarief(new Kilometertarief("testregio", "Stads", 4522));
        idd.addKilometerTarief(new Kilometertarief("testregio", "Stads", 422));
        idd.addKilometerTarief(new Kilometertarief("testregio", "Stads", 42));
        
    }
}
