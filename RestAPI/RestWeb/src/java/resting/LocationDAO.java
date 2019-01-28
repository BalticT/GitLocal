/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resting;

import java.math.BigDecimal;

/**
 *
 * @author crazzyman
 */
public class LocationDAO {
    
    String country;
    String city;
    String street;
    String gps;

    public LocationDAO() {
    }
    
    

    public LocationDAO(Location loc) {
       setCountry(loc.getCountry());
       setCity(loc.getCity());
               setStreet(loc.getStreet());
               setGps(loc.getGps());
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }
 
    
}
