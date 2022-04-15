package servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

import controllers.CreerAdherents;
import controllers.ICommand;
import controllers.ListerAdherents;
import controllers.ModifierAdherents;
import controllers.PageAccueilController;
import controllers.SupprimerAdherents;

/**
 *
 * @author Nicolas
 * @version 2.0.0
 *  le arobaseWebServlet signifie
 *  "Salut c'est moi la Servlet qu'on surnomme Accueil"
 */

@WebServlet(urlPatterns = { "/accueil" })
public class FrontController extends HttpServlet {

  private static final String CHEMINJSP = "WEB-INF/JSP/";
  private static final Map<String, ICommand> MAPS =
  new HashMap<>();

  private static EntityManagerFactory factory;
  public static final EntityManagerFactory getFactory() {
      return factory;
    }

    private static EntityManager em;
    public static final EntityManager getEm() {
      return em;
  }
  public static void setEm(final EntityManager emToSet) {
    em = emToSet;
    }

  @Override
  public final void init() throws ServletException {

    makeEntityManager("maRessourceSql");
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
          String messageErreur = "impossible de procéder au DOPOST";
          log(messageErreur);
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
      String messageErreur = "impossible de procéder au doPOST";
      log(messageErreur);

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
      controller.init(request, response);
      controller.runCookies(request, response);
      String urlSuite = controller.execute(request, response);
      request.getRequestDispatcher(
        CHEMINJSP + urlSuite).forward(request, response);

      } catch (Exception e) {
      e.printStackTrace();
      log(e.getLocalizedMessage() + "Erreur inconnue lors de la requête");
      request.getRequestDispatcher(
        CHEMINJSP + "erreur.jsp").forward(request, response);
    }
  }

  @Override
  public final void destroy() {

    try {
      DriverManager.deregisterDriver(
        DriverManager.getDriver("com.mysql.cj.jdbc.Driver"));
      } catch (SQLException e) {
      }

      AbandonedConnectionCleanupThread.checkedShutdown();
  }

  /**
   * crée un entity manager à partir d'un profil de persistance.
   * @param nomMaRessource name du profil de persistance dans
   *  le fichier de configuration
   * @return 1 si l'opération réussie. Sinon 0
   */
  public final int makeEntityManager(final String nomMaRessource) {

    setEm(null);

    try {
       factory = Persistence.createEntityManagerFactory(nomMaRessource);
       setEm(getFactory().createEntityManager());
    } catch (IllegalStateException ise) {
      log("Impossible de mettre en place"
      +
      "l'entity manager pour ce contexte. >");
     }
     if (getEm().equals(null)) {
      return 0;
     } else {
       return 1;
      }

  }


}

