/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Auto;
import domain.Cartracker;
import domain.Factuur;
import domain.FactuurOnderdeel;
import domain.Kilometertarief;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kay de groot
 */
public interface IDataDistributer {
    public List<Cartracker> findAllCartraker();

    public List<FactuurOnderdeel> findOnderdelenForMonth(String Maand);

    public void addFactuur(Factuur factuur);

    public void mergeCartraker(Cartracker c);
    
    public void addOnderdeel(FactuurOnderdeel fo);

    public Factuur findFactuurWithID(int nummer);

    public void mergeFactuur(Factuur factuur);

    public void addCartraker(Cartracker cartracker);

    public Cartracker findCartrakerWithId(int nummer);

    public List<Auto> getAutos(int i);
    
    public List<Kilometertarief> getAlleKilometerTarieven();
    
    public Kilometertarief getKilometerTarief(int id);
    
    public void addKilometerTarief(Kilometertarief kt);
    
    public void editKilometerTarief(Kilometertarief kt);
    
    public void deleteKilometerTarief(int id);
}
