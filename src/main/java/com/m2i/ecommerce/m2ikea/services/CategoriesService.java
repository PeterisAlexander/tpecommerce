package com.m2i.ecommerce.m2ikea.services;

import java.io.InvalidObjectException;
import java.util.NoSuchElementException;

import com.m2i.ecommerce.m2ikea.entities.CategoriesEntity;
import org.springframework.stereotype.Service;
import com.m2i.ecommerce.m2ikea.repositories.CategoriesRepository;

@Service
public class CategoriesService {

    private CategoriesRepository cr;

    public CategoriesService(CategoriesRepository cr) {
        this.cr = cr;}

    public Iterable<CategoriesEntity> findAll () {
        return cr.findAll();
    }

    public CategoriesEntity findCategories ( int id){
        return cr.findById(id).get();
    }

    public void delete ( int id){
        cr.deleteById(id);
    }

    public void addCategories (CategoriesEntity c) throws InvalidObjectException {
        cr.save(c);
    }

    public void editCategories ( int id, CategoriesEntity c) throws InvalidObjectException {

        try {
            CategoriesEntity caExistant = cr.findById(id).get();
            caExistant.setNomCategorie(c.getNomCategorie());
            caExistant.setDescription(c.getDescription());
            cr.save(caExistant);
        } catch (NoSuchElementException e) {
            throw e;
        }

    }

}
