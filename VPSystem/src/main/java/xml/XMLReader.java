/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import domain.CartrackerMovement;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Daan
 */
public class XMLReader extends DefaultHandler {
    
    private final SAXParserFactory factory;
    private CartrackerMovement currentMovement;
    private ArrayList<CartrackerMovement> movementsReceived;
    private String temp;
    
    public XMLReader(){
        factory = SAXParserFactory.newInstance();
        movementsReceived = new ArrayList<CartrackerMovement>();
    }
    
    public List<CartrackerMovement> readXML(String xml){
        try {
            SAXParser sp = factory.newSAXParser();
            XMLReader reader = new XMLReader();
            String filename = stringToXml(xml);
            if (!filename.equals("")){
                sp.parse(filename, reader);
            }
            
            return reader.movementsReceived;
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    @Override
    public void characters(char[] buffer, int start, int length){
        temp = new String(buffer,start,length);
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes){
        if (qName.equalsIgnoreCase("movement")){
            currentMovement = new CartrackerMovement();
            currentMovement.setDateOfMovement(new Date());
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName){
        switch(qName){
            case "cartrackerId":
                currentMovement.setCartrackerId(temp);
                break;
            case "position":
                currentMovement.setPosition(Double.parseDouble(temp));
                break;
            case "speed":
                currentMovement.setSpeed(Double.parseDouble(temp));
                break;
            case "roadId":
                currentMovement.setRoadId(Integer.parseInt(temp));
                break;
            case "movement":
                movementsReceived.add(currentMovement);
                break;
        }
    }
    
    private String stringToXml(String XML){
        try {
            String filename = "movements.xml";
            FileWriter fw = new FileWriter(filename);
            fw.write(XML);
            fw.close();
            return filename;
        } catch (IOException ex) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "";
    }
}
