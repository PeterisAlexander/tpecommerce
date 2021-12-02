package com.m2i.ecommerce.m2ikea.repositories;
import com.m2i.ecommerce.m2ikea.entities.CommandesEntity;
import org.springframework.data.repository.CrudRepository;
import com.m2i.ecommerce.m2ikea.entities.DetailsCommandesEntity;

    public interface DetailsCommandesRepository extends CrudRepository<DetailsCommandesEntity, Integer> {

        Iterable<DetailsCommandesEntity> findAll();
        Iterable<CommandesEntity> findByIdCommandeContains(String search);
        void deleteById(int id);
    }
