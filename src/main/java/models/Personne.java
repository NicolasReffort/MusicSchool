package models;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Entity
@Table(name = "personne")
public class Personne {

  // annotations pour le mappage avec ORM
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  private Integer identifiant;

  @Nonnull
  @Column
  @Size (min = 2, max = 30,
   message = "Le nom doit faire entre 2 et 30 caractères")
  private String nom;

  @Nonnull
  @Column
  @Size (min = 2, message = "Le prénom est trop court")
  private String prenom;

  public Integer getIdentifiant() {
    return identifiant;
  }
  public void setIdentifiant(final Integer identifiantASetter) {
      this.identifiant = identifiantASetter;
  }

  public  String getNom() {
    return nom;
  }

  public  void setNom(final String nomASetter) throws MonException {
    this.nom = nomASetter;
  }

  public  String getPrenom() {
    return prenom;
  }

  public  void setPrenom(final String prenomASetter) {
    this.prenom = prenomASetter;
  }

  //CONSTRUCTEUR VIDE
  public Personne() { };

  public Personne(final String nomASetter,
   final String prenomASetter) {
    this.nom = nomASetter;
    this.prenom = prenomASetter;
  }
  public Personne(final String nomASetter,
   final String prenomASetter, final Integer identifiantASetter) {
    this.nom = nomASetter;
    this.prenom = prenomASetter;
    setIdentifiant(identifiantASetter);
  }

  /** Beanvalide la personne.
   * @return liste des messages d'erreur (vide si pas d'erreur)
   */
  public List<String> areMyAttributesOk(Personne this) {

    Logger logger = Logger.getLogger(Personne.class.getName());
    List<String> listesErreurs = new ArrayList<>();

    try {
      Set<ConstraintViolation<Personne>> violations;
      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
      Validator validator = factory.getValidator();
      violations = validator.validate(this);
      if (!violations.isEmpty()) {
        for (ConstraintViolation<Personne> violation : violations) {
          listesErreurs.add(violation.getMessage());
        }
      }
    } catch (ValidationException ve) {
      logger.log(Level.INFO, ve.getMessage());
    } catch (RuntimeException rte) {
      logger.log(Level.INFO, rte.getMessage());
    }

    return listesErreurs;
  }

}
