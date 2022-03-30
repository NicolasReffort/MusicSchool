package models;

import javax.annotation.Nonnull;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 *  Une personne
 */
public class Personne {
 
    @Nonnull
    @Min(value = 1, message = "l'identifiant ne peut pas être inférieur à 1" )
    private Integer identifiant;

    @Nonnull
    @Size (min = 1, max = 30, message = "Le nom est beaucoup trop long")
    private String nom;

    @Nonnull 
    @Size (min = 2, message = "Le prénom est trop court") 
    private String prenom; 

    public Integer getIdentifiant() {
        return identifiant;
    }
    public void setIdentifiant(Integer identifiant) {
        this.identifiant = identifiant;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    //CONSTRUCTEUR VIDE 
    Personne(){}

    public Personne(String nom, String  prenom, Integer identifiant){
        this.nom = nom;
        this.prenom = prenom;
        this.identifiant = identifiant; 
    }
    
}
