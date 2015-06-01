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
import javax.ejb.Schedule;
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
        Eigenaar e = new Eigenaar("test data", "testing", "testvill");
        Auto a = new Auto("test", e, "test voertuig", "paars", 2);
        Cartracker c = new Cartracker(a);
        c.setId(999);
        database.addCartracker(c);
        Kilometertarief k = new Kilometertarief("testvill", "standaard", 5);
        database.addKilometerTarief(k);
        FactuurOnderdeel fo = new FactuurOnderdeel(999, k, new Date(), new Date(), 45);
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
        System.out.println("rekenrijder 2");
        database.mergeCartracker(cartracker);

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
    public void AutomaticFactuur(String maand) {
        System.out.println("Start timeout " + Maand[Calendar.getInstance().get(Calendar.MONTH)] + maand);
        List<FactuurOnderdeel> onderdelen = database.findOnderdelenForMonth(maand);
        List<Cartracker> cs = database.findAllCartracker();
        Factuur factuur = null;
        System.out.println("Testing 1 " + cs.size() + " : " + onderdelen.size());
        for (Cartracker c : cs) {
            factuur = new Factuur(c.getId(), 0, maand);
            factuur.setBetaalStatus("Open");
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

    public void modifyAuto(Auto a) {
        database.modifyAuto(a);
    }
}
