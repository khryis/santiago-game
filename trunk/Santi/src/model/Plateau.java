package model;

import java.util.ArrayList;

public class Plateau {
	private PositionIntersection source;
	private ArrayList<PositionSegment> canaux;
	private ArrayList<PositionCase> cases;
	private ArrayList<Carte> cartes;
	private ArrayList<Carte> cartesPosees;
	private ArrayList<PositionCase> palmiers;
	
	
	private Plateau(int niveau){
		this.source = PositionIntersection.aleatoire(niveau);
		this.canaux = new ArrayList<PositionSegment>(31);
		this.cases = new ArrayList<PositionCase>(48);
		this.palmiers = new ArrayList<PositionCase>(3);
	}
	
	/**
	 * @param source
	 * @param canaux
	 * @param cases
	 * @param cartes
	 * @param palmiers
	 */
	public Plateau(int niveau, int nbJoueur) {
		this(niveau);
		switch (nbJoueur) {
			case 3:
				this.cartes = new ArrayList<Carte>(44);
				break;
			case 4:
				this.cartes = new ArrayList<Carte>(44);
				break;
			case 5:
				this.cartes = new ArrayList<Carte>(45);	
				break;
			default:
				this.cartes = new ArrayList<Carte>();
				break;
		}
	}
	
	public void placerPalmier() {
		for (int i = 0; i < 3; i++) {
			palmiers.add(PositionCase.aleatoire());
		}
	}
	
	public ArrayList<Carte> tirerCarte(){
		// TODO tirer les 4 ou 5 premières cartes de la liste Chris
		// mettre références des cartes déjà existantes de "cartes" et ne pas faire de nouvel object ou copie d'un existant
		return new ArrayList<Carte>();
	}
	
	public void poserUneCarte(Carte c){
		// TODO mettre la carte c de la liste "cartes" à "cartesPosees" Allo
		// Allo ! On a oublié d'attribuer une fonction ! x)
		// mettre à jour c.position.occupe
	}
	
	public void placerCanal(){
		// TODO placerCanal Flo
		// TODO résoudre le motherfucking compromis dans santiago Flo
	}
	
	public void placerCanalSup(){
		// TODO placerCanal Sup Antho
		// mettre position à jour 
	}

	public PositionIntersection getSource() {
		return source;
	}

	public void setSource(PositionIntersection source) {
		this.source = source;
	}

	public ArrayList<PositionSegment> getCanaux() {
		return canaux;
	}

	public void setCanaux(ArrayList<PositionSegment> canaux) {
		this.canaux = canaux;
	}

	public ArrayList<PositionCase> getCases() {
		return cases;
	}

	public void setCases(ArrayList<PositionCase> cases) {
		this.cases = cases;
	}

	public ArrayList<Carte> getCartes() {
		return cartes;
	}

	public void setCartes(ArrayList<Carte> cartes) {
		this.cartes = cartes;
	}

	public ArrayList<Carte> getCartesPosees() {
		return cartesPosees;
	}

	public void setCartesPosees(ArrayList<Carte> cartesPosees) {
		this.cartesPosees = cartesPosees;
	}

	public ArrayList<PositionCase> getPalmiers() {
		return palmiers;
	}

	public void setPalmiers(ArrayList<PositionCase> palmiers) {
		this.palmiers = palmiers;
	}
}
