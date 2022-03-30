package controllers;


import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Personne;


public class CreerAdherents implements ICommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Logger logger = Logger.getLogger(CreerAdherents.class.getName());
        String nomCreation;
        String prenomCreation; 
        ArrayList<Personne> membres = new ArrayList<Personne>();
        final String parametreToCreate = "nomToCreate";
        String creation = "";

        if (request.getParameterMap().containsKey(parametreToCreate)){             
            creation = "asked"; 
        }

        if (creation.equals("asked") ){ 

            nomCreation = request.getParameter("nomCreation");
            prenomCreation = request.getParameter("prenomCreation"); 

            try { 
                membres.add(new Personne(nomCreation, prenomCreation, 666));
                creation = "done"; 
    
            } catch (NumberFormatException nfe) {
                logger.log(Level.INFO, nfe.getMessage() + "Id non parsable");
                return "erreur.jsp"; 
            }

        }        
        else{
            creation = "asked";
        }

        // on renvoit le statut de la création
        request.setAttribute("creation", creation);            
        return "save.jsp";                
                
    }
}
 