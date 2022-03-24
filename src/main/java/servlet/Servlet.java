package servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.CreerAdherents;
import controllers.ICommand;
import controllers.ListerAdherents;
import controllers.ModifierAdherents;
import controllers.PageAccueilController;
import controllers.SupprimerAdherents;

/**
 *
 * @author Nicolas
 *         le arobase signifie "Salut c'est moi la Servlet qu'on surnomme Accueil
 */

@WebServlet(urlPatterns = { "/accueil" })
public class Servlet extends HttpServlet {

    private String cheminJSP = "WEB-INF/JSP/";
    private Map<String, ICommand> maps = new HashMap<String, ICommand>();

    // CONSITUTION DU CARNET D ADRESSE DE L'OPERATRICE
    public void init() {
        maps.put(null, new PageAccueilController());
        maps.put("accueil", new PageAccueilController());
        maps.put("lister", new ListerAdherents());
        maps.put("creer", new CreerAdherents());
        maps.put("modifier", new ModifierAdherents());
        maps.put("supprimer", new SupprimerAdherents());
    }

    /**  
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    // CONSIGNE POUR L'OPERATRICE
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action;  

        try {
            action = request.getParameter("action");
            ICommand controller = (ICommand) maps.get(action); // on récupère l’objet de la classe du contrôleur voulu
            String urlSuite = controller.execute(request, response);
            request.getRequestDispatcher(cheminJSP + urlSuite).forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            log(e.getMessage() + "Erreur inconnue lors de la requête");
            request.getRequestDispatcher(cheminJSP + "erreur.jsp").forward(request, response);
        }
    }

    

}
