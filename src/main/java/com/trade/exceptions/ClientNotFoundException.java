package com.trade.exceptions;

public class ClientNotFoundException extends Exception{
    public ClientNotFoundException(Long id) {
        super("Client, with id " + id + " not found");
    }
}
