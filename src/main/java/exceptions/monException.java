package exceptions;

public class monException extends Exception {
    // PACKAGE A PART !!!!
    private int gravite; 

    public void setGravite(int gravite) {
        this.gravite = gravite;
    }

    public monException(String message) {

        super(message);

    }
    public monException(String message, int gravite) {

        super(message); 
        setGravite(gravite);

    }

}
