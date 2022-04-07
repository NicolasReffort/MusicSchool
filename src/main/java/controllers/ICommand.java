package controllers ;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpException;

public interface ICommand {


  /** Au lancement de chaque controller, compte les pages.
   *
   * @param request
   * @param response
   * @throws Exception
   */
  default void init(HttpServletRequest request, HttpServletResponse response)
      throws HttpException {

    final Logger logger = Logger.getLogger(this.getClass().getName());
    HttpSession session = request.getSession();
    String compteurPage = "compteurPage";
    if (session.getAttribute(compteurPage) == null) {
      session.setAttribute(compteurPage, 0);
    } else {
      try {
        Object compteurPageAttribut = session.getAttribute(compteurPage);
        Integer pageIncrementee = ((Integer) compteurPageAttribut) + 1;
        session.setAttribute(compteurPage, pageIncrementee);
      } catch (IllegalStateException iso) {
        logger.warning(iso.getCause()
            + iso.getMessage()
            + "Pb de illegal state exception : ");
      } catch (Exception e) {
        logger.warning(e.getCause()
            + e.getMessage()
            + "impossible de récupérer le compteur : ");
      }
    }
      }

  /**
   *
   * @param request
   * @param response
   * @return qqchose
   * @throws Exception
   */
  String execute(HttpServletRequest request, HttpServletResponse response)
   throws HttpException;
}


