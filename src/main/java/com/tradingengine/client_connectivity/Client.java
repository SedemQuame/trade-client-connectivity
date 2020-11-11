package com.tradingengine.client_connectivity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Client {
    //    member variables
    private @Id
    @GeneratedValue
    Long id;
    private String name;
    private String password;
    private String cardDetails;
    private String bankBalance;

    //    Constructors
    public Client() {
    }

    public Client(String name, String password, String cardDetails, String bankBalance) {
        this.name = name;
        this.password = password;
        this.cardDetails = cardDetails;
        this.bankBalance = bankBalance;
    }

    //    Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getCardDetails() {
        return cardDetails;
    }

    public String getBankBalance() {
        return bankBalance;
    }

    //    Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCardDetails(String cardDetails) {
        this.cardDetails = cardDetails;
    }

    public void setBankBalance(String bankBalance) {
        this.bankBalance = bankBalance;
    }

    //    Equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Client))
            return false;
        Client employee = (Client) obj;
        return Objects.equals(this.id, employee.id) && Objects.equals(this.name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id,this.name, this.cardDetails, this.bankBalance);
    }

    @Override
    public String toString() {
        return "Client{" +
                "client_id " + this.id +
                "name " + this.name +
                "cardDetails " + this.cardDetails +
                "bankBalance " + this.bankBalance +
                "}";
    }
}
