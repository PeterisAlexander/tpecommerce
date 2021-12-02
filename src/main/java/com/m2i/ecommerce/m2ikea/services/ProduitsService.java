package com.m2i.ecommerce.m2ikea.services;

import java.io.InvalidObjectException;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import com.m2i.ecommerce.m2ikea.entities.ProduitsEntity;
import com.m2i.ecommerce.m2ikea.repositories.ProduitsRepository;

@Service
public class ProduitsService {

    private ProduitsRepository pr;

    public ProduitsService(ProduitsRepository pr) {
        this.pr = pr;}

    private void checkProduit( ProduitsEntity p ) throws InvalidObjectException {

        if (p.getNomProduit().length() <= 3) {
            throw new InvalidObjectException("Nom produit invalide");
        }

        if (p.getPrixUnitaire() < 0) {
            throw new InvalidObjectException("Prix unitaire invalide");
        }

        if (p.getDescription().length() <= 3) {
            throw new InvalidObjectException("Description produit invalide");
        }

        if (p.getUnitesCommandees() < 0) {
            throw new InvalidObjectException("Nombre unites commandées invalide");
        }

        if (p.getUnitesStock() < 0) {
            throw new InvalidObjectException("Nombre unites en stock invalide");
        }

        if (p.getQuantite() < 0) {
            throw new InvalidObjectException("Quantité invalide");
        }
    }

    public Iterable<ProduitsEntity> findAll () {
        return pr.findAll();
    }

    public Iterable<ProduitsEntity> findAll( String search ) {
        if( search != null && search.length() > 0 ){
            return pr.findByNomProduitContains( search );
        }
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
        checkProduit(p);
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
