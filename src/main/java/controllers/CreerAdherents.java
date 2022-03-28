package controllers ;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Personne;

public class CreerAdherents implements ICommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String oeuvre = request.getParameter("oeuvre");

        Personne leonard  = new Personne("De Vinci", "Léonard", 50);  
        Personne pablo  = new Personne("Picasso", "Pablo", 60) ;
        Personne david  = new Personne("David", "Jacques-Louis", 77) ;
        Personne nouveau = new Personne(nom, prenom, 50);

        Object[][] tableaux = {
            { leonard , "La Joconde"},
            { pablo, "Gernica"}, 
            {david, "Le Sacre de Napoléon"},
            {nouveau, oeuvre}
        
        };
        request.setAttribute("nom", nom);
        request.setAttribute("tableaux", tableaux);
        return "creer.jsp";

    }
}
 