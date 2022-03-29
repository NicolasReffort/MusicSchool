package controllers ;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Personne;

public class ModifierAdherents implements ICommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Logger logger = Logger.getLogger(CreerAdherents.class.getName());

        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        Integer id = 0;

        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException nfe) {
            logger.log(Level.INFO, "Id non parsable");
        }

        Personne leonard = new Personne("De Vinci", "Léonard", 50);
        Personne pablo = new Personne("Picasso", "Pablo", 60);
        Personne david = new Personne("David", "Jacques-Louis", 77);

        ArrayList<Personne> membres = new ArrayList<Personne>();
        membres.add(leonard);
        membres.add(pablo);
        membres.add(david);
        membres.add(new Personne(nom, prenom, id));

        request.setAttribute("membres", membres);

        return "save.jsp";
    }
}
