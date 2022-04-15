package utilitaires;

import java.util.List;

import models.Personne;

public class Outils {

  /**
   * Caste une liste d'objet contenant
   * des Personnes en liste de personnes.
   *
   * @param trouves
   * @return une liste de personnesCastees;
   */
  public final List<Personne> castToPersonne(final List<Object> trouves) {

    List<Personne> personnesCastees = null;

    for (Object trouve : trouves) {
      if (trouve instanceof Personne) {
        Personne personneTrouve = ((Personne) trouve);
        personnesCastees.add(personneTrouve);
      }
    }

    return personnesCastees;

  }

}
