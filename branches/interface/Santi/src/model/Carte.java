package model;

public class Carte {
    private PositionCase position;
    private int nbMarqueurMax;
    private int nbMarqueurActuel;
    private boolean deserte;
    private TypeChamp type;
    private Joueur possesseur;

    public enum TypeChamp {
        PATATE, HARICOT, CANNE_A_SUCRE, BANANE, PIMENT
    }

    public Carte(int nbMarqueurMax, TypeChamp type) {
        this.nbMarqueurMax = nbMarqueurMax;
        this.type = type;
        nbMarqueurActuel = nbMarqueurMax;
        position = null;
        deserte = false;
        possesseur = new Joueur();
    }

    @Override
    public String toString() {
        return "Carte [possesseur : " + possesseur.getNom() + " position=" + position + ", nbMarqueurActuel=" + nbMarqueurActuel
                + ", deserte=" + deserte + ", type=" + type + "]\n";
    }

    public Joueur getPossesseur() {
        return possesseur;
    }

    public void setPossesseur(Joueur possesseur) {
        this.possesseur = possesseur;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(PositionCase position) {
        this.position = position;
    }

    public PositionCase getPositionCase() {

        return position;
    }

    public void setPositionCase(PositionCase position) {

        this.position = position;
    }

    public int getNbMarqueurMax() {
        return nbMarqueurMax;
    }

    public void setNbMarqueurMax(int nbMarqueurMax) {
        this.nbMarqueurMax = nbMarqueurMax;
    }

    public int getNbMarqueurActuel() {
        return nbMarqueurActuel;
    }

    public void setNbMarqueurActuel(int nbMarqueurActuel) {
        this.nbMarqueurActuel = nbMarqueurActuel;
    }

    public boolean isDeserte() {
        return deserte;
    }

    public void setDeserte(boolean estDeserte) {
        deserte = estDeserte;
    }

    public TypeChamp getType() {
        return type;
    }

    public void setType(TypeChamp type) {
        this.type = type;
    }
}
