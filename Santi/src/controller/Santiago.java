package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

import model.Carte;
import model.Joueur;
import model.Plateau;
import model.PositionSegment;

public class Santiago {
	private ArrayList<Joueur> listJoueurs;
	private int nbCanaux;
	private int niveau;
	private String avecPalmier;
	private Plateau plateau;
	private int nbTours; // décrémenter à chaque fin de tour
	
	public void configurer(){
		// configurer partie Flo
		
		// choix du niveau, 
		niveauPartie();
		
		// init liste joueurs, 
		initJoueur();
		
		// init plateau (avec positionnement source)
		plateau = new Plateau(niveau, listJoueurs.size());
		
		// mettre a jour le nbTours de la partie
		// nbCanaux en fonction nb joueurs
		if (listJoueurs.size() == 3 || listJoueurs.size() == 4) {
			nbTours = 11;
			nbCanaux = 11;
		} else {
			nbTours = 9;
			nbCanaux = 9;
		}
	}
	
	public void initJoueur() {
		// init joueur Flo
		// init couleur et nom pour chaque joueur et remplir la liste
		// verif nombre joueur
		// 
		Scanner sc = new Scanner(System.in);
		int nbJ = 0;
		while (nbJ != 3 || nbJ != 4 || nbJ != 5) {
			System.out.println("\nVeuillez entrer le nombre de joueurs (3, 4 ou 5) pour la partie : ");
			nbJ = sc.nextInt();
		}
		
		int i = 0;
		while (i < nbJ) {
			Joueur j = null;
			String n = "";
			ArrayList<String> couleurs = new ArrayList<String>();
			couleurs.add("blanc");
			couleurs.add("noir");
			couleurs.add("violet");
			couleurs.add("gris");
			couleurs.add("beige");
			String c = "";
			
			while (n.compareTo("") == 0) {
				System.out.println("Indiquez le nom du joueur : ");
				n = sc.next();
			}
			
			int k = 0;
			while (c.compareTo("") == 0) {
				System.out.println("Indiquez votre couleur parmi : ");
				while (k < couleurs.size()) {
					System.out.println("\t"+couleurs.get(k));
					k++;
				}
				c = sc.next();
				k = 0;
				while (c.compareTo(couleurs.get(k)) != 0 && k < couleurs.size()) {
					k++;
				}
				if (k != couleurs.size() && c.compareTo("") != 0) {
					couleurs.remove(k);
					j = new Joueur(n, c);
				}
			}
			listJoueurs.add(j);
			i++;
		}
		
	}
	
	public void niveauPartie(){
		// TEST to test Chris
//		System.out.println("Choisissez votre niveau : 0 pour normal, 1 pour expérimenté");
//		int s = Saisie.IN.nextIntBlank();
//		if (s == 1){
//			this.niveau = 1;
//		}else{
//			this.niveau = 0;
//		}
		// J'avais commencé à faire un truc dans configurerPartie() ^^'
		// l'attribut avecPalmier, c'est pas un booleen ?!
		// Et je viens de revoir qu'on avait seulement mis un niveau (0 ou 1) pour la source. On met pas un booleen pour savoir si on cache l'argent dans joueur ?
		Scanner sc = new Scanner(System.in);
		while (niveau != 1 || niveau != 2 || niveau != 3 || niveau != 4) {
			System.out.println("\nDifférents niveaux vous sont proposés (entrez le numéro) :");
			System.out.println("\t1 - Pas de palmiers, argent visible");
			System.out.println("\t2 - Pas de palmiers, argent caché");
			System.out.println("\t3 - Avec palmiers, argent caché");
			System.out.println("\t4 - Avec palmiers, argent caché, source sur un bord");
		
			niveau = sc.nextInt();
		}
	}
	
