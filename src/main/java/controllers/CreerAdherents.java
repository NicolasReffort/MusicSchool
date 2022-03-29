package controllers ;

java.util.logging.Logger;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Personne;

public class CreerAdherents implements ICommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        Logger logger = Logger.getLogger(CreerAdherents.class.getName());
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        Integer oeuvre = 0; 
        
        try {
            oeuvre = Integer.parseInt(request.getParameter("oeuvre"));            
        } catch (NumberFormatException nfe) {
            
             "Défaut d'identifaint valide.  ");

        Personne leonard  = new Personne("De Vinci", "Léonard", 50);  
        Personne pablo  = new Personne("Picasso", "Pablo", 60) ;
        Personne david  = new Personne("David", "Jacques-Louis", 77) ;     
        
        ArrayList<Personne> membres = new ArrayList<Personne>(); 
        membres.add(leonard); 
        membres.add(pablo); 
        membres.add(david); 
        membres.add(new Personne(nom, prenom, oeuvre)); 
     
        request.setAttribute("nom", nom);
        request.setAttribute("membres", membres);
        return "creer.jsp";

    }
}
 