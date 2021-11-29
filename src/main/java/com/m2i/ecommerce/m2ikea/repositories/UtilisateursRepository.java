package com.m2i.ecommerce.m2ikea.repositories;

import com.m2i.ecommerce.m2ikea.entities.UtilisateursEntity;
import org.springframework.data.repository.CrudRepository;

public interface UtilisateursRepository extends CrudRepository<UtilisateursEntity, Integer> {
    Iterable<UtilisateursEntity> findAll();

    void deleteByIdUtilisateur(int id);
}