	public void encherirCarte(){
		/* Enchère carte Anthony
		appeler une méthode qui va chercher sur le plateau les 4 ou 5 prochaines cartes
		changer statut du joueur ayant l'enchère la plus basse en constructeur
		prévoir PASSER SON TOUR
		mettre à jour possesseur de la carte
		pose des cartes dans l'ordre décroissant des enchères
		mettre à jour les marqueurs
		mettre à jour solde des joueurs */
		int enchereJoueur;
		HashMap<Joueur,Integer> tabEnchere = new HashMap<Joueur, Integer>();
		Scanner sc = new Scanner(System.in);
		int carteChoisie;
		System.out.println("Tirage des cartes :");
		ArrayList<Carte> cartesDevoilees = this.plateau.tirerCarte();
		System.out.println("Voici les cartes tirées :");
		System.out.println(cartesDevoilees.toString());
		System.out.println("Phase d'enchère");
		for (int i=0; i < listJoueurs.size(); i++){
			System.out.println("Joueur :"+(i+1));
			System.out.println("Veuillez indiquer le montant de votre enchère : (0 pour passer son tour)");
			enchereJoueur = sc.nextInt();
			while (enchereJoueur > listJoueurs.get(i).getSolde()){
				System.out.println("Votre enchère est plus élevée que votre solde! Indiquez une nouvelle enchère.");
				enchereJoueur = sc.nextInt();
			}
			if (enchereJoueur == 0){
				//passer son tour PREVOIR CONSTRUCTEUR
			}
			tabEnchere.put(listJoueurs.get(i), enchereJoueur);
		}
		System.out.println("Résultat de la phase d'enchère...");
		while (!tabEnchere.isEmpty()){
			Joueur JoueurGagnant = enchereMax(tabEnchere);
			System.out.println("Joueur "+JoueurGagnant.getNom()+" avec une enchère de : "+tabEnchere.get(JoueurGagnant).intValue());
			System.out.println("Liste des cartes : ");
			System.out.println(cartesDevoilees.toString());
			System.out.println("Choisissez une carte : ");
			while (!cartesDevoilees.isEmpty()){
				for (int i=0; i<cartesDevoilees.size(); i++){
					System.out.println(cartesDevoilees.get(i).toString()+ " "+(i+1));
				}
				carteChoisie = sc.nextInt();
				//Maj possesseur
				cartesDevoilees.get(carteChoisie).setPossesseur(JoueurGagnant);
				//Maj marqueurs
				JoueurGagnant.setNbMarqueurDispos(JoueurGagnant.getNbMarqueurDispos() - cartesDevoilees.get(carteChoisie).getNbMarqueurMax());
				//Maj solde
				JoueurGagnant.setSolde(JoueurGagnant.getSolde() - tabEnchere.get(JoueurGagnant).intValue());
				//Pose de la carte
				this.plateau.poserUneCarte(cartesDevoilees.get(carteChoisie));
				cartesDevoilees.remove(carteChoisie);
			}
			tabEnchere.remove(JoueurGagnant);
		}
		
	}
	
	public Joueur enchereMax(HashMap<Joueur, Integer> tab){
		Joueur res = null;
		int maxValue=(Collections.max(tab.values()));
		for (Entry<Joueur, Integer> entry : tab.entrySet()) {
            if (entry.getValue()==maxValue) {
                 res = entry.getKey();
            }
		}
		return res;
	}
	
