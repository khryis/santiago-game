package model;

public class Carte {
	private Position position;
	private int nbMarqueurMax;
	private int nbMarqueurActuel;
	private boolean estDeserte;
	private String type;
	private Joueur possesseur;
	
	public Joueur getPossesseur() {
		return possesseur;
	}
	public void setPossesseur(Joueur possesseur) {
		this.possesseur = possesseur;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
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
	public boolean isEstDeserte() {
		return estDeserte;
	}
	public void setEstDeserte(boolean estDeserte) {
		this.estDeserte = estDeserte;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
