package com.m2i.ecommerce.m2ikea.controller;

import com.m2i.ecommerce.m2ikea.entities.CategoriesEntity;
import com.m2i.ecommerce.m2ikea.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;

//CONTROLLER WEB
@Controller
@RequestMapping("/admin/categories")
public class CategoriesController {

    @Autowired
    private CategoriesService catService;


    @GetMapping(value = "")
    public String list(Model model, HttpServletRequest request) {

        Iterable<CategoriesEntity> categories = catService.findAll();

        model.addAttribute("categories", categories);
        model.addAttribute("error", request.getParameter("error"));
        model.addAttribute("success", request.getParameter("success"));

        return "pagesAdmin/categories/list_categories";
    }

    @GetMapping(value = "/add")
    public String add (Model model){
        model.addAttribute("categorie", new CategoriesEntity());
        return "pagesAdmin/categories/add_edit_categories";
    }

    @PostMapping(value = "/add")
    public String addPost (HttpServletRequest request, Model model){

        String nomCategorie=request.getParameter("nomCategorie");
        String description=request.getParameter("description");

        CategoriesEntity c=new CategoriesEntity(0, nomCategorie,description);

        try{
            catService.addCategories(c);
        }catch( Exception e ){
            System.out.println( e.getMessage() );
            model.addAttribute("categorie" , c );
            model.addAttribute("error" , e.getMessage() );
            return "pagesAdmin/categories/add_edit_categories";
        }
        return "redirect:/admin/categories?success=true";
    }


    @GetMapping(value = "/edit/{id}")
    public String edit( Model model , @PathVariable int id ){
        try{
            model.addAttribute("categorie" , catService.findCategories(id) );
        }catch ( NoSuchElementException e ){
            return "redirect:/admin/categories?error=Categorie%20introuvable";
        }
        return "pagesAdmin/categories/add_edit_categories";

    }

    @PostMapping(value = "/edit/{id}")
    public String editPost (HttpServletRequest request, @PathVariable int id, Model model){
        CategoriesEntity cExistant= catService.findCategories(id);

        String nomCategorie=request.getParameter("nomCategorie");
        String description=request.getParameter("description");


        CategoriesEntity c=new CategoriesEntity(0, nomCategorie, description);
        c.setIdCategorie(cExistant.getIdCategorie());

        try {
            catService.editCategories(id, c);
        }catch( Exception e ){
            //c.setIdCategorie(  -1 ); // hack
            System.out.println( e.getMessage() );
            model.addAttribute("categorie" , c );
            model.addAttribute("error" , e.getMessage() );
            return "pagesAdmin/categories/add_edit_categories";
        }
        return "redirect:/admin/categories?success=true";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete (@PathVariable int id){
        String message = "?success=true";
        try {
            catService.delete(id);
        }catch ( Exception e ){
            message = "?error=Categorie%20introuvalble";
        }
        return "redirect:/admin/categories"+message;
    }
}
