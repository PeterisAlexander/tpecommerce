package com.m2i.ecommerce.m2ikea.api;

import com.m2i.ecommerce.m2ikea.entities.ClientsEntity;
import com.m2i.ecommerce.m2ikea.entities.CommandesEntity;
import com.m2i.ecommerce.m2ikea.services.CommandesService;
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

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/commande")
public class CommandesAPIController {
    CommandesService cs;

    public CommandesAPIController( CommandesService cs ){
        this.cs = cs;}

    @GetMapping(value="" , produces = "application/json")
    public Iterable<CommandesEntity> getAll(HttpServletRequest request){
        String search = request.getParameter("search");
        return cs.findAll(search);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CommandesEntity> get(@PathVariable int id) {
        try{
            CommandesEntity c = cs.findCommande(id);
            return ResponseEntity.ok(c);
        }catch ( Exception e ){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "", consumes = "application/json")
    public ResponseEntity<CommandesEntity> add(@RequestBody CommandesEntity c) {
        System.out.println(c);
        try {
            cs.addCommande(c);
            //creation de url d'access au nouvel objet => http://localhost:80/api/categorie/20
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getIdCommande()).toUri();
            return ResponseEntity.created(uri).body(c);
        } catch (InvalidObjectException e) {
            //return ResponseEntity.badRequest().build();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public void update(@PathVariable int id, @RequestBody CommandesEntity c) {
        try {
            cs.editCommande(id, c);
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