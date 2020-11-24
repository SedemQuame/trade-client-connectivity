package com.trade.services;

import com.trade.exceptions.ClientNotFoundException;
import com.trade.models.AuthenticatedResponse;
import com.trade.repository.ClientRepository;
import com.trade.models.Client;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class Authenticator {
//    @Autowired
    private final ClientRepository clientList;

    public Authenticator(ClientRepository clientList) {
        this.clientList = clientList;
    }

    @GetMapping("/client/authentication")
    @CrossOrigin
    AuthenticatedResponse authentication(@RequestParam String email, @RequestParam String password){
//        find client with the user's name
//        create query for find clients in client list using the client email address
        AuthenticatedResponse response = null;
        try {
            Client clientToAuthenticate = clientList.findByEmail(email);
            if((clientToAuthenticate.getEmail()).equals(email) && (clientToAuthenticate.getPassword()).equals(password)){
                response = new AuthenticatedResponse(String.valueOf(clientToAuthenticate.getId()), "authenticated");
                return response;
            }
            else{
                response = new AuthenticatedResponse(null, "failed");
                return response;
            }
        }catch(ClientNotFoundException e){
            response = new AuthenticatedResponse(null, "failed");
            return response;
        }catch (NullPointerException e){
            response = new AuthenticatedResponse(null, "failed");
            return response;
        }
    }
}
