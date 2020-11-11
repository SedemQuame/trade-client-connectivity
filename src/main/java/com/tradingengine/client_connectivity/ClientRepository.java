package com.tradingengine.client_connectivity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @RestController
    class ClientController {
        private final ClientRepository clientList;
        private final ModelAssembler assembler;

        public ClientController(ClientRepository clients, ModelAssembler assembler) {
            this.clientList = clients;
            this.assembler = assembler;
        }

        @GetMapping("/")
        public String welcome(){
            return "Client Connectivity Service";
        }

        @GetMapping("/clients")
        public CollectionModel<EntityModel<Client>> all(){
            List<EntityModel<Client>> clients = clientList.findAll().stream()
                    .map(assembler::toModel)
                    .collect(Collectors.toList());
            return CollectionModel.of(clients,
                    linkTo(methodOn(ClientController.class).all()).withSelfRel());
        }

        @PostMapping("/client")
        Client newClient(@RequestBody Client newClient){
            return clientList.save(newClient);
        }

        @GetMapping("/client/{id}")
        EntityModel<Client> one(@PathVariable Long id) throws ClientNotFoundException {
            Client client = clientList.findById(id)
                    .orElseThrow(() -> new ClientNotFoundException(id));

            return EntityModel.of(client,
                    linkTo(methodOn(ClientController.class).one(client.getId())).withSelfRel(),
                    linkTo(methodOn(ClientController.class).all()).withRel("clients"));
        }

        @GetMapping("/validate")
        public Boolean validateClient(@RequestBody Client clientToValidate){
            return true;
        }

        @GetMapping("/client/{id}/create/porfolio")
        public Boolean createPortfolio(@RequestBody Portfolio newPortfolio){
            return true;
        }

        @GetMapping("/client/{id}/get/porfolio")
        public Boolean getAllPortfolios(@PathVariable Long id){
            return true;
        }

        @GetMapping("/client/{id}/get/porfolio/{porfolioId}")
        public Boolean getPortfolioById(@PathVariable Long id) throws ClientNotFoundException {
            Client client = clientList.findById(id)
                    .orElseThrow(() -> new ClientNotFoundException(id));

//            client.
            return true;
        }

        @PutMapping("/client/{id}")
        Client replaceEmployee(@RequestBody Client newClient, @PathVariable Long id) {
            return clientList.findById(id)
                    .map(client -> {
                        client.setName(newClient.getName());
                        client.setPassword(newClient.getPassword());
                        client.setCardDetails(newClient.getCardDetails());
                        return clientList.save(client);
                    }).orElseGet(() -> {
                        newClient.setId(id);
                        return  clientList.save(newClient);
                    });
        }
        
        @DeleteMapping("/client/{id}")
        void deleteEmployee(@PathVariable Long id){
            clientList.deleteById(id);
        }

    }
}
