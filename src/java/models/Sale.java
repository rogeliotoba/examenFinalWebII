/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author rogeliotorres
 */


public class Sale {
    Integer id;
    Integer UserId;
    Float TotalAmount;
    
    public Sale(){};

    public Sale(Integer id, Integer UserId, Float TotalAmount) {
        this.id = id;
        this.UserId = UserId;
        this.TotalAmount = TotalAmount;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer UserId) {
        this.UserId = UserId;
    }

    public Float getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(Float TotalAmount) {
        this.TotalAmount = TotalAmount;
    }
    
    
}
