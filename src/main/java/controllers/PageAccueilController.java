package controllers;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpException;

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

    return "Accueil.jsp";
  }


}
