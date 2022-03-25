package controllers ;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Personne;

public class CreerAdherents implements ICommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
        Map<String, Personne > mapStars = new HashMap<>(); 

        Personne gaetan  = new Personne("Roussel", "Gaëtan", 37);  
        Personne simpson  = new Personne("Sturgill", "Simpson", 42);  
        Personne tony  = new Personne("Allen","Tony", 85);  

        mapStars.put(gaetan.getPrenom(), gaetan); 
        mapStars.put(tony.getPrenom(), tony); 
        mapStars.put(simpson.getPrenom(), simpson); 

        request.setAttribute("mapStars", mapStars);
        return "creer.jsp";
    }
}
 