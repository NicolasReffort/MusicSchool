package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpException;

import dao.DaoPersonne;
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
   throws HttpException {

    String nomCreation;
    String prenomCreation;
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
      //setting de la personne à créer
      try {
        personneACreer.setNom(nomCreation);
        personneACreer.setPrenom(prenomCreation);
        //id permettant à la méhtode save de comprendre que c'est une création :
        personneACreer.setIdentifiant(2);

         //indique à la DAO qu'on veut le créer
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
          // si toujours pas d'erreurs, on essaie d'enregistrer en Bdd
          try {
            new DaoPersonne().save(personneACreer);
            creation = "done";

           // on stocke ces infos dans un cookie
           String cookiePrenomUser = "prenomUser";
           String cookieNomUser = "nomUser";
           Cookie cookieNom = getCookie(request, cookieNomUser);
           if (cookieNom != null) {
               cookieNom.setValue(nomCreation);
               response.addCookie(cookieNom);
               request.setAttribute("nouveauNom", cookieNom.getValue());
           }
           Cookie cookiePrenom = getCookie(request, cookiePrenomUser);

           if (cookiePrenom != null) {
             cookiePrenom.setValue(prenomCreation);
             response.addCookie(cookiePrenom);
             request.setAttribute("nouveauPrenom", cookiePrenom.getValue());
           }

          } catch (NumberFormatException nfe) {
            // si pb on renvoie la personne en erreur, avec un flag d'erreur
            logger.log(Level.INFO, nfe.getMessage());
            erreurDetectee = true;
          } catch (IllegalStateException ise) {
          } catch (Exception e) {
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
