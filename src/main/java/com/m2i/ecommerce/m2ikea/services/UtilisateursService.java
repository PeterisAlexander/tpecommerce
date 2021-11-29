package com.m2i.ecommerce.m2ikea.services;

import com.m2i.ecommerce.m2ikea.entities.UtilisateursEntity;
import com.m2i.ecommerce.m2ikea.repositories.UtilisateursRepository;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.NoSuchElementException;

@Service
public class UtilisateursService {


    private UtilisateursRepository ur;


    public UtilisateursService(UtilisateursRepository ur) {
        this.ur = ur;}

    public Iterable<UtilisateursEntity> findAll () {
        return ur.findAll();
    }

    public UtilisateursEntity findUser( int id){
        return ur.findById(id).get();
    }

    public void delete ( int id){
        ur.deleteById(id);
    }

    public void addUser (UtilisateursEntity u) throws InvalidObjectException {
        ur.save(u);
    }

    public void editUser ( int id, UtilisateursEntity u) throws InvalidObjectException {

        try {
            UtilisateursEntity ulExistant = ur.findById(id).get();
            ulExistant.setNomUtilisateur(u.getNomUtilisateur());
            ulExistant.setNomComplet(u.getNomComplet());
            ulExistant.setEmail(u.getEmail());
            ulExistant.setMotdepasse(u.getMotdepasse());
            ulExistant.setRole(u.getRole());
            ulExistant.setImages(u.getImages());


            ur.save(ulExistant);
        } catch (NoSuchElementException e) {
            throw e;
        }

    }
}
