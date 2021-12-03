package com.m2i.ecommerce.m2ikea.controller;

import com.m2i.ecommerce.m2ikea.entities.UtilisateursEntity;
import com.m2i.ecommerce.m2ikea.services.StorageServiceImpl;
import com.m2i.ecommerce.m2ikea.services.UtilisateursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.sql.Date;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/admin/user")
public class UtilisateursController {
    @Autowired
    private UtilisateursService us;

    private StorageServiceImpl storageService;

    @GetMapping(value = "")
    public String list(Model model) {
        model.addAttribute("users" , us.findAll() );
        return "pagesAdmin/utilisateurs/list_utilisateurs";
    }

    // http://localhost:8080/admin/user/add
    @GetMapping(value = "/add")
    public String add( Model model ){
        //model.addAttribute("villes" , vservice.findAll() );
        model.addAttribute("user" , new UtilisateursEntity());
        return "pagesAdmin/utilisateurs/addEdit_utilisateur";
    }

    @PostMapping(value = "/add")
    public String addPost( HttpServletRequest request , Model model ){
        // Récupération des paramètres envoyés en POST
        String nom_utilisateur = request.getParameter("nomUtilisateur");
        String email = request.getParameter("email");
        String password = request.getParameter("motdepasse");
        String role = request.getParameter("role");

        // String username, String email, String roles, String password, String name
        // Préparation de l'entité à sauvegarder
        UtilisateursEntity u = new UtilisateursEntity(nom_utilisateur, email, password, role );

        // Enregistrement en utilisant la couche service qui gère déjà nos contraintes
        try{
            us.addUser( u );
        }catch( Exception e ){
            System.out.println( e.getMessage() );
        }

        return "redirect:/admin/user?success=true";
    }

    @RequestMapping( method = { RequestMethod.GET , RequestMethod.POST} , value = "/edit/{id}" )
    public String editGetPost( Model model , @PathVariable int id ,  HttpServletRequest request ){

        if( request.getMethod().equals("POST") ){
            // Récupération des paramètres envoyés en POST
            String nom_utilisateur = request.getParameter("nomUtilisateur");
            String email = request.getParameter("email");
            String password = request.getParameter("motdepasse");
            String role = request.getParameter("role");

            // String username, String email, String roles, String password, String name
            // Préparation de l'entité à sauvegarder
            UtilisateursEntity u = new UtilisateursEntity(nom_utilisateur, email, password, role );

            // Enregistrement en utilisant la couche service qui gère déjà nos contraintes
            try {
                us.editUser( id, u );
            } catch (InvalidObjectException e) {
                e.printStackTrace();
            }

            return "redirect:/admin/user?success=true";
        }else{
            try{
                model.addAttribute("user" , us.findUser( id ) );
            }catch ( NoSuchElementException e ){
                return "redirect:/admin/user?error=User%20introuvalble";
            }

            return "pagesAdmin/utilisateurs/addEdit_utilisateur";
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String delete( @PathVariable int id ){
        String message = "?success=true";
        try{
            us.delete(id);
        }catch ( Exception e ){
            message = "?error=User%20introuvalble";
        }
        return "redirect:/admin/user"+message;
    }

    @PostMapping(value = "/profil/{id}")
    public String editProfil( @PathVariable int id , HttpServletRequest request , @RequestParam("photoProfil") MultipartFile file ) throws IOException {
        // Récupération des paramètres envoyés en POST
        String nom_utilisateur = request.getParameter("nomUtilisateur");
        String email = request.getParameter("email");
        String password = request.getParameter("motdepasse");
        String role = request.getParameter("role");

        //String photo = storageService.store(file , "src\\main\\resources\\static\\images\\uploads");

        // String username, String email, String roles, String password, String name
        // Préparation de l'entité à sauvegarderpassword
        UtilisateursEntity u = new UtilisateursEntity(nom_utilisateur, email, password, role );
        u.setIdUtilisateur( id );

        // Enregistrement en utilisant la couche service qui gère déjà nos contraintes
        try{
            us.editProfil( id, u );
        }catch( Exception e ){
            System.out.println( e.getMessage() );
        }

        // Mettre à jour l'utilisateur ????


        return "redirect:/admin/user?success=true";
    }

    public UtilisateursService getUs() {
        return us;
    }

    public void setUs(UtilisateursService us) {
        this.us = us;
    }
}
