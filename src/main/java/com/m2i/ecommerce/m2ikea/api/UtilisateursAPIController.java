package com.m2i.ecommerce.m2ikea.api;

import com.m2i.ecommerce.m2ikea.entities.UtilisateursEntity;
import com.m2i.ecommerce.m2ikea.services.UtilisateursService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.InvalidObjectException;
import java.net.URI;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/user")
public class UtilisateursAPIController {
    UtilisateursService us;

    public UtilisateursAPIController( UtilisateursService us ){
        this.us = us;
    }

    @GetMapping(value="" , produces = "application/json")
    public Iterable<UtilisateursEntity> getAll(){
        return us.findAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UtilisateursEntity> get(@PathVariable int id) {
        try{
            UtilisateursEntity u = us.findUser(id);
            return ResponseEntity.ok(u);
        }catch ( Exception e ){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "", consumes = "application/json")
    public ResponseEntity<UtilisateursEntity> add(@RequestBody UtilisateursEntity u) {
        System.out.println(u);
        us.addUser(u);
        //creation de url d'access au nouvel objet => http://localhost:80/api/utilisateur/20
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(u.getIdUtilisateur()).toUri();
        return ResponseEntity.created(uri).body(u);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public void update(@PathVariable int id, @RequestBody UtilisateursEntity u) {
        try {
            us.editUser(id, u);
        } catch (InvalidObjectException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable");
        }
    }

    @DeleteMapping  (value="/{id}", produces = "application/json")
    public ResponseEntity<Object> delete(@PathVariable int id){
        try {
            us.delete(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
