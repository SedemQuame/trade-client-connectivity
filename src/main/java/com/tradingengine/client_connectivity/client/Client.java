package com.tradingengine.client_connectivity.client;

import com.tradingengine.client_connectivity.client.portfolio.Portfolio;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
public class Client {
    private @Id @GeneratedValue Long id;
    private String fname;
    private String lname;
    private String email;
    private String password;
    private String cardDetails;
    private String bankBalance;
    @ElementCollection
    private List<Portfolio> userPortfolio;

    public Client(String fname, String lname, String email, String password, String cardDetails, String bankBalance) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.cardDetails = cardDetails;
        this.bankBalance = bankBalance;
        this.userPortfolio = new ArrayList<Portfolio>();
    }

    public Client() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCardDetails() {
        return cardDetails;
    }

    public void setCardDetails(String cardDetails) {
        this.cardDetails = cardDetails;
    }

    public String getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(String bankBalance) {
        this.bankBalance = bankBalance;
    }

    public List<Portfolio> getUserPortfolio() {
        return userPortfolio;
    }

    public void setUserPortfolio(List<Portfolio> userPortfolio) {
        this.userPortfolio = userPortfolio;
    }

    public void addPortfolio(Portfolio portfolio){
        this.userPortfolio.add(portfolio);
    }

    public void removePortfolio(Portfolio portfolio){
        this.userPortfolio.remove(portfolio);
    }
}
