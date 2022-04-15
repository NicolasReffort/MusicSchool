package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import models.Personne;
import servlet.FrontController;

public class DaoPersonne {

  private Logger logger =
   java.util.logging.Logger.getLogger(DaoPersonne.class.getName());

  public final List<Personne> findAll() {

    List<Object> personnesObjet = new ArrayList<Object>();
    List<Personne> personnes = new ArrayList<Personne>();
    EntityManager em = FrontController.getEm();
    String resultat = "vide";

    // appel Entité Manager
    try {
      em.getTransaction().begin();
      personnes =
       em.createQuery("SELECT p FROM Personne p").getResultList();

      resultat = personnes.get(1).getPrenom();
      em.getTransaction().commit();
    } catch (Exception e) {
      // em.getTransaction().rollback();
      logger.warning(
        em.toString()
        + "le findAll a planté : " + e.getLocalizedMessage() + resultat);
    }
    return personnes;
}

}
