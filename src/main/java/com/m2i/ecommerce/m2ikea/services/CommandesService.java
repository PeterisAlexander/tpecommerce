package com.m2i.ecommerce.m2ikea.services;

import java.io.InvalidObjectException;
import java.util.NoSuchElementException;
import com.m2i.ecommerce.m2ikea.entities.CommandesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.m2i.ecommerce.m2ikea.repositories.CommandesRepository;

@Service
public class CommandesService {

    @Autowired
    private CommandesRepository commandesRepository;


    public CommandesService(CommandesRepository commandesRepository) {
        this.commandesRepository = commandesRepository;
    }

    public Iterable<CommandesEntity> findAll(String search) {
        if( search != null && search.length() > 0 ){
            return commandesRepository.findByIdCommandeContains( search  );}
        return commandesRepository.findAll();
    }

    public CommandesEntity findCommande (int id) {
        return commandesRepository.findById(id).get();
    }

    public void delete(int id) {
        commandesRepository.deleteById(id);
    }

    public void addCommande(CommandesEntity c) throws InvalidObjectException {
        commandesRepository.save(c);
    }

    public void editCommande(int id, CommandesEntity c) throws InvalidObjectException {

        try{
            CommandesEntity comExistant= commandesRepository.findById(id).get();
            comExistant.setDateCommande(c.getDateCommande());
            comExistant.setDateEnvoi(c.getDateEnvoi());
            comExistant.setPort(c.getPort());
            comExistant.setClient(c.getClient());

            commandesRepository.save(comExistant);
        }catch(NoSuchElementException e){
            throw e;
        }
    }
}