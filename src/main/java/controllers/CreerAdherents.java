package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import exceptions.MonException;
import models.Personne;
import models.forms.FormulaireCreation;


public class CreerAdherents implements ICommand {

  private Logger logger =
  Logger.getLogger(CreerAdherents.class.getName());

  /** cree un adhérent.
   * @param request une requête html
   * @param response une réponse html
   * @return une adresse de page jsp
   */
  public String execute(final HttpServletRequest request,
  final HttpServletResponse response)
   throws Exception {

    init(request, response);
    String nomCreation;
    String prenomCreation;
    ArrayList<Personne> membres = new ArrayList<Personne>();
    final String savoirSiCreationSouhaitee = "nom";
    String creation = "";
    List<String> erreurs = new ArrayList<String>();
    Boolean erreurDetectee = false;
    Personne personneACreer = new Personne();

    //SI on a un paramètre issue de la création c'est que
    // l'user a déjà cliqué sur créer.
    if (request.getParameterMap().containsKey(savoirSiCreationSouhaitee)) {

      creation = "asked";
      nomCreation = request.getParameter("nom");
      prenomCreation = request.getParameter("prenom");
      //to do mettre en try catch les setters
      try {
        personneACreer.setNom(nomCreation);
        personneACreer.setPrenom(prenomCreation);
        personneACreer.setIdentifiant(666);
      } catch (MonException mem) {
        erreurs.add(mem.getMessage());
      }
        // tester les valeurs reçues avec BeanValidator
      erreurs = personneACreer.areMyAttributesOk();

      //s'il n'y a PAS d'erreurs
      if (erreurs.isEmpty()) {

        // on teste les valeurs reçues avec la classe forms adaptée
        FormulaireCreation monFormulaireCreation =
         new FormulaireCreation();
        monFormulaireCreation.areTwoFieldsEgals(request);
        String retourForm = monFormulaireCreation.getMessage();

        // si le formulaire n'est pas validé on récupère le message d'erreur
        if (!retourForm.isEmpty()) {
          erreurs.add(retourForm);
          erreurDetectee = true;
        } else {
        // si toujours pas d'erreurs, on essaie de setter
          try {
            membres.add(personneACreer);
            creation = "done";
          } catch (NumberFormatException nfe) {
            // si pb on renvoie la personne en erreur, avec un flag d'erreur
            logger.log(Level.INFO, nfe.getMessage());
            erreurDetectee = true;
          }
        }
      } else {
        /* si erreurs dès le bean validator,
        on refuse de lui donner le statut de "done" et
        on renvoie avec des erreurs*/
        erreurDetectee = true;
      }

    } else {
      /* si la requête ne contient pas encore de nom c'est qu'il s'agit
      de la première visite de la page Créer */
      creation = "asked";
    }

    // et on renvoie quoi qu'il arrive:
    request.setAttribute("creation", creation);
    request.setAttribute("erreurDetectee", erreurDetectee);
    request.setAttribute("erreurs", erreurs);
    //... la personne que l'useur veut créer (même avec des erreurs)
    request.setAttribute("personneACreer", personneACreer);

    return "save.jsp";

  }

}
