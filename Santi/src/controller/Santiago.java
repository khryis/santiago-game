package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import model.Carte;
import model.Joueur;
import model.Plateau;

public class Santiago {
	private ArrayList<Joueur> listJoueurs;
	private int nbCanaux;
	private int niveau;
	private String avecPalmier;
	private Plateau plateau;
	private int nbTours; // décrémenter à chaque fin de tour
	
	public void configurer(){
		// TODO configurer partie Flo
		// choix du niveau,  
		// init liste joueurs, 
		// nbCanaux en fonction nb joueurs
		// init plateau (avec positionnement source)
		// mettre a jour le nbTours de la partie
	}
	
	public void initJoueur() {
		// TODO init joueur Flo
		// init couleur et nom pour chaque joueur et remplir la liste
		// verif nombre joueur
		// 
	}
	
	public void niveauPartie(){
		// TODO choisir niveau Chris
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
		// TODO tour soudoiement Flo
		// pose position cannal temp
		// stocker la liste des propositions de placement de canal
		// associer une position à une liste de joueur
		// proposer montant (mettre à jour Joueur.enchereConstructeur)
		// prévoir PASSER SON TOUR
		// TODO trouver un motherfucking compromis Flo
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
		// TODO secheresse Chris
		// décrémenter le nombre de marqueurs des cartes non irriguées
		// mettre en sécheresse les cartes sans marqueurs
		// check si on est au dernier tour ou non
		// dezoifhiufehr
		// ofiehruigdfv
	}
	
	public void diaDePaga(){
		// TODO money money money Chris
		// parcourir les joueurs et leur donner des sous sous dans leurs popoches
		// YIPPEE KAY YAY
	}
	
}
