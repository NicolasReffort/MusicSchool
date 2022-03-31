package controllers;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SupprimerAdherents implements ICommand {
  
  /** * modifie un adhérent.
   * 
   * @param request  une requête html
   * @param response une réponse html
   * @return une adresse de page jsp
   */
  public String execute(final HttpServletRequest request,
  final HttpServletResponse response)
  throws Exception {
    return "supprimer.jsp";
  }
}
