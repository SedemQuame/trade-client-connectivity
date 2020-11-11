package com.tradingengine.client_connectivity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

public class Portfolio {
    private String name;

    public Portfolio(String name) {
        this.name = name;
    }

    public Portfolio() {
        
    }

    public String getPortfolioName() {
        return name;
    }

    public void setPortfolioName(String name) {
        this.name = name;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    @Id
//    public Long getId() {
//        return id;
//    }
}
