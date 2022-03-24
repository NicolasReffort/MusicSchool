package models;

import javax.annotation.Nonnull;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 *  Une personne
 */
public class Personne {

 
    @Min(value = 1, message = "L'âge doit être compris entre 1 et 132 ans" )
    @Max (value = 132) //Jame Calment + 10 ans 
    private Integer identifiant;

    @Nonnull
    @Size (min = 1, max = 30, message = "Ton nom est beaucoup trop long")
    private String nom;

    @Nonnull 
    @Size (min = 2, message = "Prénom trop court") 
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

    Personne(String nom, String  prenom, Integer age){
        this.nom = nom;
        this.prenom = prenom; 
        setIdentifiant(age);
    }
    
}
