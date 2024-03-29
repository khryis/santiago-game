package model;

public class Carte {
	private PositionCase position;
	private int nbMarqueurMax;
	private int nbMarqueurActuel;
	private boolean deserte;
	private String type;
	private Joueur possesseur;
	
	/**
	 * @param position
	 * @param nbMarqueurMax
	 * @param nbMarqueurActuel
	 * @param deserte
	 * @param type
	 * @param possesseur
	 */
	public Carte(int nbMarqueurMax, String type) {
		this.position = null;
		this.nbMarqueurMax = nbMarqueurMax;
		this.nbMarqueurActuel = nbMarqueurMax;
		this.deserte = false;
		this.type = type;
		this.possesseur = null;
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
	public boolean isEstDeserte() {
		return deserte;
	}
	public void setEstDeserte(boolean estDeserte) {
		this.deserte = estDeserte;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
