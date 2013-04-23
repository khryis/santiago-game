package model;

public class Joueur {
    private String nom;
    private String couleur;
    private int nbMarqueurDispos;
    private boolean tuyauSup;
    private boolean constructeur;
    private int solde;
    private int enchereCarte;
    private int enchereConstructeur;

    public Joueur() {
        super();
        nom = "null";
        couleur = "null";
        nbMarqueurDispos = 22;
        tuyauSup = true;
        constructeur = false;
        solde = 10;
    }

    public Joueur(String nom, String couleur) {
        super();
        this.nom = nom;
        this.couleur = couleur;
        nbMarqueurDispos = 22;
        tuyauSup = true;
        constructeur = false;
        solde = 10;
    }

    public void ajouterArgent(int somme) {
        solde += somme;
    }

    public void enleverArgent(int somme) {
        solde -= somme;
    }

    @Override
    public String toString() {
        return "Joueur [nom=" + nom + ", couleur=" + couleur + ", estConstructeur=" + constructeur + ", solde=" + solde + "]\n";
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

    public boolean hasTuyauSup() {
        return tuyauSup;
    }

    public void setTuyauSup(boolean tuyauSup) {
        this.tuyauSup = tuyauSup;
    }

    public boolean isConstructeur() {
        return constructeur;
    }

    public void setConstructeur(boolean constructeur) {
        this.constructeur = constructeur;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
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

}
