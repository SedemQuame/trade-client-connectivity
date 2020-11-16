package com.tradingengine.client_connectivity.ops;

import com.tradingengine.client_connectivity.client.Client;
import com.tradingengine.client_connectivity.client.ClientNotFoundException;
import com.tradingengine.client_connectivity.client.ClientRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ModelAssembler implements RepresentationModelAssembler<Client, EntityModel<Client>> {
    @Override
    public EntityModel<Client> toModel(Client employee) {

        try {
            return EntityModel.of(employee, //
                    linkTo(methodOn(ClientRepository.ClientController.class).one(employee.getId())).withSelfRel(),
                    linkTo(methodOn(ClientRepository.ClientController.class).all()).withRel("client"));
        } catch (ClientNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
