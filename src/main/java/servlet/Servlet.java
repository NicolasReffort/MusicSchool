package servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import controllers.CreerAdherents;
import controllers.ICommand;
import controllers.ListerAdherents;
import controllers.ModifierAdherents;
import controllers.PageAccueilController;
import controllers.SupprimerAdherents;
import models.Personne;

/**
 *
 * @author Nicolas
 * @version 2.0.0
 *  le arobaseWebServlet signifie
 *  "Salut c'est moi la Servlet qu'on surnomme Accueil"
 */

@WebServlet(urlPatterns = { "/accueil" })
public class Servlet extends HttpServlet {

  //private Logger logger = Logger.getLogger(Servlet.class.getName());
  private static final String CHEMINJSP = "WEB-INF/JSP/";
  private static final Map<String, ICommand> MAPS =
  new HashMap<>();

  private static EntityManagerFactory factory;
  private static EntityManager em;
  private List<Personne> membres = new ArrayList<>();

  public static final EntityManager getEm() {
      return em;
  }
  public static final EntityManagerFactory getFactory() {
      return factory;
  }

   @Override
   public final void init() throws ServletException {

    try {
      factory = Persistence.createEntityManagerFactory("maRessourceSql");
      Personne michel = new Personne("Michel", "Robert");
      em = factory.createEntityManager();
      em.getTransaction().begin();
      em.persist(michel);
      em.getTransaction().commit();

    } catch (IllegalStateException ise) {
      log("pb création EntityManager" + ise.getMessage());
    } catch (Exception e) {
      log("l'appel de l'entité manager a échoué : " + e.getLocalizedMessage());
    }


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

  @Override
  public final void destroy() {

      try {
        em.close();
      } catch (IllegalStateException ise) {
        log("pb destroy EntityManager" + ise.getMessage());
      }
  }

}
