package controllers;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.MonException;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Personne;
import outils.Verificateur;

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
    
    String nomModifie; 
    String prenomModifie;
    List<String> erreurs;
    Boolean erreurDetectee = false;
    Boolean unePersonneChoisieDansLeSelect = false; 
    //si la requete contient le savoirSiSelectSelectionne c'est que
    // l'utilisateur a sélectionné une personne à modifier dans le select

    Boolean unePersonneModifiee = false; 
    //si la requete contient le savoirSiPersonneEstModifie c'est que
    //l'utilisateur a modifié la personne
   
    Boolean succesModification = false; 
    
    // recupérer la collection :
    Personne leonard = new Personne("De Vinci", "Léonard", 50);
    Personne pablo = new Personne("Picasso", "Pablo", 60);
    Personne david = new Personne("David", "Jacques-Louis", 77);
    membres.add(leonard);
    membres.add(pablo);
    membres.add(david);

    final String savoirSiSelectSelectionne = "idFromSelect"; 
    final String savoirSiPersonneEstModifie = "identifiant"; 

    if (request.getParameterMap().containsKey(savoirSiSelectSelectionne)) {  
      unePersonneChoisieDansLeSelect = true;
    }
        
    if ((request.getParameterMap().containsKey(savoirSiPersonneEstModifie))) {
      unePersonneModifiee = true; 
    } 

    if (unePersonneChoisieDansLeSelect && !unePersonneModifiee) {
      //on récupère son id
      try {    
        idToModifier =
         Integer.parseInt(request.getParameter(savoirSiSelectSelectionne));
      } catch (NumberFormatException nfe) {
        logger.log(Level.INFO, "Id non parsable");
 
      }

      // on le relie l'id la personne,
      // personne qu'on renvoit à la JSP pour modification
      for (Personne membre : membres) {
        if ((membre.getIdentifiant()) == idToModifier) {
          request.setAttribute("membreToModifier", membre);
        }
      }
    }

    if (unePersonneModifiee) {      

      // on lit ce qu'on vient de recevoir comme nouveaux attribubts
      nomModifie = request.getParameter("nom");
      prenomModifie = request.getParameter("prenom");
      try {
        idToModifier =
         Integer.parseInt(request.getParameter(savoirSiPersonneEstModifie));
      } catch (NumberFormatException nfe) {
        logger.log(Level.INFO, "Id non parsable");

      }
      Personne membreModifie = new Personne(); 
      
      // on relie l'id à la personne
      for (Personne membre : membres) {
        if ((membre.getIdentifiant()) == idToModifier) {
          // on créé une personne pour aller passer les tests BeansValidator
    
        membreModifie.setNom(nomModifie);
        membreModifie.setIdentifiant(idToModifier);
        membreModifie.setPrenom(prenomModifie);

        }
      }
      // on teste les valeurs reçues avec BeanValidator
      erreurs = Verificateur.areMyAttributesOk(membreModifie);
         
      // tester les valeurs reçues avec tests supplémentaires
      // if (nomModifie.trim() == prenomModifie.trim()) {
      //   erreurs.add("Les champs ne doivent pas être identiques");
      // }

      // si erreurs, on renvoie avec elles
      if (erreurs.isEmpty()) {
        succesModification = true;          
      } else {
        erreurDetectee = true;        
        request.setAttribute("erreurs", erreurs);
        // si pas erreurs, on renvoie avec succès
        for (Personne membre : membres) {

          if ((membre.getIdentifiant()) == idToModifier) {
            request.setAttribute("membreToModifier", membre);
          }
        }
      }
      
    }   
    
    //dans tous les cas on renvoit les flags d'état :             
    request.setAttribute(
      "unePersonneChoisieDansLeSelect", unePersonneChoisieDansLeSelect);
    request.setAttribute("erreurDetectee", erreurDetectee);
    request.setAttribute("succesModification", succesModification);    
    request.setAttribute("unePersonneModifiee", unePersonneModifiee);
    request.setAttribute("membres", membres);    
    return "save.jsp";
  }
}
