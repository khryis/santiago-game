package model;

public class PositionCase extends Position {

    private boolean irriguee;

    public PositionCase() {
        super();
        this.irriguee = false;
    }

    public PositionCase(int abscisse, int ordonnee) {
        super(abscisse, ordonnee);
        this.irriguee = false;
    }

    public PositionCase(int abscisse, int ordonnee, boolean occupee) {
        super(abscisse, ordonnee, occupee);
        this.irriguee = false;
    }

    public static PositionCase aleatoire() {
        // TEST positionCase aleatoire Chris
        int abscisse, ordonnee;
        double rand = Math.random() * 8;
        rand = Math.floor(rand);
        Double d = new Double(rand);
        abscisse = d.intValue();

        rand = Math.random() * 6;
        rand = Math.floor(rand);
        d = new Double(rand);
        ordonnee = d.intValue();

        PositionCase pc = new PositionCase(abscisse, ordonnee, false);
        return pc;
    }

    public boolean isIrriguee() {
        return irriguee;
    }

    public boolean getIrriguee() {

        return irriguee;
    }

    public void setIrriguee(boolean irriguee) {

        this.irriguee = irriguee;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (irriguee ? 1231 : 1237);
        return result;
    }

}
