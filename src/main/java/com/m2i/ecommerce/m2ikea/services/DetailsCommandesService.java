package com.m2i.ecommerce.m2ikea.services;

import java.io.InvalidObjectException;
import java.util.NoSuchElementException;

import com.m2i.ecommerce.m2ikea.entities.DetailsCommandesEntity;
import org.springframework.stereotype.Service;
import com.m2i.ecommerce.m2ikea.repositories.DetailsCommandesRepository;

@Service
public class DetailsCommandesService {

    private DetailsCommandesRepository detailsRepository;


    public DetailsCommandesService(DetailsCommandesRepository detailsRepository) {
        this.detailsRepository = detailsRepository;
    }

    public Iterable<DetailsCommandesEntity> findAll() {
        return detailsRepository.findAll();
    }

    public DetailsCommandesEntity findDetail (int id) {
        return detailsRepository.findById(id).get();
    }

    public void delete(int id) {
        detailsRepository.deleteById(id);
    }

    public void addDetail(DetailsCommandesEntity dc) throws InvalidObjectException {
        detailsRepository.save(dc);
    }

    public void editDetail(int id, DetailsCommandesEntity dc) throws InvalidObjectException {

        try{
            DetailsCommandesEntity dcExistant= detailsRepository.findById(id).get();
            dcExistant.setPrixUnitaire(dc.getPrixUnitaire());
            dcExistant.setQuantite(dc.getQuantite());
            dcExistant.setRemise(dc.getRemise());
            dcExistant.setProduit(dc.getProduit());



            detailsRepository.save(dcExistant);}
        catch(NoSuchElementException e){
            throw e;
        }
    }
}