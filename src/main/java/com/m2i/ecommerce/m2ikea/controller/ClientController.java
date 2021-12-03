package com.m2i.ecommerce.m2ikea.controller;


import com.m2i.ecommerce.m2ikea.entities.CategoriesEntity;
import com.m2i.ecommerce.m2ikea.entities.ClientsEntity;
import com.m2i.ecommerce.m2ikea.entities.ProduitsEntity;
import com.m2i.ecommerce.m2ikea.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/client/client")
public class ClientController {

    @Autowired
    private ClientService cs;

    @GetMapping(value = "/{id}")
    public String list(Model model, HttpServletRequest request, @PathVariable int id) {
        model.addAttribute("client", cs.findClient(id));
        model.addAttribute("error", request.getParameter("error"));
        model.addAttribute("success", request.getParameter("success"));

        return "pagesClient/client/list_clients";
    }

    @GetMapping(value = "/add")
    public String add(Model model) {
        model.addAttribute("client", new ClientsEntity());
        return "pagesClient/client/add_edit_clients";
    }

    @PostMapping(value = "/add")
    public String addPost(HttpServletRequest request, Model model) {

        String nomClient = request.getParameter("nomClient");
        String adresse = request.getParameter("adresse");
        String ville = request.getParameter("ville");
        String codePostal = request.getParameter("codePostal");
        String pays = request.getParameter("pays");
        String telephone = request.getParameter("telephone");

        ClientsEntity c=new ClientsEntity(0,nomClient, adresse, ville, codePostal, pays, telephone);

        try{
            cs.addClient(c);
        }catch( Exception e ){
            System.out.println( e.getMessage() );
            model.addAttribute("client" , c );
            model.addAttribute("error" , e.getMessage() );
            return "pagesClient/client/add_edit_client";
        }
        return "redirect:/client/client?success=true";

    }

    @GetMapping(value = "/edit/{id}")
    public String edit( Model model , @PathVariable int id ){
        try{
            model.addAttribute("client" , cs.findClient(id) );
        }catch ( NoSuchElementException e ){
            return "redirect:/client/client?error=Produit%20introuvable";
        }
        return "pagesClient/client/add_edit_client";
    }

    @PostMapping(value = "/edit/{id}")
    public String editPost (HttpServletRequest request, @PathVariable int id, Model model){

        String nomClient = request.getParameter("nomClient");
        String adresse = request.getParameter("adresse");
        String ville = request.getParameter("ville");
        String codePostal = request.getParameter("codePostal");
        String pays = request.getParameter("pays");
        String telephone = request.getParameter("telephone");

        ClientsEntity c=new ClientsEntity(0,nomClient, adresse, ville, codePostal, pays, telephone);

        try {
            cs.editClient(id, c);
        }catch( Exception e ){
            System.out.println( e.getMessage() );
            model.addAttribute("client" , c );
            model.addAttribute("error" , e.getMessage() );
            return "pagesClient/client/add_edit_client";
        }
        return "redirect:/client/client?success=true";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete (@PathVariable int id){
        String message = "?success=true";
        try {
            cs.delete(id);
        }catch ( Exception e ){
            message = "?error=Client%20introuvable";
        }
        return "redirect:/client/client"+message;
    }
}
