package com.m2i.ecommerce.m2ikea.repositories;
import com.m2i.ecommerce.m2ikea.entities.ClientsEntity;
import com.m2i.ecommerce.m2ikea.entities.ProduitsEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProduitsRepository extends CrudRepository<ProduitsEntity, Integer>{

    Iterable<ProduitsEntity> findAll();

    Iterable<ProduitsEntity> findByNomProduitContains( String nomProduit );

    void deleteById(int id);
}
