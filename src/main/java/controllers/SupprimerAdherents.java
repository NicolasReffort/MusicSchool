package controllers;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Personne;

public class SupprimerAdherents implements ICommand {

  private Logger logger = Logger.getLogger(Class.class.getName());

  /** * modifie un adhérent.
   *
   * @param request  une requête html
   * @param response une réponse html
   * @return une adresse de page jsp
   */
  public String execute(final HttpServletRequest request,
  final HttpServletResponse response)
  throws Exception {


    final String savoirSiSelectSelectionne = "idFromSelect";
    ArrayList<Personne> membres = new ArrayList<Personne>();
    List<String> erreurs = new ArrayList<String>();
    Boolean erreurDetectee = false;
     Boolean unePersonneChoisieDansLeSelect = false;
    /*si la requete contient le savoirSiSelectSelectionne, c'est que
     l'utilisateur a sélectionné une personne dans le select*/

    if (membres.isEmpty()) {
      // recupérer la collection :
      Personne leonard = new Personne("De Vinci", "Léonard", 1);
      Personne pablo = new Personne("Picasso", "Pablo", 2);
      Personne david = new Personne("David", "Jacques-Louis", 3);
      membres.add(leonard);
      membres.add(pablo);
      membres.add(david);
    }

    if (request.getParameterMap().containsKey(savoirSiSelectSelectionne)) {
      unePersonneChoisieDansLeSelect = true;
    }

    if (unePersonneChoisieDansLeSelect) {
      try {
        //on récupère son ID
        Integer idToModifier = Integer.parseInt(
          request.getParameter(savoirSiSelectSelectionne));
        // on le relie l'id la personne et on essaie de le supprimer,
        for (Personne membre : membres) {
          if ((membre.getIdentifiant()) == idToModifier) {
            membres.remove(membre);
            }
          }
      } catch (NumberFormatException nfe) {
        erreurDetectee = true;
        erreurs.add("La suppression est"
        + "impossible,"
        + "merci de recommencer plus tard."
        + " Ca sera sûrement fonctionnel plus tard!");
      } catch (Exception e) {
        logger.info(e.getMessage());
      }
  }
  // au premier affichage :
  request.setAttribute("membres", membres);
  request.setAttribute("erreurDetectee", erreurDetectee);
  return "supprimer.jsp";

  }
}
