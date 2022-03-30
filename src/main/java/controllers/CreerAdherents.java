package controllers;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.monException;
import models.Personne;
import outils.Verificateur;


public class CreerAdherents implements ICommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        Logger logger = Logger.getLogger(CreerAdherents.class.getName());
        String nomCreation;
        String prenomCreation; 
        ArrayList<Personne> membres = new ArrayList<Personne>();
        final String parametreToCreate = "nomToCreate";
        String creation = "";
        List<String> erreursBeans; 
        Boolean erreurDetectee =false ; 
        

        if (request.getParameterMap().containsKey(parametreToCreate)){             
            creation = "asked";  
            nomCreation = request.getParameter("nomToCreate");
            prenomCreation = request.getParameter("prenomToCreate");  
            
            // tester les valeurs reçus
            erreursBeans = Verificateur.areMyAttributesOk(new Personne(nomCreation, prenomCreation, 1)); 
            
            if(erreursBeans == null ){       

                try{ 
                    //  envoyer pour enregistrement
                    membres.add(new Personne(nomCreation, prenomCreation, 666));
                    creation = "done";
                }
                catch (NumberFormatException nfe) {
                    logger.log(Level.INFO, nfe.getMessage());
                    return "erreur.jsp"; 
                }

            }//si erreurs,  on refuse de lui donner le statut de "done" et on renvoie avec des erreurs
            else{
                erreurDetectee = true;
                request.setAttribute("erreurs", erreursBeans);
                request.setAttribute("erreurDetectee", erreurDetectee);
            }            
           
        }

        //ici chemin classique de la première visite de la page Créer
        else{
            creation = "asked";
        }

        // on renvoit le statut de la création
        request.setAttribute("creation", creation);            
        return "save.jsp";                
                
   
    }
}
 