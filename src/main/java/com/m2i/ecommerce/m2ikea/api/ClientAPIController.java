package com.m2i.ecommerce.m2ikea.api;

import com.m2i.ecommerce.m2ikea.entities.ClientsEntity;
import com.m2i.ecommerce.m2ikea.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class ClientAPIController {
    ClientService cs;

    public ClientAPIController( ClientService cs ){
        this.cs = cs;
    }

    @GetMapping(value="" , produces = "application/json")
    public Iterable<ClientsEntity> getAll(){
        return cs.findAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ClientsEntity> get(@PathVariable int id) {
        try{
            ClientsEntity c = cs.findClient(id);
            return ResponseEntity.ok(c);
        }catch ( Exception e ){
            return ResponseEntity.notFound().build();
        }
    }
}