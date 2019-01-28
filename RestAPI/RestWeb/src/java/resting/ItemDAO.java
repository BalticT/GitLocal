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
public class ItemDAO {
    
     
    private Long id;
    private String title;
    private String description;
    private Double price;
    private Integer stock;
    private Location location; 

    public ItemDAO() {
    }
    
    public ItemDAO (Item item) {
        
        setId(item.getId());
        setTitle(item.getTitle());
        setDescription(item.getDescription());
                setPrice(item.getPrice());
                        setStock(item.getStock());
                        setLocation(item.getLocation() );
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        if (stock < 0) {
            this.stock = stock *(-1);
        } else {
        
        this.stock = stock;
        } 
        
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


}


