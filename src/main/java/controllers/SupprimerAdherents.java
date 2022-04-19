package controllers;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpException;

import dao.DaoPersonne;
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
  throws HttpException {

    List<Personne> membres = new ArrayList<Personne>();
    List<String> erreurs = new ArrayList<String>();
    Boolean erreurDetectee = false;
    Boolean unePersonneChoisieDansLeSelect = false;
    /*si l'utilisateur a sélectionné une personne dans le select */

    String deleteStatus = "";
    Boolean okForDelete = false;


    // on traduit les infos de la requête en Boolean pour travailler
    if (request.getParameterMap().containsKey("idFromSelect")) {
      unePersonneChoisieDansLeSelect = true;
    }
    if (request.getParameterMap().containsKey("identifiant")) {
      okForDelete = true;
    }

    try {

      // recupérer la collection :
      DaoPersonne dao = new DaoPersonne();
      membres = dao.findAll();

      if (okForDelete) {
        Personne membreToDelete = new Personne();
        Integer idToDelete;
        String idConfirmeString =  request.getParameter("identifiant");
        logger.info("identifiant" + idConfirmeString);
        // on récupère son ID
           idToDelete = Integer.parseInt(idConfirmeString);
           //on relie l'ID à la personne
          membreToDelete = dao.findById(idToDelete);
          // on supprime le membre
          if (!dao.delete(membreToDelete).equals(null)) {
            deleteStatus = "deleteConfirmed";
          }
      } else if (unePersonneChoisieDansLeSelect) {
          Integer idFromSelect;
          String idFromSelectString = request.getParameter("idFromSelect");
          //on récupère son ID
          idFromSelect = Integer.parseInt(idFromSelectString);
          //on renvoit le membre pour confirmation
          for (Personne membre : membres) {
          if ((membre.getIdentifiant()).equals(idFromSelect)) {
            request.setAttribute("membreToDelete", membre);
            }
          }
          deleteStatus = "waitingOk";
      }

    } catch (NumberFormatException nfe) {
        erreurDetectee = true;
        request.setAttribute("erreurDetectee", erreurDetectee);
        erreurs.add("La suppression est"
        + "impossible,"
        + "merci de recommencer plus tard."
        + " Ca sera sûrement fonctionnel plus tard!"
        + nfe.getMessage());
        request.setAttribute("erreurs", erreurs);
    } catch (Exception e) {
        logger.severe(e.getMessage()
        + e.toString() + " Oups impossible de supprimer. ");
    }

    // dans tous les cas :
    request.setAttribute("membres", membres);
    request.setAttribute("deleteStatus", deleteStatus);
    return "supprimer.jsp";

  }


}
