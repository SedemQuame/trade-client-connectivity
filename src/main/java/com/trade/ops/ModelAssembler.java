package com.trade.ops;

import com.trade.models.Client;
import com.trade.exceptions.ClientNotFoundException;
import com.trade.repository.ClientRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ModelAssembler implements RepresentationModelAssembler<Client, EntityModel<Client>> {
    @Override
    public EntityModel<Client> toModel(Client client) {

        try {
            return EntityModel.of(client, //
                    WebMvcLinkBuilder.linkTo(methodOn(ClientRepository.ClientController.class).one(client.getId())).withSelfRel(),
                    linkTo(methodOn(ClientRepository.ClientController.class).all()).withRel("client"));
        } catch (ClientNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
