package outils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import exceptions.monException;
import models.Personne;

import java.util.logging.Level;
import java.util.logging.Logger;



public class  Verificateur {

    /**
     * 
     * @param unePersonne une personne de la classe Personne (TO DO : utiliser la généricité pour l'étendre à tous les objets)
     * @return liste d'erreur vide si pas d'erreur, sinon retour une liste des messages d'erreur, en String. 
     */
    public static List<String> areMyAttributesOk(Personne unePersonne){

    Set<ConstraintViolation<Personne>> violations = null;
    Logger logger = Logger.getLogger(Verificateur.class.getName());
    List<String>  listesErreurs = new ArrayList<>(); 

    try{
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();    
        violations = validator.validate(unePersonne);  
    }
    catch(ValidationException ve){
        logger.log(Level.INFO,ve.getMessage());
    }
    catch(RuntimeException rte){
        logger.log(Level.INFO, rte.getMessage());
    }

    if(!violations.isEmpty()){
        for (ConstraintViolation<Personne> violation:violations ){
            listesErreurs.add(violation.getMessage());  
        }
    }    
    return listesErreurs;

    }
  

}
