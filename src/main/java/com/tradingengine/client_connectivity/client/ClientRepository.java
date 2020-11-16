package com.tradingengine.client_connectivity.client;

import com.tradingengine.client_connectivity.client.portfolio.Portfolio;
import com.tradingengine.client_connectivity.ops.ModelAssembler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByEmail(String email) throws ClientNotFoundException;

    @RestController
    class ClientController {
        private final ClientRepository clientList;
        private final ModelAssembler assembler;

        public ClientController(ClientRepository clients, ModelAssembler assembler) {
            this.clientList = clients;
            this.assembler = assembler;
        }

//        test client route
        @GetMapping("/clients")
        public String welcome() {
            return "Client Connectivity Service";
        }

        @GetMapping("/client/login")
        public ModelAndView login(ModelMap map, @RequestBody Credentials clientToAuthenticate) {
            map.addAttribute("email", clientToAuthenticate.getEmail());
            map.addAttribute("password", clientToAuthenticate.getPassword());
            return new ModelAndView("redirect:/client/authentication", map);
        }

        @PostMapping("/client/{id}/create/portfolio")
        public ModelAndView createPortfolioByUserId(@PathVariable Long id, @RequestBody Portfolio portfolio){
            clientList.findById(id).map(client -> {
                client.addPortfolio(portfolio);
                return clientList.save(client);
            });
            return new ModelAndView("redirect:/client/get/" + id);
        }

        @PostMapping("/client/{clientId}/close/portfolio/{portfolioId}")
        public ModelAndView closePortfolioById(@PathVariable Long clientId, @PathVariable Long portfolioId){
            clientList.findById(clientId).map(client -> {
                client.getUserPortfolio().remove(portfolioId);
                return clientList.save(client);
            });
            return new ModelAndView("redirect:/client/get/" + clientId);
        }

//        create new client accounts
        @PostMapping("/client/create")
        Client newClient(@RequestBody Client newClient) {
            return clientList.save(newClient);
        }

//        return all registered users in the system
        @GetMapping("/clients/all")
        public CollectionModel<EntityModel<Client>> all() {
            List<EntityModel<Client>> clients = clientList.findAll().stream()
                    .map(assembler::toModel)
                    .collect(Collectors.toList());
            return CollectionModel.of(clients,
                    linkTo(methodOn(ClientController.class).all()).withSelfRel());
        }

//       filter clients in database by id
        @GetMapping("/client/get/{id}")
        public EntityModel<Client> one(@PathVariable Long id) throws ClientNotFoundException {
            Client client = clientList.findById(id)
                    .orElseThrow(() -> new ClientNotFoundException(id));
            return EntityModel.of(client,
                    linkTo(methodOn(ClientController.class).one(client.getId())).withSelfRel(),
                    linkTo(methodOn(ClientController.class).all()).withRel("clients"));
        }

//          update client documents by id
        @PutMapping("/client/update/{id}")
        Client replaceEmployee(@RequestBody Client newClient, @PathVariable Long id) {
            return clientList.findById(id)
                .map(client -> {
                    client.setFname(newClient.getFname());
                    client.setLname(newClient.getLname());
                    client.setEmail(newClient.getEmail());
                    client.setPassword(newClient.getPassword());
                    client.setCardDetails(newClient.getCardDetails());
                    client.setBankBalance(newClient.getBankBalance());
                    return clientList.save(client);
                }).orElseGet(() -> {
                    newClient.setId(id);
                    return clientList.save(newClient);
                });
        }

//        Delete clients by id.
        @DeleteMapping("/client/{id}")
        void deleteEmployee(@PathVariable Long id) {
            clientList.deleteById(id);
        }
    }
}
