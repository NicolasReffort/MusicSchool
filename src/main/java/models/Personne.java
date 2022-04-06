package models;

import java.sql.Array;
import java.util.ArrayList;

import javax.annotation.Nonnull;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import exceptions.MonException;

/** Une personne.
 */
public class Personne {

   @Nonnull
  @Min(value = 1, message = "l'identifiant ne peut pas être inférieur à 1")
  private Integer identifiant;

  @Nonnull
  @Size (min = 2, max = 30,
   message = "Le nom doit faire entre 2 et 30 caractères")
  private String nom;

  @Nonnull
  @Size (min = 2, message = "Le prénom est trop court")
  private String prenom;

  public final Integer getIdentifiant() {
    return identifiant;
  }
  public final void setIdentifiant(final Integer identifiantASetter) {
    this.identifiant = identifiantASetter;
  }

  public final String getNom() {
    return nom;
  }
  public final void setNom(final String nomASetter) throws MonException {
    this.nom = nomASetter;
  }
  public final String getPrenom() {
    return prenom;
  }

  public final void setPrenom(final String prenomASetter) {
    this.prenom = prenomASetter;
  }

  //CONSTRUCTEUR VIDE
  public Personne() { };

  public Personne(final String nomASetter,
   final String prenomASetter, final Integer identifiantASetter) {
    this.nom = nomASetter;
    this.prenom = prenomASetter;
    this.identifiant = identifiantASetter;
  }
}
