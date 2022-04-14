package controllers;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpException;

import dao.DaoPersonne;
import models.Personne;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PageAccueilController implements ICommand {

  private Logger logger =
   Logger.getLogger(PageAccueilController.class.getName());

  /**
   * liste les adhérents.
   *
   * @param request  une requête html
   * @param response une réponse html
   * @return une adresse de page jsp
   */
  public String execute(final HttpServletRequest request,
  final HttpServletResponse response) throws HttpException {

    init(request, response);
    runCookies(request, response);

    List<Personne> personnes = new ArrayList<Personne>();
    return "Accueil.jsp";
  }



}
