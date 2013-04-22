package exception;

public class MauvaisePositionCanalException extends Exception {
    private static final long serialVersionUID = 1L;

    public MauvaisePositionCanalException() {
        super("Mauvaise position de canal");
    }
}
