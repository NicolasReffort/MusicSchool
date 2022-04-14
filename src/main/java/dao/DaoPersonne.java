package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.Transaction;
import javax.transaction.UserTransaction;

import models.Personne;

 @NamedQuery(name = "findAllCustomersWithName",
   query = "SELECT p FROM personne p ")
public class DaoPersonne {

  private static EntityManagerFactory factory;
  @PersistenceContext
  private static EntityManager em;
  public static final EntityManager getEm() {
      return em;
  }
  public static final EntityManagerFactory getFactory() {
      return factory;
  }


  public final List<Personne> findAll() {

    Personne robert = new Personne("Robert", "Laffont", 1);

    List<Personne> personnes = new ArrayList<Personne>();
    // appel Entité Manager
    try {

      final String nomMaRessource = "maRessourceSql";
      factory = Persistence.createEntityManagerFactory(nomMaRessource);
      em = getFactory().createEntityManager();
    } catch (IllegalStateException ise) {
    }
    return personnes;
}




}
