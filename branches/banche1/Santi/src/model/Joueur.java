package model;

public class Joueur {
	private String nom;
	private String couleur;
	private int nbMarqueurDispos;
	private boolean tuyauSup;
	private boolean estConstructeur;
	private int solde;
	
	public Joueur(String nom, String couleur) {
		super();
		this.nom = nom;
		this.couleur = couleur;
		this.nbMarqueurDispos = 22;
		this.tuyauSup = true;
		this.estConstructeur = false;
		this.solde = 10;
	}
	
}
