package com.m2i.ecommerce.m2ikea.services;

import java.io.InvalidObjectException;
import java.util.NoSuchElementException;

import com.m2i.ecommerce.m2ikea.entities.ProduitsEntity;
import org.springframework.stereotype.Service;
import com.m2i.ecommerce.m2ikea.repositories.ProduitsRepository;

@Service
public class ProduitsService {

    private ProduitsRepository pr;

    public ProduitsService(ProduitsRepository pr) {
        this.pr = pr;}

    public Iterable<ProduitsEntity> findAll () {
        return pr.findAll();
    }

    public ProduitsEntity findProduit ( int id){
        return pr.findById(id).get();
    }

    public void delete ( int id){
        pr.deleteById(id);
    }

    public void addProduit (ProduitsEntity p) throws InvalidObjectException {
        pr.save(p);
    }

    public void editProduit ( int id, ProduitsEntity p) throws InvalidObjectException {

        try {
            ProduitsEntity prExistant = pr.findById(id).get();
            prExistant.setNomProduit(p.getNomProduit());
            prExistant.setQuantite(p.getQuantite());
            prExistant.setPrixUnitaire(p.getPrixUnitaire());
            prExistant.setUnitesStock(p.getUnitesStock());
            prExistant.setUnitesCommandees(p.getUnitesCommandees());
            prExistant.setIndisponible(p.getIndisponible());
            prExistant.setDescription(p.getDescription());


            pr.save(prExistant);
        } catch (NoSuchElementException e) {
            throw e;
        }

    }

}
