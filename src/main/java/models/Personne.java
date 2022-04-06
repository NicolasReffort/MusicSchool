package models;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

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

  /** Beanvalide la personne.
   * @return liste des messages d'erreur (vide si pas d'erreur)
   */
  public List<String> areMyAttributesOk(Personne this) {

    Set<ConstraintViolation<Personne>> violations = null;
    Logger logger = Logger.getLogger(Personne.class.getName());
    List<String> listesErreurs = new ArrayList<>();

    try {
      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
      Validator validator = factory.getValidator();
      violations = validator.validate(this);
    } catch (ValidationException ve) {
      logger.log(Level.INFO, ve.getMessage());
    } catch (RuntimeException rte) {
      logger.log(Level.INFO, rte.getMessage());
    }

    if (!violations.isEmpty()) {
      for (ConstraintViolation<Personne> violation : violations) {
        listesErreurs.add(violation.getMessage());
      }
    }
    return listesErreurs;
  }

}
