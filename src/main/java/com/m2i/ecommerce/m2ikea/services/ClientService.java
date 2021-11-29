package com.m2i.ecommerce.m2ikea.services;

import java.io.InvalidObjectException;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import com.m2i.ecommerce.m2ikea.entities.ClientsEntity;
import com.m2i.ecommerce.m2ikea.repositories.ClientRepository;

@Service
public class ClientService {

    private ClientRepository cr;


    public ClientService(ClientRepository cr) {
        this.cr = cr;}

        public Iterable<ClientsEntity> findAll () {
            return cr.findAll();
        }

        public ClientsEntity findClient ( int id){
            return cr.findById(id).get();
        }

        public void delete ( int id){
            cr.deleteById(id);
        }

        public void addClient (ClientsEntity c) throws InvalidObjectException {
            cr.save(c);
        }

        public void editClient ( int id, ClientsEntity c) throws InvalidObjectException {

            try {
                ClientsEntity clExistant = cr.findById(id).get();
                clExistant.setNomClient(c.getNomClient());
                clExistant.setAdresse(c.getAdresse());
                clExistant.setVille(c.getVille());
                clExistant.setCodePostal(c.getCodePostal());
                clExistant.setPays(c.getPays());
                clExistant.setTelephone(c.getTelephone());


                cr.save(clExistant);
            } catch (NoSuchElementException e) {
                throw e;
            }

        }
    }
