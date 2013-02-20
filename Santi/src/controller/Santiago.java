package controller;

import java.util.ArrayList;

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
		// TODO enchère carte Anthony
		// appeler une méthode qui va chercher sur le plateau les 4 ou 5 prochaines cartes
		// changer statut du joueur ayant l'enchère la plus basse en constructeur
		// prévoir PASSER SON TOUR
		// mettre à jour possesseur de la carte
		// pose des cartes dans l'ordre décroissant des enchères
		// mettre à jour les marqueurs
		// mettre à jour solde des joueurs
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
		// TODO irrigation supplémentaire Anthony
		// parcours des joueurs
		// appeler placerCanalSup
		// changer boolean tuyauSup
		// prévoir PASSER SON TOUR
	}
	
	public void secheresse(){
		// TODO secheresse Chris
		// décrémenter le nombre de marqueurs des cartes non irriguées
		// mettre en sécheresse les cartes sans marqueurs
		// check si on est au dernier tour ou non
	}
	
	public void diaDePaga(){
		// TODO money money money Chris
		// parcourir les joueurs et leur donner des sous sous dans leurs popoches
	}
	
}
