package controllers;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
   final HttpServletResponse response) throws Exception {

    init(request, response);

    Integer idToModifier = 0;
    ArrayList<Personne> membres = new ArrayList<Personne>();

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
      Personne leonard = new Personne("De Vinci", "Léonard", 50);
      Personne pablo = new Personne("Picasso", "Pablo", 60);
      Personne david = new Personne("David", "Jacques-Louis", 77);
      membres.add(leonard);
      membres.add(pablo);
      membres.add(david);
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
        if ((membre.getIdentifiant().equals(idToModifier))) {
          request.setAttribute("membreToModifier", membre);
        }
      }
    }

    if (unePersonneModifiee) {
      String retourForm;

      // on lit ce qu'on vient de recevoir comme nouveaux attributs
      nomModifie = request.getParameter("nom");
      prenomModifie = request.getParameter("prenom");
      try {
        idToModifier =
         Integer.parseInt(request.getParameter(savoirSiPersonneEstModifie));
      } catch (NumberFormatException nfe) {
        logger.log(Level.INFO, "Id non parsable");
      }

      // on relie l'id à la personne
      for (Personne membre : membres) {
        if ((membre.getIdentifiant()).equals(idToModifier)) {
          // on créé une personne pour aller passer les tests BeansValidator
        membreModifie.setNom(nomModifie);
        membreModifie.setIdentifiant(idToModifier);
        membreModifie.setPrenom(prenomModifie);
        }
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
        }

      } else {
        erreurDetectee = true;
        //on renvoit le membre que l'user va essayer de modifier
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
