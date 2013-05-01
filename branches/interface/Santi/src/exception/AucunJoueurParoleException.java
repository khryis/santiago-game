package exception;

public class AucunJoueurParoleException extends Exception {
    private static final long serialVersionUID = 1L;

    public AucunJoueurParoleException() {
        super("Aucun joueur n'as la main dans la partie..");
    }

}
