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
import dao.IDataDistributer;
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
public class RekeningAdministratie implements IRekeningAdministratie {

    private String[] Maand = {"januari", "februari", "maart", "april", "mei", "juni", "juli", "augustus", "september", "oktober", "november", "december"};
    private List<Account> accounts;
    private List<Cartracker> cartrackers;

    @Inject
    private IDataDistributer database;

    private List<Kilometertarief> kilometertarieven;
    private List<Eigenaar> eigenaren;
    private List<Medewerker> medewerkers;
    private Timer timer;

    @PostConstruct
    @Override
    public void RekeningAdministratieInit() {
        timer = new Timer();
        final Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new Date()); // sets calendar time/date
        cal.add(Calendar.MINUTE, 5); // adds one hour
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                RekeningAdministratie.this.AutomaticFactuur();
                cal.setTime(new Date());

                //Dit moet worden verandert worden naar maand wanneer er niet meer hoeft te worden getest.
                cal.add(Calendar.HOUR_OF_DAY, 1);
                timer.schedule(this, cal.getTime());
            }
        };
        timer.schedule(task, cal.getTime());
    }

    @Override
    public void addAccount(Account account) {
    }

    @Override
    public void modifyAccount(int ID, Account account) {
    }

    @Override
    public void removeAccount(int ID) {
    }

    @Override
    public void addCartraker(Cartracker cartracker) {

        database.addCartraker(cartracker);

    }

    @Override
    public void modifyCartraker(int ID, Cartracker cartracker) {
        List<Cartracker> cartrackers = database.findAllCartraker();
        for (Cartracker c : cartrackers) {
            if (c.getId() == ID) {
                database.mergeCartraker(cartracker);
            }
        }

    }

    @Override
    public void removeCartraker(int ID) {
    }

    @Override
    public void connectDatsbase(DatabaseManager db) {
    }

    @Override
    public void addKilometertarief(Kilometertarief kilometertarief) {
    }

    @Override
    public void modifyKilometertarief(int ID, Kilometertarief kilometertarief) {
    }

    @Override
    public void removeKilometertarief(int ID) {
    }

    /**
     * This method wil look through al the Factuuronderdelen for those who have
     * a enddate for this month then they wil be added to a factuur whish is
     * added to the database and a cartraker.
     */
    @Override
    public void AutomaticFactuur() {
        List<FactuurOnderdeel> onderdelen = database.findOnderdelenForMonth(Maand[Calendar.MONTH]);
        List<Cartracker> cs = database.findAllCartraker();
        for (Cartracker c : cs) {
            Factuur factuur = new Factuur(c, 0, Maand[Calendar.MONTH]);
            for (FactuurOnderdeel fac : onderdelen) {
                if ((fac.getCartrakerID() == c.getId()) && (Maand[fac.getEindTijd().getMonth()].equals(Maand[Calendar.MONTH]))) {
                    factuur.addFactuurOnderdelen(fac);
                    onderdelen.remove(fac);
                }
            }
            if (factuur.getSizeOnderdeelList() > 0) {
                factuur.calculateAmount();
                c.addFactuur(factuur);
                database.mergeCartraker(c);
            }
        }
    }

    @Override
    public void addFactuur(Factuur factuur) {
    }

    @Override
    public void changeStatusFactuur(String status, int nummer) {
        Factuur factuur = database.findFactuurWithID(nummer);
        if (!status.equals(factuur.getBetaalStatus())) {
            factuur.setBetaalStatus(status);
            database.mergeFactuur(factuur);
        }
    }

    @Override
    public void updateFiles(List<File> files) {
    }

    @Override
    public void addeigenaar(Eigenaar eigenaar) {
    }

    @Override
    public void modifyEigenaar(int ID, Eigenaar eigenaar) {
    }

    @Override
    public void removeEigenaar(int ID) {
    }

    @Override
    public void addMedewerker(Medewerker medewerker) {
    }

    @Override
    public void modifyMedewerker(int ID, Medewerker medewerker) {
    }

    @Override
    public void removeMedewerker(int ID) {
    }

    @Override
    public void addFactuurOnderdeel(FactuurOnderdeel fo) {
        database.addOnderdeel(fo);
    }

    @Override
    public void addFactuurOnderdeel(int CartrakerID, Kilometertarief kilometertarief, Date beginTijd, Date eindTijd, long aantalKilometers) {
        FactuurOnderdeel factuurOnderdeel = new FactuurOnderdeel(CartrakerID, kilometertarief, beginTijd, eindTijd, aantalKilometers);
        this.addFactuurOnderdeel(factuurOnderdeel);
    }

    @Override
    public List<Auto> getAutos(int i) {
        List<Auto> autos = database.getAutos(i);
        return autos;
    }
}
