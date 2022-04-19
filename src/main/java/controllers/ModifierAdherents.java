package controllers;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpException;

import dao.DaoPersonne;
import exceptions.MonException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.Personne;
import models.forms.FormulaireModification;

public final class ModifierAdherents implements ICommand {

  private Logger logger = Logger.getLogger(ModifierAdherents.class.getName());

  /**
   * modifie un adhérent.
   * @param request  une requête html
   * @param response une réponse html
   * @return une adresse de page jsp
   */
  public String execute(final HttpServletRequest request,
   final HttpServletResponse response) throws HttpException {


    Integer idToModifier = 0;
    List<Personne> membres = new ArrayList<Personne>();

    String nomModifie;
    String prenomModifie;
    Personne membreModifie = new Personne();

    List<String> erreurs = new ArrayList<String>();
    Boolean erreurDetectee = false;
    Boolean unePersonneChoisieDansLeSelect = false;
    /*si la requete contient le savoirSiSelectSelectionne, c'est que
     l'utilisateur a sélectionné une personne à modifier dans le select*/

    Boolean unePersonneModifiee = false;
    //si la requete contient le savoirSiPersonneEstModifie c'est que
    //l'utilisateur a modifié la personne et renvoyé des données

    Boolean succesModification = false;
    //si la modification en Bdd a fonctionné

    if (Boolean.TRUE.equals(membres.isEmpty())) {
      // recupérer la collection :
      DaoPersonne dao = new DaoPersonne();
      membres = dao.findAll();
    }

    final String savoirSiSelectSelectionne = "idFromSelect";
    final String savoirSiPersonneEstModifie = "identifiant";

    if (request.getParameterMap().containsKey(savoirSiSelectSelectionne)) {
      unePersonneChoisieDansLeSelect = true;
    }

    if ((request.getParameterMap().containsKey(savoirSiPersonneEstModifie))) {
      unePersonneModifiee = true;
    }

    if (Boolean.TRUE.equals(unePersonneChoisieDansLeSelect)
     && Boolean.FALSE.equals(unePersonneModifiee)) {

      //on récupère la personne via l'id
      try {
        idToModifier =
         Integer.parseInt(request.getParameter(savoirSiSelectSelectionne));
         DaoPersonne dao = new DaoPersonne();
         Personne membreToModifier = new Personne();
         membreToModifier = dao.findById(idToModifier);
         // personne qu'on renvoit à la JSP pour modification
         request.setAttribute("membreToModifier", membreToModifier);
        } catch (NumberFormatException nfe) {
          logger.log(Level.INFO, "Id non parsable");
        } catch (MonException me) {
          erreurs.add(me.getMessage());
        }
    }

    if (unePersonneModifiee) {
      String retourForm;

      // on lit ce qu'on vient de recevoir comme nouveaux attributs
      nomModifie = request.getParameter("nom");
      prenomModifie = request.getParameter("prenom");
      try {
        // on relie l'id à la personne
        idToModifier =
        Integer.parseInt(request.getParameter(savoirSiPersonneEstModifie));
        membreModifie = new DaoPersonne().findById(idToModifier);
        membreModifie.setNom(nomModifie);
        membreModifie.setIdentifiant(idToModifier);
        membreModifie.setPrenom(prenomModifie);
      } catch (NumberFormatException nfe) {
        logger.log(Level.INFO, "Id non parsable");
      } catch (MonException monException) {
        erreurs.add(monException.getMessage());
        logger.log(Level.INFO, monException.getMessage());
      }

      // on teste les valeurs reçues avec BeanValidator
      erreurs = membreModifie.areMyAttributesOk();
      // si pas d'erreurs au niveau du BeanValidator
      if (erreurs.isEmpty()) {
        // on teste les valeurs reçues avec la classe forms adaptée
        FormulaireModification monFormulaireModification =
         new FormulaireModification();
        monFormulaireModification.areTwoFieldsEgals(request);
        retourForm = monFormulaireModification.getMessage();

        // si le formulaire n'est pas validé on récupère le message d'erreur
        if (!retourForm.trim().isEmpty()) {
          erreurs.add(retourForm);
          erreurDetectee = true;
        } else {
        // si pas erreurs, on renvoie avec succès
          succesModification = true;
          //... et on sauvegarde la modification
          try {
            new DaoPersonne().save(membreModifie);
          } catch (MonException me) {
            logger.log(Level.INFO, "impossible de save le membre modifié"
            + me.getMessage());
            erreurs.add("Impossible de modifier ce membre pour l'heure."
            + "Merci de réessayer plus tard");
          }

        }

      } else {
        /*si erreur détectée on renvoit le membre que
        l'user va essayer de modifier*/
        erreurDetectee = true;
        for (Personne membre : membres) {
          if ((membre.getIdentifiant()).equals(idToModifier)) {
            request.setAttribute("membreToModifier", membre);
          }
        }
      }
    }

    //dans tous les cas on renvoit:
    // on renvoit le membre saisi précédemment(attention : avec ses erreurs)
    request.setAttribute("membreModifie", membreModifie);
    request.setAttribute(
      "unePersonneChoisieDansLeSelect", unePersonneChoisieDansLeSelect);
    request.setAttribute("erreurDetectee", erreurDetectee);
    request.setAttribute("erreurs", erreurs);
    request.setAttribute("succesModification", succesModification);
    request.setAttribute("unePersonneModifiee", unePersonneModifiee);
    request.setAttribute("membres", membres);

    return "save.jsp";
  }

}
