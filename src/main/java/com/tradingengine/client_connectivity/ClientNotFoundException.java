package com.tradingengine.client_connectivity;

public class ClientNotFoundException extends Exception{
    public ClientNotFoundException(Long id) {
        super("Client, with id " + id + " not found");
    }
}
