package models.forms;

import javax.servlet.http.HttpServletRequest;

public final class FormulaireCreation {
  
  private String message = "";
  public String getMessage() {
      return message;
  }
  public void setMessage(final String messageASetter) {
      this.message = messageASetter;
  }

  public FormulaireCreation() { }; 

  public void  areTwoFieldsEgals(
    final HttpServletRequest request) {    

    String nomModifie = request.getParameter("nom");
    String prenomModifie = request.getParameter("prenom");
         
    // tester les valeurs reçues avec tests supplémentaires
    if (nomModifie.trim().toLowerCase().equals(
      prenomModifie.trim().toLowerCase())) {
    setMessage("Les champs ne doivent pas être dentiques");
    }
  }

} 
