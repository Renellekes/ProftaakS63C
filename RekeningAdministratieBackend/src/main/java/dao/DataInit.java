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
/**
 *
 * @author kay de groot
 */
@Singleton
@Startup
public class DataInit {
    
    private String[] Maand = {"januari", "februari", "maart", "april", "mei", "juni", "juli", "augustus", "september", "oktober", "november", "december"};
    
    @Inject
    DatabaseManager database;
    
    @PostConstruct
    private void init() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(DataInit.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
        date.setMonth(new Date().getMonth()-1);
        FactuurOnderdeel fo = new FactuurOnderdeel(999, k, date, date, 45);
        database.addOnderdeel(fo);

        System.out.println("Start timer");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int noOfLastDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(new Date());
        int day = cal2.get(Calendar.DAY_OF_MONTH);
        String testString = null;

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
    }
    
    /**
     * This method wil look through al the Factuuronderdelen for those who have
     * a enddate for this month then they wil be added to a factuur whish is
     * added to the database and a cartracker.
     */
    public void AutomaticFactuur(String maand) {
        System.out.println("Start timeout " + Maand[Calendar.getInstance().get(Calendar.MONTH)] + maand);
        List<FactuurOnderdeel> onderdelen = database.findOnderdelenForMonth(maand);
        List<Cartracker> cs = database.findAllCartracker();
        Factuur factuur = null;
        System.out.println("Testing 1 " + cs.size() + " : " + onderdelen.size());
        for (Cartracker c : cs) {
            factuur = new Factuur(c.getId(), 0, maand);
            factuur.setBetaalStatus(BetaalStatus.OPEN);
            for (FactuurOnderdeel fac : onderdelen) {
                System.out.println("Testing 2 fac" + fac.getCartrackerID() + " : cartracker" + c.getId());
                System.out.println("Testing 3 " + fac.getMaand());
                if ((fac.getCartrackerID() == c.getId()) && (maand.equals(fac.getMaand()))) {
                    factuur.addFactuurOnderdelen(fac);
                }
            }
            if (factuur.getSizeOnderdeelList() > 0) {
                factuur.calculateAmount();
                database.addFactuur(factuur);
                c.addFactuur(factuur);
                database.mergeCartracker(c);
            }
        }
    }
}
