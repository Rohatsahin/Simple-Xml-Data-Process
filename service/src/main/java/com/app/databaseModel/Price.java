/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.databaseModel;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author rohat
 */
@Embeddable
public class Price {

    @Column(name = "price")
    private float price;

    @Column(name = "date")
    private String date;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
