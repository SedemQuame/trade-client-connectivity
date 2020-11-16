package com.tradingengine.client_connectivity.client.portfolio;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Embeddable
@Data
public class Portfolio {
//    @Id
//    @GeneratedValue
    private Long id;
    private String stock;
    private String Status;
}
