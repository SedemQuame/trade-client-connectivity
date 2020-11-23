package com.tradingengine.client_connectivity.portfolio;

import lombok.Data;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Embeddable
public class Stock {
    @Id @GeneratedValue
    private Long id;
    private String name;
}
