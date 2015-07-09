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
import domain.CartrackerMovement;
import java.net.URISyntaxException;
import service.File;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import sockets.MovementSystemSockets;

/**
 *
 * @author kay de groot
 */
@Stateless
public class RekeningAdministratie {

    private List<Account> accounts;
    private List<Cartracker> cartrackers;

    @Inject
    private DatabaseManager database;

    private List<Kilometertarief> kilometertarieven;
    private List<Eigenaar> eigenaren;
    private List<Medewerker> medewerkers;
    private Timer timer;

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

    public void addFactuur(Factuur factuur) {
        database.addFactuur(factuur);
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

    public void modifyEigenaar(Eigenaar eigenaar) {
        Eigenaar b = (Eigenaar) database.getEigenaar(eigenaar.getId());
        b.setAdres(eigenaar.getAdres());
        b.setWoonplaats(eigenaar.getWoonplaats());
        database.modifyEigenaar(b);
    }

    public void modifyFactonderdeel(FactuurOnderdeel onderdeel) {
        FactuurOnderdeel b = (FactuurOnderdeel) database.getFactuurOnderdeel(onderdeel.getFactuurOnderdeelID());
        b.setFactuurID(onderdeel.getFactuurID());
        database.MergeFactuurOnderdeel(b);
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

    public void addFactuurOnderdeel(int factuurID, int CartrackerID, Kilometertarief kilometertarief, Date beginTijd, Date eindTijd, long aantalKilometers) {
        FactuurOnderdeel factuurOnderdeel = new FactuurOnderdeel(factuurID, CartrackerID, kilometertarief, beginTijd, eindTijd, aantalKilometers);
        this.addFactuurOnderdeel(factuurOnderdeel);
    }

    public List<Auto> getAllAutos() {
        List<Auto> autos = database.getAllAutos();
        return autos;
    }

    public Auto getAuto(int i) {
        Auto autos = database.getAuto(i);
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

    public List<Cartracker> getCartrackers() {
        return database.getCartrackers();
    }

    public Cartracker getCartracker(int id) {
        return database.getCartracker(id);
    }

    public Eigenaar getEigenaar(int id) {
        return database.getEigenaar(id);
    }

    public List<Eigenaar> getAllEigenaars() {
        List<Eigenaar> eigenaars = database.getAllEigenaars();
        return eigenaars;
    }

    public void modifyAuto(Auto a) {
        Auto b = (Auto) database.getAuto(a.getId());
        b.setEersteKleur(a.getEersteKleur());
        b.setZitplaatsen(a.getZitplaatsen());
        b.setVoertuig(a.getVoertuig());
        database.modifyAuto(b);
    }

    public Factuur getFactuur(int id) {
        return database.getFactuur(id);
    }

    public String init() {
        return database.init(this);
    }

    public void factuurBetaald(int id) {
        database.factuurBetaald(id);
    }

    public List<CartrackerMovement> getAllMovements(Date start, Date end) {
        try {
            System.out.println("start");
            MovementSystemSockets ms = new MovementSystemSockets("ws://localhost:7071/VPSystem/MovementSystemEndpoint");
            System.out.println("go");
            int callId = ms.getAllMovement(start, end);
            ms.getCartrackersForCallId(callId);
            System.out.println("list");
            List<CartrackerMovement> whothefuckcares;

            System.out.println("go");

            return null;
        } catch (URISyntaxException ex) {
            Logger.getLogger(RekeningAdministratie.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<CartrackerMovement> getAllMovementsForCartracker(String cartrackerId, Date start, Date end) {
        try {
            final MovementSystemSockets ms = new MovementSystemSockets("ws://localhost:7071/VerplaatsingenSysteem/MovementSystemEndpoint");
            final int callId = ms.getMovementForUser(cartrackerId, start, end);
            final List<CartrackerMovement> whothefuckcares = new ArrayList<>();

            Timer tmr = new Timer();
            tmr.schedule(new TimerTask() {

                @Override
                public void run() {
                    for (CartrackerMovement m : ms.getCartrackersForCallId(callId)) {
                        whothefuckcares.add(m);
                    }
                }
            }, 2000);

            return whothefuckcares;
        } catch (URISyntaxException ex) {
            Logger.getLogger(RekeningAdministratie.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<FactuurOnderdeel> findOnderdelenForMonth(String maand) {
        return database.findOnderdelenForMonth(maand);
    }

    public List<Cartracker> findAllCartracker() {
        return database.findAllCartracker();
    }

    public Factuur getLaatsteFactuur() {
        return database.getLaatsteFactuur();
    }

    public void modifyFactuur(Factuur factuur) {
        Factuur b = (Factuur) database.getFactuur(factuur.getNummer());
        b.setBetaalStatus(factuur.getBetaalStatus());
        b.setFactuuronderdelen(factuur.getFactuuronderdelen());
        b.setTotaalBedrag(factuur.getTotaalBedrag());
        database.mergeFactuur(b);
    }
}
