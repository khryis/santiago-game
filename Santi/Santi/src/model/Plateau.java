package model;

import java.util.ArrayList;

public class Plateau {
	private PositionIntersection source;
	private ArrayList<PositionSegment> canaux;
	private ArrayList<PositionCase> cases;
	private ArrayList<Carte> cartes;
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
}
