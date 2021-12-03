package com.m2i.ecommerce.m2ikea.controller;
import com.m2i.ecommerce.m2ikea.entities.ClientsEntity;
import com.m2i.ecommerce.m2ikea.entities.CommandesEntity;
import com.m2i.ecommerce.m2ikea.entities.ProduitsEntity;
import com.m2i.ecommerce.m2ikea.entities.CategoriesEntity;
import com.m2i.ecommerce.m2ikea.services.ClientService;
import com.m2i.ecommerce.m2ikea.services.CommandesService;
import com.m2i.ecommerce.m2ikea.services.ProduitsService;
import com.m2i.ecommerce.m2ikea.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.InvalidObjectException;
import java.sql.Timestamp;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/client/commande")
public class CommandesController {

    @Autowired
    private CommandesService coms;

    @Autowired
    private ClientService cs;

    @GetMapping(value = "")
    public String list(Model model, HttpServletRequest request) {
        String search = request.getParameter("search");
        model.addAttribute("commandes", coms.findAll(search));
        model.addAttribute("error", request.getParameter("error"));
        model.addAttribute("success", request.getParameter("success"));
        model.addAttribute("search" );
        return "pagesClient/commande/list_commandes";
    }

    @GetMapping(value = "/add")
    public String add (Model model){
        model.addAttribute("clients", cs.findAll());
        model.addAttribute("commande", new CommandesEntity());
        return "pagesClient/commande/add_edit_commande";
    }

    private CommandesEntity createCommande(HttpServletRequest request){
        String dateCommande = request.getParameter("dateCommande");
        String dateEnvoi = request.getParameter("dateEnvoi");
        dateCommande = dateCommande.replace("T" , " ");
        dateEnvoi = dateEnvoi.replace("T" , " ");
        float port=Float.parseFloat(request.getParameter("port"));
        int codeClient = Integer.parseInt(request.getParameter("client"));

        ClientsEntity c = new ClientsEntity();
        c.setIdClient(codeClient);

        CommandesEntity com=new CommandesEntity(0,Timestamp.valueOf( dateCommande + ":00" ),Timestamp.valueOf( dateEnvoi + ":00" ),port,c);
        return com;
    }

    @PostMapping(value = "/add")
    public String addPost (HttpServletRequest request, Model model){

        try{
            coms.addCommande(createCommande(request));
        }catch( Exception e ){
            model.addAttribute("error" , e.getMessage() );
            return "pagesClient/commande/add_edit_commande";
        }
        return "redirect:/client/commande?success=true";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit( Model model , @PathVariable int id ){
        model.addAttribute("clients", cs.findAll());
        try{
            model.addAttribute("commande" , coms.findCommande(id) );
        }catch ( NoSuchElementException e ){
            return "redirect:/client/commande?error=Commande%20introuvable";
        }
        return "pagesClient/commande/add_edit_commande";
    }

    @PostMapping(value = "/edit/{id}")
    public String editPost (HttpServletRequest request, @PathVariable int id, Model model) throws InvalidObjectException {

        try{
            coms.editCommande(id,createCommande(request));
        }catch( Exception e ){
            model.addAttribute("error" , e.getMessage() );
            System.out.println(e.getMessage());
        }
        return "redirect:/client/commande?success=true";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete (@PathVariable int id){
        String message = "?success=true";
        try {
            coms.delete(id);
        }catch ( Exception e ){
            message = "?error=Commande%20introuvable";
        }
        return "redirect:/client/commande"+message;
    }

}
