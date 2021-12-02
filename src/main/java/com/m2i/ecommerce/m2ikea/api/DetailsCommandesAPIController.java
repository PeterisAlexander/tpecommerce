package com.m2i.ecommerce.m2ikea.api;

import java.io.InvalidObjectException;
import java.net.URI;
import java.util.NoSuchElementException;

import com.m2i.ecommerce.m2ikea.entities.DetailsCommandesEntity;
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

import com.m2i.ecommerce.m2ikea.services.DetailsCommandesService;

@RestController
@RequestMapping("/api/detail")
public class DetailsCommandesAPIController {
    DetailsCommandesService ds;

    public DetailsCommandesAPIController( DetailsCommandesService ds ){
        this.ds = ds;
    }

    @GetMapping(value="" , produces = "application/json")
    public Iterable<DetailsCommandesEntity> getAll(){
        return ds.findAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<DetailsCommandesEntity> get(@PathVariable int id) {
        try{
            DetailsCommandesEntity dc = ds.findDetail(id);
            return ResponseEntity.ok(dc);
        }catch ( Exception e ){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "", consumes = "application/json")
    public ResponseEntity<DetailsCommandesEntity> add(@RequestBody DetailsCommandesEntity dc) {
        System.out.println(dc);
        try {
            ds.addDetail(dc);
            //creation de url d'access au nouvel objet => http://localhost:80/api/categorie/20
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dc.getIdCommande()).toUri();
            return ResponseEntity.created(uri).body(dc);
        } catch (InvalidObjectException e) {
            //return ResponseEntity.badRequest().build();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public void update(@PathVariable int id, @RequestBody DetailsCommandesEntity dc) {
        try {
            ds.editDetail(id, dc);
        } catch (InvalidObjectException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient introuvable");
        }
    }

    @DeleteMapping  (value="/{id}", produces = "application/json")
    public ResponseEntity<Object> delete(@PathVariable int id){
        try {
            ds.delete(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}