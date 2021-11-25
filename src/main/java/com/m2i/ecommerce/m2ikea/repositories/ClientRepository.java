package com.m2i.ecommerce.m2ikea.repositories;

import com.m2i.ecommerce.m2ikea.entities.ClientsEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository<T> extends CrudRepository<ClientsEntity, Integer> { }