package com.m2i.ecommerce.m2ikea.repositories;
import com.m2i.ecommerce.m2ikea.entities.CommandesEntity;
import com.m2i.ecommerce.m2ikea.entities.DetailsCommandesEntity;
import org.springframework.data.repository.CrudRepository;

public interface DetailsCommandesRepository extends CrudRepository<DetailsCommandesEntity, Integer> {

        Iterable<DetailsCommandesEntity> findAll();
        Iterable<CommandesEntity> findByIdCommandeContains(String search);
        void deleteById(int id);
    }
