package controllers;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Personne;

public class ListerAdherents implements ICommand {

  /**
   * liste les adhérents.
   * 
   * @param request  une requête html
   * @param response une réponse html
   * @return une adresse de page jsp
   */
  public String execute(final HttpServletRequest request,
   final HttpServletResponse response) throws Exception {

    ArrayList<Personne> membres = new ArrayList<Personne>();

    // recupérer la collection :
    Personne leonard = new Personne("De Vinci", "Léonard", 50);
    Personne pablo = new Personne("Picasso", "Pablo", 60);
    Personne david = new Personne("David", "Jacques-Louis", 77);
    membres.add(leonard);
    membres.add(pablo);
    membres.add(david);
    request.setAttribute("membres", membres);

    return "lister.jsp";
  }
}
