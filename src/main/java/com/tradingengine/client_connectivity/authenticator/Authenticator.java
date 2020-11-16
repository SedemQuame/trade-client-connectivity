package com.tradingengine.client_connectivity.authenticator;

import com.tradingengine.client_connectivity.client.Client;
import com.tradingengine.client_connectivity.client.ClientNotFoundException;
import com.tradingengine.client_connectivity.client.ClientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Authenticator {
//    @Autowired
    private final ClientRepository clientList;

    public Authenticator(ClientRepository clientList) {
        this.clientList = clientList;
    }

    @GetMapping("/client/authentication")
    String authentication(@RequestParam String email, @RequestParam String password){
//        find client with the user's name
//        create query for find clients in client list using the client email address
        try {
            Client clientToAuthenticate = clientList.findByEmail(email);
            if((clientToAuthenticate.getEmail()).equals(email) && (clientToAuthenticate.getPassword()).equals(password))
                return "Passed authentication";
            else
                return "Failed authentication";
        }catch(ClientNotFoundException e){
            return "Failed authentication";
        }catch (NullPointerException e){
            return "Failed authentication";
        }
    }
}
