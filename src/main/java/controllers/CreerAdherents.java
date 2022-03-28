package controllers ;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Personne;

public class CreerAdherents implements ICommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
        // Map<String, Personne > mapStars = new HashMap<>(); 

        Personne leonard  = new Personne("De Vinci", "Léonard", 50);  
        Personne pablo  = new Personne("Picasso", "Pablo", 60) ;

        Object[][] tableaux = {
            { leonard , "La Joconde"},
            { pablo, "Gernica"}, 
        
        };

        request.setAttribute("tableaux", tableaux);
        return "creer.jsp";

    }
}
 