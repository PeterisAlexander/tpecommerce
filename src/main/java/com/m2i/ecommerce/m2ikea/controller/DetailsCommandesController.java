package com.m2i.ecommerce.m2ikea.controller;

import com.m2i.ecommerce.m2ikea.entities.*;
import com.m2i.ecommerce.m2ikea.services.DetailsCommandesService;
import com.m2i.ecommerce.m2ikea.services.ProduitsService;
import com.m2i.ecommerce.m2ikea.entities.ClientsEntity;
import com.m2i.ecommerce.m2ikea.entities.CommandesEntity;
import com.m2i.ecommerce.m2ikea.entities.ProduitsEntity;
import com.m2i.ecommerce.m2ikea.entities.CategoriesEntity;
import com.m2i.ecommerce.m2ikea.services.ClientService;
import com.m2i.ecommerce.m2ikea.services.CommandesService;
import com.m2i.ecommerce.m2ikea.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/client/detail")
public class DetailsCommandesController {

    @Autowired
    private DetailsCommandesService ds;

    @Autowired
    private CommandesService cs;

    @Autowired
    private ProduitsService ps;

    @GetMapping(value = "")
    public String list(Model model, HttpServletRequest request) {
        model.addAttribute("details", ds.findAll());
        model.addAttribute("error", request.getParameter("error"));
        model.addAttribute("success", request.getParameter("success"));
        return "pagesClient/detailCommande/list_details";
    }

    @GetMapping(value = "/{id}")
    public String listId(Model model, HttpServletRequest request, @PathVariable int id ) {
        //model.addAttribute("produit", ps.findProduit(id));
        model.addAttribute("commande", cs.findCommande(id));
        model.addAttribute("details", ds.findDetail(id));
        model.addAttribute("error", request.getParameter("error"));
        model.addAttribute("success", request.getParameter("success"));
        return "pagesClient/detailCommande/list_details";
    }

    @GetMapping(value = "/add")
    public String add (Model model){
        model.addAttribute("produits", ps.findAll());
        model.addAttribute("detail", new DetailsCommandesEntity());
        return "pagesClient/detailCommande/add_edit_details";
    }

    @PostMapping(value = "/add")
    public String addPost (HttpServletRequest request, Model model){

        Float prixUnitaire=Float.parseFloat(request.getParameter("prixUnitaire"));
        int quantite=Integer.parseInt(request.getParameter("quantite"));
        Float remise=Float.parseFloat(request.getParameter("remise"));
        int produit = Integer.parseInt(request.getParameter("produit"));

        ProduitsEntity p = new ProduitsEntity();
        p.setIdProduit(produit);
        DetailsCommandesEntity d=new DetailsCommandesEntity(0,prixUnitaire,quantite,remise,p);

        try{
            ds.addDetail(d);
        }catch( Exception e ){
            model.addAttribute("produits", ps.findAll());
            System.out.println( e.getMessage() );
            model.addAttribute("detail" , d );
            model.addAttribute("error" , e.getMessage() );
            return "pagesClient/detailCommande/add_edit_details";
        }
        return "redirect:/client/detail?success=true";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit( Model model , @PathVariable int id ){
        model.addAttribute("produits", ps.findAll());
        try{
            model.addAttribute("detail" , ds.findDetail(id) );
        }catch ( NoSuchElementException e ){
            return "redirect:/client/detail/{id}?error=Detail%20introuvable";
        }
        return "pagesClient/detailCommande/add_edit_details";
    }

    @PostMapping(value = "/edit/{id}")
    public String editPost (HttpServletRequest request, @PathVariable int id, Model model){
        Float prixUnitaire=Float.parseFloat(request.getParameter("prixUnitaire"));
        int quantite=Integer.parseInt(request.getParameter("quantite"));
        Float remise=Float.parseFloat(request.getParameter("remise"));
        int produit = Integer.parseInt(request.getParameter("produit"));

        ProduitsEntity p = new ProduitsEntity();
        p.setIdProduit(produit);
        DetailsCommandesEntity d=new DetailsCommandesEntity(0,prixUnitaire,quantite,remise,p);

        try {
            ds.editDetail(id, d);
        }catch( Exception e ){
            model.addAttribute("produits", ps.findAll());
            System.out.println( e.getMessage() );
            model.addAttribute("commande" , cs.findCommande(id) );
            model.addAttribute("detail" , d );
            model.addAttribute("error" , e.getMessage() );
            return "pagesClient/detailCommande/add_edit_details";
        }
        return "redirect:/client/detail/{id}?success=true";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete (@PathVariable int id){
        String message = "?success=true";
        try {
            ds.delete(id);
        }catch ( Exception e ){
            message = "?error=Detail%20introuvable";
        }
        return "redirect:/client/detail"+message;
    }

}
