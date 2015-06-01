/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Road;
import java.util.List;

/**
 *
 * @author Daan
 */
public interface roadsDAO {
    
    void create(Road r);
    List<Road> getAllRoads();
}
