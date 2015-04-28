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
import javax.ejb.Stateless;
import javax.inject.Inject;
import service.IRekeningAdministratie;

/**
 *
 * @author kay de groot
 */
@Singleton
@Stateless
public class DataInit {
    
    @Inject
    private IDataDistributer idd;
    
    @PostConstruct
    public void Constructor(){
        Cartracker c = new Cartracker(new Auto("test", new Eigenaar("meneer test", "testing", "Tester")));
        idd.addCartraker(c);
        
        c = idd.findAllCartraker().get(0);
        Kilometertarief k = new Kilometertarief("testregio", "Stads", 45.2);
        FactuurOnderdeel fo = new FactuurOnderdeel(c.getId(), k, new Date(), new Date(), 513);
        idd.addOnderdeel(fo);
    }
}
