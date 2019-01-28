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
public class Location {
    
    String country;
    String city;
    String street;
    String gps;

    public Location() {
    }
    
    

    public Location(String country, String city, String street, String gps) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.gps = gps;
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
