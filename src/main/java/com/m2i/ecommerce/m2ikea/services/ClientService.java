package com.m2i.ecommerce.m2ikea.services;

import com.m2i.ecommerce.m2ikea.entities.ClientsEntity;
import com.m2i.ecommerce.m2ikea.repositories.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private ClientRepository cr;

    public ClientService(ClientRepository cr) {
        this.cr = cr;
    }

    public Iterable<ClientsEntity> findAll() {
        return cr.findAll();
    }

    public ClientsEntity findClient(int id) {
        return (ClientsEntity) cr.findById(id).get();
    }
}