package com.m2i.ecommerce.m2ikea.controller;

import com.m2i.ecommerce.m2ikea.entities.CategoriesEntity;
import com.m2i.ecommerce.m2ikea.entities.ProduitsEntity;
import com.m2i.ecommerce.m2ikea.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/admin/produit")
public class ProduitsController {

    @Autowired
    private StorageServiceImpl StorageService;

    @Autowired
    private ProduitsService ps;

    @Autowired
    private DetailsCommandesService ds;

    @Autowired
    private CategoriesService cs;

    @GetMapping(value = "")
    public String list(Model model, HttpServletRequest request) {
        String search = request.getParameter("search");
        Iterable<ProduitsEntity> produits = ps.findAll(search);
        model.addAttribute("produits", produits);
        model.addAttribute("error", request.getParameter("error"));
        model.addAttribute("success", request.getParameter("success"));
        model.addAttribute("search", search);

        return "pagesAdmin/produit/list_produits";
    }

    @GetMapping(value = "/add")
    public String add (Model model){
        model.addAttribute("categories", cs.findAll());
        model.addAttribute("produit", new ProduitsEntity());
        return "pagesAdmin/produit/add_edit_produit";
    }

    @PostMapping(value = "/add")
    public String addPost (HttpServletRequest request, Model model){

        String nomProduit=request.getParameter("nomProduit");
        int quantite=Integer.parseInt(request.getParameter("quantite"));
        float prixUnitaire=Float.parseFloat(request.getParameter("prixUnitaire"));
        int unitesStock=Integer.parseInt(request.getParameter("unitesStock"));
        int unitesCommandees=Integer.parseInt(request.getParameter("unitesCommandees"));
        boolean indisponible=Boolean.parseBoolean(request.getParameter("indisponible"));
        String description=request.getParameter("description");
        String image=request.getParameter("image");
        int codeCategorie = Integer.parseInt(request.getParameter("codeCategorie"));

        CategoriesEntity c = new CategoriesEntity();
        c.setIdCategorie(codeCategorie);
        ProduitsEntity p=new ProduitsEntity(0,nomProduit,quantite,prixUnitaire,unitesStock,unitesCommandees,indisponible,description,image,c);

        try{
            ps.addProduit(p);
        }catch( Exception e ){
            model.addAttribute("categories", cs.findAll());
            System.out.println( e.getMessage() );
            model.addAttribute("produit" , p );
            model.addAttribute("error" , e.getMessage() );
            return "pagesAdmin/produit/add_edit_produit";
        }
        return "redirect:/admin/produit?success=true";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit( Model model , @PathVariable int id ){
        model.addAttribute("categories", cs.findAll());
        try{
            model.addAttribute("produit" , ps.findProduit(id) );
        }catch ( NoSuchElementException e ){
            return "redirect:/admin/produit?error=Produit%20introuvable";
        }
        return "pagesAdmin/produit/add_edit_produit";
    }

    @PostMapping(value = "/edit/{id}")
    public String editPost (HttpServletRequest request, @PathVariable int id, Model model,@RequestParam("photoProfil") MultipartFile file) throws IOException{

        String nomProduit=request.getParameter("nomProduit");
        int quantite=Integer.parseInt(request.getParameter("quantite"));
        Float prixUnitaire=Float.parseFloat(request.getParameter("prixUnitaire"));
        int unitesStock=Integer.parseInt(request.getParameter("unitesStock"));
        int unitesCommandees=Integer.parseInt(request.getParameter("unitesCommandees"));
        boolean indisponible=Boolean.parseBoolean(request.getParameter("indisponible"));
        String description=request.getParameter("description");
        int codeCategorie = Integer.parseInt(request.getParameter("codeCategorie"));
        //String photo = request.getParameter("image");
        String image = StorageService.store(file, "src\\main\\resources\\static\\images");

        CategoriesEntity c = new CategoriesEntity();
        c.setIdCategorie(codeCategorie);
        ProduitsEntity p=new ProduitsEntity(0,nomProduit,quantite,prixUnitaire,unitesStock,unitesCommandees,indisponible,description,image,c);

        try {
            ps.editProduit(id, p);
        }catch( Exception e ){
            model.addAttribute("categories", cs.findAll());
            System.out.println( e.getMessage() );
            model.addAttribute("produit" , p );
            model.addAttribute("error" , e.getMessage() );
            return "pagesAdmin/produit/add_edit_produit";
        }
        return "redirect:/admin/produit?success=true";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete (@PathVariable int id){
        String message = "?success=true";
        try {
            ps.delete(id);
        }catch ( Exception e ){
            message = "?error=Produit%20introuvable";
        }
        return "redirect:/admin/produit"+message;
    }

}
