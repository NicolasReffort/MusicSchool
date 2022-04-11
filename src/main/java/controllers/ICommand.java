package controllers;

import java.util.logging.Logger;

import javax.servlet.http.Cookie;
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
      session.setAttribute(compteurPage, 1);

    } else { // si notre compteur existe déjà, on l'incrémente

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

  default void runCookies(HttpServletRequest request,
   HttpServletResponse response) {

    // instanciation des cookies
    String cookiePrenomUser = "prenomUser";
    String cookieNomUser = "nomUser";

    // si les cookies suivants sont nuls on les initialise
    if (getCookie(request, cookiePrenomUser) == null) {
      Cookie cookie = new Cookie(cookiePrenomUser, "vide");
      cookie.setMaxAge(3600);
      response.addCookie(cookie);
    }
    if (getCookie(request, cookieNomUser) == null) {
      Cookie cookie = new Cookie(cookieNomUser, "vide");
      cookie.setMaxAge(3600);
      response.addCookie(cookie);
    }

  }

  default  Cookie getCookie(HttpServletRequest request, String name) {

    if (request.getCookies() != null) {
      for (Cookie cookie : request.getCookies()) {
        if (cookie.getName().equals(name)) {
          return cookie;
        }
      }
    }
    return null;
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



