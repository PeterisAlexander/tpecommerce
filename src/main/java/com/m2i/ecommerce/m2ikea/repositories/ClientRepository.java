package com.m2i.ecommerce.m2ikea.repositories;
import com.m2i.ecommerce.m2ikea.entities.ClientsEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<ClientsEntity, Integer>{

    Iterable<ClientsEntity> findAll();

    void deleteById(int id);
} 