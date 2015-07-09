/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Auto;
import domain.Cartracker;
import domain.Eigenaar;
import domain.Factuur;
import domain.FactuurOnderdeel;
import domain.Kilometertarief;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import contstants.BetaalStatus;
import domain.CartrackerMovement;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import service.RekeningAdministratie;

/**
 *
 * @author kay de groot
 */
public class DataInit {

    private String[] Maand = {"januari", "februari", "maart", "april", "mei", "juni", "juli", "augustus", "september", "oktober", "november", "december"};

    DatabaseManager database;    
    RekeningAdministratie RAM;
            
    public String init(RekeningAdministratie rad,DatabaseManager db) {
        RAM=rad;
        database = db;
        database.addFactuur(new Factuur(0, 200, "Maart"));
        database.addKilometerTarief(new Kilometertarief("testregio", "Stads", 4522));
        database.addKilometerTarief(new Kilometertarief("testregio", "Stads", 422));
        Kilometertarief k = new Kilometertarief("testregio", "Stads", 42);
        database.addKilometerTarief(k);
        Eigenaar e = new Eigenaar("test data", "testing", "testvill");
        Auto a = new Auto("test", e, "test voertuig", "paars", 2);
        Cartracker c = new Cartracker(a);
        c.setId(999);
        database.addCartracker(c);
        Date date = new Date();
        date.setMonth(new Date().getMonth() - 1);
        FactuurOnderdeel fo = new FactuurOnderdeel(8, 999, k, date, date, 45);
        database.addOnderdeel(fo);
        addOnderdelen();
        System.out.println("Start timer");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int noOfLastDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(new Date());
        int day = cal2.get(Calendar.DAY_OF_MONTH);
        String testString = "test deze methode";

        int mndInt = Calendar.getInstance().get(Calendar.MONTH) - 1;
        if (mndInt < 0) {
            mndInt = 11;
        }

        if (day == noOfLastDay) {
            this.AutomaticFactuur(Maand[mndInt]);
        }
        if ("test deze methode".equals(testString)) {
            this.AutomaticFactuur(Maand[mndInt]);
        }
        return "Hoppa!";
    }

    /**
     * This method wil look through al the Factuuronderdelen for those who have
     * a enddate for this month then they wil be added to a factuur whish is
     * added to the database and a cartracker.
     */
    public void AutomaticFactuur(String maand) {        
        List<FactuurOnderdeel> onderdelen = RAM.findOnderdelenForMonth(maand);
        List<FactuurOnderdeel> factuurOnderdelen = new ArrayList();
        List<Cartracker> cs = RAM.findAllCartracker();
        Factuur factuur = null;
        for (Cartracker c : cs) {
            factuur = new Factuur(c.getId(), 0, maand);
            RAM.addFactuur(factuur);   
            factuur = RAM.getLaatsteFactuur();            
            factuur.setBetaalStatus(BetaalStatus.OPEN);  
            for(FactuurOnderdeel onderdeel : onderdelen){
                if(onderdeel.getCartrackerID() == c.getId()){     
                    System.out.println("factnummer " +factuur.getNummer());
                    onderdeel.setFactuurID(factuur.getNummer());
                    RAM.modifyFactonderdeel(onderdeel);
                    factuurOnderdelen.add(onderdeel);
                    
                }
                factuur.setFactuuronderdelen(factuurOnderdelen);
            }
            if (factuur.getSizeOnderdeelList() > 0) {
                factuur.calculateAmount();
                RAM.modifyFactuur(factuur);
                c.addFactuur(factuur);
                RAM.modifyCartracker(c);
            }
        }
    }

    public void addOnderdelen() {        
        Timer tmr = new Timer(true);
        tmr.scheduleAtFixedRate(new TimerTask() {
            RekeningAdministratie ram;

            @Override
            public void run() {
                Date date = new Date();                
                Date date2 = new Date();
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.add(Calendar.DATE, -1);
                date2 = c.getTime();
                Kilometertarief k = new Kilometertarief("testregio", "Stads", 42);
                RAM.addKilometerTarief(k);
                FactuurOnderdeel fo = new FactuurOnderdeel(999, k, date, date, 90);
                RAM.addFactuurOnderdeel(fo);
                //database.addOnderdeel(fo);
//                List<Cartracker> cs = database.findAllCartracker();
//                for(Cartracker car : cs)
//                {
//                    int id = car.getId();
//                    String carid = ""+id;
//                    List<CartrackerMovement> move = ram.getAllMovementsForCartracker(carid, date2, date);
//                    
//                        }
                
                                
                if(Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == 1){
                    int mndInt = Calendar.getInstance().get(Calendar.MONTH) -1;
                    AutomaticFactuur(Maand[mndInt]);
                }

            }
        }, 0, 86400000);
    }
}
