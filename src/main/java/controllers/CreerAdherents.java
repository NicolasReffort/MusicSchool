package controllers;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Personne;
import outils.Verificateur;


public class CreerAdherents implements ICommand {

  /** cree un adhérent.
   * @param request une requête html
   * @param response une réponse html
   * @return une adresse de page jsp
   */
  public String execute(final HttpServletRequest request, 
  final HttpServletResponse response)
   throws Exception {
     Logger logger = Logger.getLogger(CreerAdherents.class.getName());    
     String nomCreation;

    String prenomCreation; 
    ArrayList<Personne> membres = new ArrayList<Personne>();
    final String savoirSiCreationSouhaitee = "nom";
    String creation = "";
    List<String> erreurs; 
    Boolean erreurDetectee = false; 

    //SI on a un paramètre issue de la création c'est que
    // l'user a déjà cliqué sur créer. 
    if (request.getParameterMap().containsKey(savoirSiCreationSouhaitee)) {  

      creation = "asked"; 
      nomCreation = request.getParameter("nom");
      prenomCreation = request.getParameter("prenom"); 
      // tester les valeurs reçus avec BeanValidator
      erreurs = Verificateur.areMyAttributesOk(
        new Personne(nomCreation, prenomCreation, 1)); 

      // tester les valeurs reçus avec tests métiers
      if (nomCreation.trim() == prenomCreation.trim()) {
        erreurs.add("Les champs ne doivent pas être identiques");
      }          
      
      if (erreurs.isEmpty()) {    
        try { 
          // envoyer pour enregistrement
          membres.add(new Personne(nomCreation, prenomCreation, 666));
          creation = "done";
        } catch (NumberFormatException nfe) {
          logger.log(Level.INFO, nfe.getMessage());
          return "erreur.jsp"; 
        }        
      } else {
        // si erreurs, on refuse de lui donner le statut de "done" et
        // on renvoie avec des erreurs
        erreurDetectee = true;      
        request.setAttribute("erreurs", erreurs);
        request.setAttribute("erreurDetectee", erreurDetectee);
      }   

    } else { 
      // SINON ici chemin classique de la première visite de la page Créer
      creation = "asked";
    }

    // et on renvoie le statut de la création
    request.setAttribute("creation", creation);      
    return "save.jsp";        
          
  }
}
 