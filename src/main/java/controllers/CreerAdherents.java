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
        List<String> erreurs; 
        Boolean erreurDetectee =false ; 
        

        // SI on a un paramètre issue de la création c'est que l'user a déjà cliqué sur créer. 
        if (request.getParameterMap().containsKey(parametreToCreate)){             
            creation = "asked";  
            nomCreation = request.getParameter("nomToCreate");
            prenomCreation = request.getParameter("prenomToCreate");  
            
            // tester les valeurs reçus avec BeanValidator
            erreurs = Verificateur.areMyAttributesOk(new Personne(nomCreation, prenomCreation, 1)); 

            // tester les valeurs reçus avec tests métiers
            if (nomCreation.trim() == prenomCreation.trim()) {
                erreurs.add("Les champs ne doivent pas être identiques");
            }                   
            
            if(erreurs.isEmpty()){       
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
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("erreurDetectee", erreurDetectee);
            }      
        }

        //SINON ici chemin classique de la première visite de la page Créer
        else{
            creation = "asked";
        }

        // et on renvoie le statut de la création
        request.setAttribute("creation", creation);            
        return "save.jsp";                
                
   
    }
}
 