package com.m2i.ecommerce.m2ikea.services;

import com.m2i.ecommerce.m2ikea.entities.UtilisateursEntity;
import com.m2i.ecommerce.m2ikea.repositories.ClientRepository;
import com.m2i.ecommerce.m2ikea.repositories.UtilisateursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.NoSuchElementException;

@Service
public class UtilisateursService {


    private UtilisateursRepository ur;
    private ClientRepository cr;



    public UtilisateursService(UtilisateursRepository ur, ClientRepository cr) {
        this.ur = ur;
        this.cr = cr;
    }

    public Iterable<UtilisateursEntity> findAll () {
        return ur.findAll();
    }

    public UtilisateursEntity findUser( int id){
        return ur.findById(id).get();
    }

    public void delete ( int id){
        ur.deleteById(id);
    }

    public void addUser(UtilisateursEntity u){

        ur.save(u);
    }

    public void editUser ( int id, UtilisateursEntity u) throws InvalidObjectException {

        try {
            UtilisateursEntity ulExistant = ur.findById(id).get();

            ulExistant.setNomUtilisateur(u.getNomUtilisateur());
            ulExistant.setClient(u.getClient());
            ulExistant.setEmail(u.getEmail());
            ulExistant.setRole(u.getRole());

            if( u.getMotdepasse().length() > 0 ){
                ulExistant.setMotdepasse(u.getMotdepasse() );
            }

//            if( u.getImages().length() == 0)
//                ulExistant.setImages( "default.jpg" );
//            else
//                ulExistant.setImages( u.getImages() );

            ur.save( ulExistant );
        } catch (NoSuchElementException e) {
            throw e;
        }

    }

    public void editProfil( int id , UtilisateursEntity u) throws NoSuchElementException {
        try{
            UtilisateursEntity uExistant = ur.findById(id).get();

            uExistant.setEmail( u.getEmail() );
            uExistant.setNomUtilisateur( u.getNomUtilisateur() );

            System.out.println( "------------------" );
            System.out.println( u.getImages() );
            System.out.println( "*************" );

            if( u.getImages() == null )
                uExistant.setImages( "default.jpg" );
            else
                uExistant.setImages( u.getImages() );

            ur.save( uExistant );

        }catch ( NoSuchElementException e ){
            throw e;
        }
    }
}
