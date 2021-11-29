package com.m2i.ecommerce.m2ikea.api;

import com.m2i.ecommerce.m2ikea.entities.ClientsEntity;
import com.m2i.ecommerce.m2ikea.services.ClientService;
import java.io.InvalidObjectException;
import java.net.URI;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/client")
public class ClientAPIController {
    ClientService cs;

    public ClientAPIController( ClientService cs ){
        this.cs = cs;}

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

    @PostMapping(value = "", consumes = "application/json")
    public ResponseEntity<ClientsEntity> add(@RequestBody ClientsEntity c) {
        System.out.println(c);
        try {
            cs.addClient(c);
            //creation de url d'access au nouvel objet => http://localhost:80/api/categorie/20
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getIdClient()).toUri();
            return ResponseEntity.created(uri).body(c);
        } catch (InvalidObjectException e) {
            //return ResponseEntity.badRequest().build();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public void update(@PathVariable int id, @RequestBody ClientsEntity c) {
        try {
            cs.editClient(id, c);
        } catch (InvalidObjectException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient introuvable");
        }
    }

    @DeleteMapping  (value="/{id}", produces = "application/json")
    public ResponseEntity<Object> delete(@PathVariable int id){
        try {
            cs.delete(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}