	public void soudoyerConstructeur(){
		// tour soudoiement Flo
		

		// pose position canal temp
		// stocker la liste des propositions de placement de canal
		// associer une position à une liste de joueur
		// proposer montant (mettre à jour Joueur.enchereConstructeur)
		// prévoir PASSER SON TOUR
		// trouver un motherfucking compromis Flo
		
		// Sous quelle forme donne-t-on la position du canal temp ? Ac interface graphique, cliquer sur l'endroit ? Dire: l2, case 3 ?
		// Tour joueurs, à démarrer à la gauche du constructeur !!
		int depart = -1;
		int i = 0;
		while (i < listJoueurs.size() && depart == -1) {	// Trouver la position de départ (après le constructeur)
			if (listJoueurs.get(i).isEstConstructeur()) {
				depart = i+1;
			}
			i++;
		}
		if (depart == listJoueurs.size()) {
			depart = 0;
		}
		
		// Tour d'enchères :
		Scanner sc = new Scanner(System.in);
		HashMap<PositionSegment, ArrayList<Joueur>> enchereConstr = new HashMap<PositionSegment, ArrayList<Joueur>>();
		PositionSegment canal = null;
		ArrayList<Joueur> joueursEnch = new ArrayList<Joueur>();
		String c = "";
		int montant = 0;
		Joueur j = null;
		// Boucle sur les joueurs :
		i = depart;
		while (!listJoueurs.get(i).isEstConstructeur()) {
			while (i < listJoueurs.size() && !listJoueurs.get(i).isEstConstructeur()) {
				j = listJoueurs.get(i);
				System.out.println("Voulez-vous soudoyer le constructeur de canal ? (o/n) ");
				c = sc.next();
				if (c.compareTo("o") == 0) {
					System.out.println("Indiquez où vous souhaitez poser le canal : ");
					// récupérer la popsition, I don't know how, in which format
					// transformer cette position en positionSegment 
					// canal = ;
					System.out.println("Combien proposez-vous pour votre canal ?");
					montant = sc.nextInt();
					if (montant > 0 && montant < j.getSolde()) {
						// récupérer enchère
						j.setEnchereConstructeur(montant);
						if (enchereConstr.containsKey(canal)) {
							// ajouter le joueur à l'arrayList si position de canal déjà proposée
							enchereConstr.get(canal).add(j);
						} else {	// ajouter dans la hashmap
							joueursEnch.add(j);
							enchereConstr.put(canal, joueursEnch);
						}
					} else {
						System.out.println("Enchère incorrecte, vous ne pouvez pas poser de canal.");
					}
					
				} else {
					System.out.println("Vous avez passé votre tour.");
				}
				i++;
			}
			if (i == listJoueurs.size()) {
				i = 0;
			}
		}
		
		// trouver le constructeur 
		// faire choisir une enchère
		// placer le canal
		int constr = -1;
		if (depart == 0) {
			constr = listJoueurs.size()-1;
		} else {
			constr = depart - 1;
		}
		System.out.println("Voici les propositions de construction des canaux : ");
		// Indiquez quel joueur a proposé quoi ?
		i = 0;
		// itérer sur la map, montrer les propositions
		//while ()
	}
	
	public void irrigationSupplementaire(){
		/* Irrigation supplémentaire Anthony
		parcours des joueurs
		appeler placerCanalSup
		changer boolean tuyauSup
		prévoir PASSER SON TOUR */
		Scanner sc = new Scanner(System.in);
		String choix = "";
		System.out.println("Phase d'irrigation supplémentaire");
		for (int i=0; i<listJoueurs.size(); i++){
			System.out.println("Joueur "+listJoueurs.get(i).getNom());
			System.out.println("Voulez vous placer un canal supplémentaire ? O/N");
			choix = sc.nextLine();
			if (choix.compareTo("O") == 0 || choix.compareTo("o") == 0){
				if (listJoueurs.get(i).isTuyauSup()){
					System.out.println("Vous n'avez plus de canal supplémentaire!");
					break;
				}
				else {
					System.out.println("Indiquez où placer le canal.");
					// prévoir placement avec position
					this.plateau.placerCanalSup();
					listJoueurs.get(i).setTuyauSup(false);
				}
			}
			else if (choix.compareTo("N") == 0 || choix.compareTo("n") == 0)
				System.out.println("Vous n'avez pas placé de canal pour ce tour.");
				//Prevoir cas de merde si ce n'est ni "O" ni "N" 
				//Vraiment besoin de la méthode passer son tour ici ?
		}
	}
	
	public void secheresse(){
		// TEST to test Chris
		// check si on est arrivé au dernier tour
		if (this.nbTours > 1){
			plateau.secheresse();
		}
	}
	
	public void diaDePaga(){
		for (Iterator<Joueur> iterator = this.listJoueurs.iterator(); iterator.hasNext();) {
			Joueur j = iterator.next();
			j.setSolde(j.getSolde()+3);
		}
	}
	
}
