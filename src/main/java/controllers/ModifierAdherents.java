package controllers;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Personne;

public final class ModifierAdherents implements ICommand {

  /**
   * modifie un adhérent.
   * @param request  une requête html
   * @param response une réponse html
   * @return une adresse de page jsp
   */
  public String execute(final HttpServletRequest request,
   final HttpServletResponse response) throws Exception {

    Logger logger = Logger.getLogger(CreerAdherents.class.getName());

    Integer idToModifier = 0;
    ArrayList<Personne> membres = new ArrayList<Personne>();
    final String parametreChoixSelect = "idFromSelect"; 
    final String parametreIdToModifier = "idToModifier"; 

    String nomModifie; 
    String prenomModifie;

    Boolean unePersonneChoisie = false; 
    //si la requete contient le parametreChoixSelect c'est que
    // l'utilisateur a sélectionné une personne à modifier

    Boolean unePersonneModifiee = false; 
    //si la requete contient le parametreIdToModifier c'est que
    //l'utilisateur a modifié la personne

    // recupérer la collection :
    Personne leonard = new Personne("De Vinci", "Léonard", 50);
    Personne pablo = new Personne("Picasso", "Pablo", 60);
    Personne david = new Personne("David", "Jacques-Louis", 77);
    membres.add(leonard);
    membres.add(pablo);
    membres.add(david);

    if (request.getParameterMap().containsKey(parametreChoixSelect) 
    &&
      (!request.getParameter(parametreChoixSelect).trim().isEmpty())) {
      unePersonneChoisie = true;
    }  
    
    if ((request.getParameterMap().containsKey(parametreIdToModifier)) 
    &&
      (!request.getParameter(parametreIdToModifier).trim().isEmpty())) {
      unePersonneModifiee = true; 
    } 

    if (unePersonneChoisie) {
      try {
      idToModifier = Integer.parseInt(request.getParameter(parametreChoixSelect));
      } catch (NumberFormatException nfe) {
        logger.log(Level.INFO, "Id non parsable");
      }

      //on relit l'id à la personne, 
      //personne qu'on renvoit à la JSP pour modification
      for (Personne membre : membres) {

        if ((membre.getIdentifiant()) == idToModifier) {
          request.setAttribute("membreToModifier", membre);
        }
      }
    }

    if (unePersonneModifiee) {

      nomModifie = request.getParameter("nom");
      prenomModifie = request.getParameter("prenom");
      //to do tester les valeurs reçus
  
       //on relit l'id à la personne
      for (Personne membre:membres) {
        
         if ((membre.getIdentifiant()) == idToModifier) {
           //on fait les mises à jour
           membre.setNom(nomModifie);
           membre.setPrenom(prenomModifie);
           membre.setIdentifiant(666);
        }
      }  
    } else { //on fait un premier affichage des membres à modifier
      request.setAttribute("membres", membres);
    }    
    
    //dans tous les cas on renvoit les flags d'état :             
    request.setAttribute("unePersonneChoisie", unePersonneChoisie);
    request.setAttribute("unePersonneModifiee", unePersonneModifiee);

    return "save.jsp";

  }
}



