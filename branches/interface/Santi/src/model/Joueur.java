package model;

public class Joueur {
    private String nom;
    private String couleur;
    private int nbMarqueurDispos;
    private boolean tuyauSup;
    private boolean estConstructeur;
    private int solde;
    private int enchereCarte;
    private int enchereConstructeur;

    public Joueur(String nom, String couleur) {
        super();
        this.nom = nom;
        this.couleur = couleur;
        this.nbMarqueurDispos = 22;
        this.tuyauSup = true;
        this.estConstructeur = false;
        this.solde = 10;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public int getNbMarqueurDispos() {
        return nbMarqueurDispos;
    }

    public void setNbMarqueurDispos(int nbMarqueurDispos) {
        this.nbMarqueurDispos = nbMarqueurDispos;
    }

    public boolean isTuyauSup() {
        return tuyauSup;
    }

    public void setTuyauSup(boolean tuyauSup) {
        this.tuyauSup = tuyauSup;
    }

    public boolean isEstConstructeur() {
        return estConstructeur;
    }

    public void setEstConstructeur(boolean estConstructeur) {
        this.estConstructeur = estConstructeur;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public void majSolde(int somme) {
        this.solde += somme;
    }

    public int getEnchereConstructeur() {
        return enchereConstructeur;
    }

    public void setEnchereConstructeur(int enchereConstructeur) {
        this.enchereConstructeur = enchereConstructeur;
    }

    public int getEnchereCarte() {
        return enchereCarte;
    }

    public void setEnchereCarte(int enchereCarte) {
        this.enchereCarte = enchereCarte;
    }

    @Override
    public String toString() {
        return "\nJoueur [nom=" + nom + ", couleur=" + couleur + ", estConstructeur=" + estConstructeur + ", solde=" + solde + "]";
    }

}
