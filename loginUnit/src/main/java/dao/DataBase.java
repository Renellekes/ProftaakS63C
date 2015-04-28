/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class DataBase {
       
    private static Connection c;
    private static String url;
    private static String username;
    private static String password;
          
    @PostConstruct
     private void initConnection() {
        url = "jdbc:mysql://localhost:3306/InlogSysteem";
        username = "root";
        password = "root";
    }  
      
    public Connection connect() {
        try {
            c = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    } 
     
    public int login(String username, String hash, String systeem) {
        int returncode = 0;
        
        try {
            c = connect();
            String sql = "SELECT * FROM USER WHERE username = ? AND password = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, hash);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                String sql2 = "SELECT * FROM RIGHTS WHERE username = ? AND systeem = ?";
                PreparedStatement ps2 = c.prepareStatement(sql);
                ps2.setString(1, username);
                ps2.setString(2, systeem);
                ResultSet rs2 = ps2.executeQuery();
                if (!rs2.next())
                {
                    returncode = 2;
                }
            }else{
                returncode = 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            returncode = 3;
        } finally {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returncode = 3;
            }
            return returncode;
        }   
    }
    
    public int register(String username, String hash) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int addRight(String username, String systeem) {
        int returncode = 0;
        try {
            c = connect();
            String sql = "UPDATE Gebruiker SET rating = ? WHERE gebruiker = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, systeem);
            ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            returncode = 1;
        } finally {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returncode = 1;
            }
        }
        return returncode;
    }

    public int removeRight(String username, String right) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int remove(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
