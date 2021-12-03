package com.m2i.ecommerce.m2ikea.repositories;
import com.m2i.ecommerce.m2ikea.entities.CommandesEntity;
import org.springframework.data.repository.CrudRepository;

public interface CommandesRepository extends CrudRepository<CommandesEntity, Integer> {

        Iterable<CommandesEntity> findAll();

        //Iterable<CategoriesEntity> findById(int id);

        void deleteById(int id);
    }
