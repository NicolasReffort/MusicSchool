package controllers;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpException;
import dao.DaoPersonne;
import models.Personne;


public class ListerAdherents implements ICommand {

  private Logger logger =
  java.util.logging.Logger.getLogger(DaoPersonne.class.getName());

  /**
   * liste les adhérents.
   *
   * @param request  une requête html
   * @param response une réponse html
   * @return une adresse de page jsp
   */
  public String execute(final HttpServletRequest request,
   final HttpServletResponse response) throws HttpException {

    List<Personne> membres = new ArrayList<Personne>();
    // on récupère nos membres
    DaoPersonne dao = new DaoPersonne();
    membres = dao.findAll();

    // si on a des membres à l'intérieur de la collection on renvoit à la jsp
    if (!membres.equals(null)) {
      request.setAttribute("membres", membres);
    }

    return "lister.jsp";
  }


}
