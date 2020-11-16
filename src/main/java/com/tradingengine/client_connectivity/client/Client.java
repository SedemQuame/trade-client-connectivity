package com.tradingengine.client_connectivity.client;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
public class Client {
    private @Id
    @GeneratedValue
    Long id;
    private String fname;
    private String lname;
    private String email;
    private String password;
    private String cardDetails;
    private String bankBalance;

    public Client(String fname, String lname, String email, String password, String cardDetails, String bankBalance) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.cardDetails = cardDetails;
        this.bankBalance = bankBalance;
    }
}
