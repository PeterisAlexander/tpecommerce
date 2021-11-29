package com.m2i.ecommerce.m2ikea.repositories;
import com.m2i.ecommerce.m2ikea.entities.CategoriesEntity;
import org.springframework.data.repository.CrudRepository;

public interface CategoriesRepository extends CrudRepository<CategoriesEntity, Integer>{

    Iterable<CategoriesEntity> findAll();

    void deleteById(int id);
}
