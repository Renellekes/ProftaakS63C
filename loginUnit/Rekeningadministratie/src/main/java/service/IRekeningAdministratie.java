/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.DatabaseManager;
import domain.Account;
import domain.Cartracker;
import domain.Eigenaar;
import domain.Factuur;
import domain.FactuurOnderdeel;
import domain.Kilometertarief;
import domain.Medewerker;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author kay de groot
 */
public interface IRekeningAdministratie {
    
    public void RekeningAdministratieInit();
    
    public void addAccount(Account account) ;

    public void modifyAccount(int ID, Account account) ;

    public void removeAccount(int ID) ;

    public void addCartraker(Cartracker cartracker) ;

    public void modifyCartraker(int ID, Cartracker cartracker);

    public void removeCartraker(int ID);

    public void connectDatsbase(DatabaseManager db);

    public void addKilometertarief(Kilometertarief kilometertarief);

    public void modifyKilometertarief(int ID, Kilometertarief kilometertarief);

    public void removeKilometertarief(int ID);

    public void AutomaticFactuur();

    public void addFactuur(Factuur factuur);

    public void changeStatusFactuur(String status, int nummer);

    public void updateFiles(List<File> files);

    public void addeigenaar(Eigenaar eigenaar);

    public void modifyEigenaar(int ID, Eigenaar eigenaar);

    public void removeEigenaar(int ID);

    public void addMedewerker(Medewerker medewerker);

    public void modifyMedewerker(int ID, Medewerker medewerker);

    public void removeMedewerker(int ID);
    
    public void addFactuurOnderdeel(FactuurOnderdeel fo);
    public void addFactuurOnderdeel(int CartrakerID, Kilometertarief kilometertarief, Date beginTijd, Date eindTijd, long aantalKilometers);

    
}
