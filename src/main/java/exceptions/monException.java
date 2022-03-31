package exceptions;

public class MonException extends Exception {

  /** seuil de gravité d'une erreur.
   * 
   */
  private int gravite; 

  
  public final void setGravite(final int graviteMessage) {
    this.gravite = graviteMessage;        
  }
  /** constructeur erreur sans message.
   * @param message message d'erreur 
   */
  public MonException(final String message) {

    super(message);

  }
  public MonException(final String message, final int graviteMessage) {

    super(message); 
    setGravite(graviteMessage);
  }

}
