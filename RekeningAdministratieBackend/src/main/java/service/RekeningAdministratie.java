/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Account;
import domain.Cartracker;
import domain.Eigenaar;
import domain.Factuur;
import domain.FactuurOnderdeel;
import domain.Kilometertarief;
import domain.Medewerker;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import dao.DatabaseManager;
import domain.Auto;
import service.File;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author kay de groot
 */
@Stateless
public class RekeningAdministratie {

    private String[] Maand = {"januari", "februari", "maart", "april", "mei", "juni", "juli", "augustus", "september", "oktober", "november", "december"};
    private List<Account> accounts;
    private List<Cartracker> cartrackers;

    @Inject
    private DatabaseManager database;

    private List<Kilometertarief> kilometertarieven;
    private List<Eigenaar> eigenaren;
    private List<Medewerker> medewerkers;
    private Timer timer;

    @PostConstruct
    public void RekeningAdministratieInit() {
        timer = new Timer();
        final Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new Date()); // sets calendar time/date
        cal.add(Calendar.MINUTE, 5); // adds one hour
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                List<FactuurOnderdeel> onderdelen = database.findOnderdelenForMonth(Maand[Calendar.MONTH]);
                List<Cartracker> cs = database.findAllCartracker();
                for (Cartracker c : cs) {
                    Factuur factuur = new Factuur(c, 0, Maand[Calendar.MONTH]);
                    for (FactuurOnderdeel fac : onderdelen) {
                        if ((fac.getCartrackerID() == c.getId()) && (Maand[fac.getEindTijd().getMonth()].equals(Maand[Calendar.MONTH]))) {
                            factuur.addFactuurOnderdelen(fac);
                            onderdelen.remove(fac);
                        }
                    }
                    if (factuur.getSizeOnderdeelList() > 0) {
                        factuur.calculateAmount();
                        c.addFactuur(factuur);
                        database.mergeCartracker(c);
                    }
                }
                //Dit moet worden verandert worden naar maand wanneer er niet meer hoeft te worden getest.
                cal.clear();
                cal.setTime(new Date());
                cal.add(Calendar.HOUR_OF_DAY, 1);
                timer.schedule(this, cal.getTime());
            }
        };
        timer.schedule(task, cal.getTime());
    }

    public void addAccount(Account account) {
    }

    public void modifyAccount(int ID, Account account) {
    }

    public void removeAccount(int ID) {
    }

    public void addCartracker(Cartracker cartracker) {
        System.out.println(cartracker);
        database.addCartracker(cartracker);
    }

    public void modifyCartracker(Cartracker cartracker) {
        List<Cartracker> cartrackers = database.findAllCartracker();
        for (Cartracker c : cartrackers) {
            if (c.getId() == cartracker.getId()) {
                database.mergeCartracker(cartracker);
            }
        }

    }
    public void modifyAuto(Auto auto) {
        List<Auto> autos = database.getAllAutos();
        for (Auto c : autos) {
            if (c.getId() == auto.getId()) {
                database.mergeAuto(auto);
            }
        }

    }

    public void removeCartracker(int ID) {
    }

    public void connectDatsbase(DatabaseManager db) {
    }

    /**
     * This method wil look through al the Factuuronderdelen for those who have
     * a enddate for this month then they wil be added to a factuur whish is
     * added to the database and a cartracker.
     */
    public void AutomaticFactuur() {

    }

    public void addFactuur(Factuur factuur) {
    }

    public void changeStatusFactuur(String status, int nummer) {
        Factuur factuur = database.findFactuurWithID(nummer);
        if (!status.equals(factuur.getBetaalStatus())) {
            factuur.setBetaalStatus(status);
            database.mergeFactuur(factuur);
        }
    }

    public void updateFiles(List<File> files) {
    }

    public void addeigenaar(Eigenaar eigenaar) {
    }

    public void modifyEigenaar(int ID, Eigenaar eigenaar) {
    }

    public void removeEigenaar(int ID) {
    }

    public void addMedewerker(Medewerker medewerker) {
    }

    public void modifyMedewerker(int ID, Medewerker medewerker) {
    }

    public void removeMedewerker(int ID) {
    }

    public void addFactuurOnderdeel(FactuurOnderdeel fo) {
        database.addOnderdeel(fo);
    }

    public void addFactuurOnderdeel(int CartrackerID, Kilometertarief kilometertarief, Date beginTijd, Date eindTijd, long aantalKilometers) {
        FactuurOnderdeel factuurOnderdeel = new FactuurOnderdeel(CartrackerID, kilometertarief, beginTijd, eindTijd, aantalKilometers);
        this.addFactuurOnderdeel(factuurOnderdeel);
    }

    public List<Auto> getAllAutos() {
        List<Auto> autos = database.getAllAutos();
        return autos;
    }
    
    public List<Auto> getAuto(int i) {
        List<Auto> autos = database.getAuto(i);
        return autos;
    }

    public List<Kilometertarief> getAlleKilometerTarieven() {
        return database.getAlleKilometerTarieven();
    }

    public Kilometertarief getKilometerTarief(int id) {
        return database.getKilometerTarief(id);
    }

    public void addKilometerTarief(Kilometertarief kt) {
        database.addKilometerTarief(kt);
    }

    public void editKilometerTarief(Kilometertarief kt) {
        database.editKilometerTarief(kt);
    }

    public void deleteKilometerTarief(int id) {
        database.deleteKilometerTarief(id);
    }

    public List<Factuur> getAlleFacturen() {
        return database.getAlleFacturen();
    }

    public void addAuto(Auto nieuweAuto) {
        database.addAuto(nieuweAuto);
    }

    public List<Cartracker> getCartracker() {
        return database.getCartracker();
    }

    public Eigenaar getEigenaar(int id) {
        return database.getEigenaar(id);
    }
}
