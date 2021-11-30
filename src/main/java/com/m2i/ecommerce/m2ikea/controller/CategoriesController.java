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
@RequestMapping("/categorie")
public class CategoriesController {

        @Autowired
        private CategoriesService catService;


        @GetMapping(value = "")
        public String list(Model model, HttpServletRequest request) {

            Iterable<CategoriesEntity> categories = catService.findAll();

            model.addAttribute("categories", categories);
            model.addAttribute("error", request.getParameter("error"));
            model.addAttribute("success", request.getParameter("success"));

            return "categorie/list_categories";
        }



        @GetMapping(value = "/add")
        public String add (Model model){
            model.addAttribute("categorie", new CategoriesEntity());
            return "categorie/add_edit";
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
                return "categorie/add_edit";
            }
            return "redirect:/categorie?success=true";
        }


        @GetMapping(value = "/edit/{id}")
        public String edit( Model model , @PathVariable int id ){
            try{
                model.addAttribute("categorie" , catService.findCategories(id) );
            }catch ( NoSuchElementException e ){
                return "redirect:/categorie?error=Categorie%20introuvalble";
            }
            return "categorie/add_edit";

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
                return "categorie/add_edit";
            }
            return "redirect:/categorie?success=true";
        }

        @GetMapping(value = "/delete/{id}")
        public String delete (@PathVariable int id){
            String message = "?success=true";
            try {
                catService.delete(id);
            }catch ( Exception e ){
                message = "?error=Categorie%20introuvalble";
            }
            return "redirect:/categorie"+message;
        }



    }
