package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transaction;

import exceptions.MonException;
import javassist.bytecode.stackmap.BasicBlock.Catch;
import models.Personne;
import net.bytebuddy.description.annotation.AnnotationDescription.Latent;
import servlet.FrontController;

public class DaoPersonne {

  private Logger logger =
   java.util.logging.Logger.getLogger(DaoPersonne.class.getName());

  public final List<Personne> findAll() {

    List<Personne> personnes = new ArrayList<Personne>();
    // appel Entité Manager
    EntityManager em = FrontController.getEm();
    EntityTransaction transaction = em.getTransaction();

    try {
      transaction.begin();
      personnes =
      em.createQuery("SELECT p FROM Personne p").getResultList();
      transaction.commit();
    } catch (Exception e) {
      // em.getTransaction().rollback();
      logger.warning(
        em.toString()
        + "le findAll a planté : " + e.getLocalizedMessage());
    }
    return personnes;
  }

  /**
   *
   * @param idCherche l'id de la personne recherchée
   * @throws MonException si plusieurs résultats
   * @return La personne trouvée, null si pas de match.
   */
  public final Personne findById(final Integer idCherche)
  throws MonException {

    List<Personne> personnesTrouvees = new ArrayList<Personne>();
    Personne personneTrouvee = null;
    // appel Entité Manager
    EntityManager em = FrontController.getEm();
    EntityTransaction transaction = em.getTransaction();

    try {
      transaction.begin();
      personnesTrouvees = em.createNativeQuery(
        "SELECT * FROM Personne WHERE identifiant = ?", Personne.class).
        setParameter(1, idCherche).getResultList();

      //si plusieurs résultats remonte une exception
      if (personnesTrouvees.size() > 1) {
        throw new MonException("l'id suivant a retourné plusieurs résultats,"
        + "ce qui est anormal " + idCherche, 9);
      } else {
        personneTrouvee = personnesTrouvees.get(0);
      }
      transaction.commit();

    } catch (Exception e) {
      logger.warning(
        em.toString()
        + "le findAll a planté : " + e.getLocalizedMessage());
      transaction.rollback();
    }
    return personneTrouvee;
  }

  /**
   *
   * @param personneToSave la personne à créer/mettre à jour
   * @throws MonException si plusieurs résultats
   * @return La personne trouvée, null si pas de match.
   */
  public final Integer save(final Personne personneToSave)
  throws MonException {

    Integer lastInsert = 0;

    // appel Entité Manager
    EntityManager em = FrontController.getEm();
    Integer idPersonneToSave = personneToSave.getIdentifiant();
    EntityTransaction transaction = em.getTransaction();

    //est-ce une mise à jour ou une création ?

    /* si id vaut null(de la JSP)
    c'est qu'il n'existe pas encore*/
    if (idPersonneToSave == 1) {
      try {
        transaction.begin();
        em.persist(personneToSave);
        transaction.commit();
      } catch (Exception e) {
        logger.warning("la création a planté : "
        + e.getLocalizedMessage());
        transaction.rollback();
      }
      //sinon c'est une mise à jour
    } else {
      try {
        transaction.begin();
        em.merge(personneToSave);
        transaction.commit();
      } catch (Exception e) {
        logger.warning("la mise à jour a planté : "
            + e.getLocalizedMessage());
        transaction.rollback();
      }

    }

    return lastInsert;
  }
}
