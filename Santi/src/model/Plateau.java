package model;

import java.util.ArrayList;

import singleton.Saisie;
import vue.AffichageConsole;

public class Plateau {
	private PositionIntersection source;
	private ArrayList<PositionSegment> canaux;
	private ArrayList<PositionCase> cases;
	private ArrayList<Carte> cartes;
	private ArrayList<Carte> cartesPosees;
	private ArrayList<PositionCase> palmiers;
	private int nbJoueurs;
	
	
	
	private Plateau(int niveau){
		// FIXME fuckin' crackage, constructeur useless
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
		this.nbJoueurs = nbJoueur;
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
		// TEST to test Chris
		ArrayList<Carte> carteTirage = null;
		if(this.nbJoueurs==5){
			carteTirage = this.popArrayList(5);
		}else{
			carteTirage = this.popArrayList(4);
		}
		return carteTirage;
	}
	
	public void poserUneCarte(Carte carteAPoser){
		// FIXME basculer l'interaction dans le controleur, rien a foutre dans le model (encherir) !! Chris
		// TEST to test Chris
		// Demander à l'utilisateur de  choisir une position pour poser la carte
		boolean pose = false;
		while(!pose){
			System.out.println("Voici le plateau, choississez une position pour poser votre carte");
			AffichageConsole.afficheMatrice(8, 6);
			
			System.out.println("Première coordonnée, les abscisse : ");
			int x = Saisie.IN.nextIntWithRangeNotBlank(0, 7);
			
			System.out.println("Deuxième coordonnée, les ordonnées : ");
			int y = Saisie.IN.nextIntWithRangeNotBlank(0, 5);
			
			Position positionChoisie = new PositionCase(x,y);
			if (this.cases.contains(positionChoisie)){
				int indexPosition = this.cases.indexOf(positionChoisie);
				positionChoisie = this.cases.get(indexPosition);
				if (positionChoisie.isOccupe()){
					System.out.println("Cette Case est déjà occupée...");
				}else{
					this.cartesPosees.add(carteAPoser);
					PositionCase positionCase = this.cases.get(indexPosition);
					positionCase.setOccupe(true);
					carteAPoser.setPosition(positionCase);
					pose = true;
				}
			}
		}
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

	public void secheresse() {
		// TODO sécheresse Chris
		// décrémenter le nombre de marqueurs des cartes non irriguées
		// mettre en sécheresse les cartes sans marqueurs
		
	}
	
	public void majIrrigation(){
		// TODO irrigation Chris
		// FIXME ajouté cette maj a chaque fois que l'on pose un canal
		// masse calcul
		
	}
	
	private ArrayList<Carte> popArrayList(int nbCartes){
		// TEST to test Chris
		ArrayList<Carte> carteTirage = new ArrayList<Carte>(nbCartes);
		for (int i = 0 ; i < nbCartes ; i++) {
			carteTirage.add(this.cartes.get(this.cartes.size()-1));
			this.cartes.remove(this.cartes.size()-1);
		}
		return carteTirage;
	}
	
//	public static void main(String [] args){
//		Plateau p = new Plateau(0,4);
//		p.poserUneCarte(new Carte(2, "banane"));
//	}
}
