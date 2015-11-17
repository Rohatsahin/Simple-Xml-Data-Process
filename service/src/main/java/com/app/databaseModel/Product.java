/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.databaseModel;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

/**
 *
 * @author rohat
 */
@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "IDProduct")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "brand")
    private String brand;
    @Column(name = "category")
    private String category;
    @Column(name = "url")
    private String url;
    @ElementCollection(fetch=FetchType.EAGER)
    @JoinTable(name="Prices",joinColumns=@JoinColumn(name="IDProduct"))
    private Set<Price> prices = new HashSet<Price>();

    public Set<Price> getPrice() {
        return prices;
    }

    public void setPrice(Set<Price> prices) {
        this.prices = prices;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
