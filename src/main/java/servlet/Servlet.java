package servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.validator.internal.util.logging.LoggerFactory;

import controllers.CreerAdherents;
import controllers.ICommand;
import controllers.ListerAdherents;
import controllers.ModifierAdherents;
import controllers.PageAccueilController;
import controllers.SupprimerAdherents;

/**
 *
 * @author Nicolas
 *  le arobaseWebServlet signifie
 *  "Salut c'est moi la Servlet qu'on surnomme Accueil"
 */

@WebServlet(urlPatterns = { "/accueil" })
public class Servlet extends HttpServlet {

  // private Logger logger = Logger.getLogger(Servlet.class.getName());
  private static final String CHEMINJSP = "WEB-INF/JSP/";
  private static final Map<String, ICommand> MAPS =
   new HashMap<>();

   // CONSITUTION DU CARNET D ADRESSE
   @Override
   public final void init() {

     MAPS.put(null, new PageAccueilController());
     MAPS.put("accueil", new PageAccueilController());
     MAPS.put("lister", new ListerAdherents());
     MAPS.put("creer", new CreerAdherents());
     MAPS.put("modifier", new ModifierAdherents());
     MAPS.put("supprimer", new SupprimerAdherents());

    }

    /**
     */
    @Override
  protected void doGet(final HttpServletRequest request,
  final HttpServletResponse response)
  throws ServletException, IOException {
        try {
          processRequest(request, response);
        } catch (ServletException | IOException seio) {
          String messageErreur = "impoddible de procéder au DOPOST";
        }
      }

  /**
   */
  @Override
  protected void doPost(final HttpServletRequest request,
  final HttpServletResponse response)
  throws ServletException, IOException {

    try {
      processRequest(request, response);
    } catch (ServletException | IOException seio) {
      String messageErreur = "impoddible de procéder au doPOST";
    }
  }

  // CONSIGNES
  protected final void processRequest(final HttpServletRequest request,
  final HttpServletResponse response)
      throws ServletException, IOException {

    String action;
    try {
      action = request.getParameter("action");
      // on récupère l’objet de la classe du contrôleur voulu
      ICommand controller = MAPS.get(action);
      String urlSuite = controller.execute(request, response);
      request.getRequestDispatcher(
        CHEMINJSP + urlSuite).forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      log(e.getMessage() + "Erreur inconnue lors de la requête");
      request.getRequestDispatcher(
        CHEMINJSP + "erreur.jsp").forward(request, response);
    }
  }

}
