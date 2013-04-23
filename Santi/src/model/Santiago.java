package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;

import exception.MauvaisePositionCanalException;

public class Santiago extends Observable {

    private static Santiago SANTIAGO;
    private ArrayList<Joueur> listJoueurs;
    private int nbCanaux;
    private NiveauPartie niveau;
    private int niveauSource;
    private boolean avecPalmier;
    private Plateau plateau;
    private int nbTours; // décrémenter à chaque fin de tour
    private final HashMap<Joueur, Integer> tabEnchere;
    private HashMap<PositionSegment, ArrayList<Joueur>> enchereConstr = new HashMap<>();
    private Joueur joueurGagnant;
    private boolean configured;

    public enum TypeEnchere {
        CARTE, CONSTRUCTEUR
    }

    private Santiago() {
        super();
        listJoueurs = new ArrayList<>();
        nbCanaux = 0;
        niveauSource = 0;
        avecPalmier = false;
        plateau = null;
        nbTours = 0;
        tabEnchere = new HashMap<>();
        enchereConstr = new HashMap<>();
        joueurGagnant = null;
        configured = false;
    }

    public static Santiago getSantiago() {
        if (SANTIAGO == null) {
            SANTIAGO = new Santiago();
        }
        return SANTIAGO;
    }

    public void initPartie() {
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
        determinerUnConstructeur();
        configured = true;
        repercuterModification();
    }

    public void setConstructeur(int indexJoueurs, boolean estConstructeur) {
        listJoueurs.get(indexJoueurs).setConstructeur(estConstructeur);
    }

    public boolean devientConstructuerApresEnchere(int indexJoueur, int enchereJoueur, boolean dernier) {
        boolean constructeurTrouve = false;

        listJoueurs.get(indexJoueur).setEnchereCarte(enchereJoueur);
        // on regarde si on trouve le nouveau constructeur
        if (enchereJoueur == 0 && !constructeurTrouve) {
            setConstructeur(indexJoueur, true);
            constructeurTrouve = true;
        }
        tabEnchere.put(listJoueurs.get(indexJoueur), enchereJoueur);
        listJoueurs.get(indexJoueur).setEnchereCarte(enchereJoueur);

        if (!dernier) {
            // TODO
        }
        return constructeurTrouve;
    }

    public boolean isFinish() {
        if (nbTours == 0) {
            return true;
        }
        return false;
    }

    public void setNiveauPartie(NiveauPartie niv) {
        niveau = niv;
        switch (niveau) {
            case FACILE:
                avecPalmier = NiveauPartie.FACILE.withPalmier();
                niveauSource = NiveauPartie.FACILE.getNiveauSource();
                break;
            case MOYEN:
                avecPalmier = NiveauPartie.MOYEN.withPalmier();
                niveauSource = NiveauPartie.MOYEN.getNiveauSource();
                break;
            case DIFFICILE:
                avecPalmier = NiveauPartie.DIFFICILE.withPalmier();
                niveauSource = NiveauPartie.DIFFICILE.getNiveauSource();
                break;
            default:
                avecPalmier = NiveauPartie.FACILE.withPalmier();
                niveauSource = NiveauPartie.FACILE.getNiveauSource();
                break;
        }
    }

    public void devoilerCarte() {
        plateau.setCartesDevoilees(plateau.tirerCarte());
    }

    public void determinerUnConstructeur() {
        // Constructeur aléatoire pour premier tour
        int rdm = (int) Math.random() * listJoueurs.size();
        listJoueurs.get(rdm).setConstructeur(true);
    }

    public void secheresse() {
        // TEST secheresse partie 1 Chris
        // check si on est arrivé au dernier tour
        if (nbTours > 1) {
            plateau.secheresse();
        }
    }

    public void diaDePaga() {
        for (Joueur j : listJoueurs) {
            j.ajouterArgent(3);
        }
        resetEnchereVars();
    }

    public int positionConstructeur() {
        int positionConstructeur = -1;
        int i = 0;
        // Trouver la position du constructeur
        while (i < listJoueurs.size() && positionConstructeur == -1) {
            if (listJoueurs.get(i).isConstructeur()) {
                positionConstructeur = i;
            }
            i++;
        }
        return positionConstructeur;
    }

    public int positionApresConstructeur() {
        int positionApresContructeur = positionConstructeur() + 1;
        if (positionApresContructeur == listJoueurs.size()) {
            positionApresContructeur = 0;
        }
        return positionApresContructeur;
    }

    public boolean poserUneCarte(Joueur joueur, Carte carteAPoser, int x, int y) {

        boolean estPosee = false;
        if (plateau.poserUneCarte(carteAPoser, x, y)) {
            // Maj possesseur
            carteAPoser.setPossesseur(joueur);

            // Maj marqueurs
            joueur.setNbMarqueurDispos(joueur.getNbMarqueurDispos() - carteAPoser.getNbMarqueurMax());

            // Maj solde
            joueur.enleverArgent(tabEnchere.isEmpty() ? 0 : tabEnchere.get(joueur));

            // MAJ marqueurs de la carte si enchère joueur = 0
            if (joueur.getEnchereCarte() == 0) {
                carteAPoser.setNbMarqueurActuel(carteAPoser.getNbMarqueurActuel() - 1);
            }

            // verifie si la carte est irrigué
            plateau.majIrrigation1Carte(carteAPoser);

            // plateau.getCartesPosees().add(carte);
            plateau.popCarteDevoilees(carteAPoser);

            // On enlève la carte des cartes dévoilées
            if (!tabEnchere.isEmpty()) {
                tabEnchere.remove(joueur);
            }
            estPosee = true;
        } else {
            estPosee = false;
        }
        return estPosee;
    }

    public Joueur enchereMax() {
        Joueur res = null;
        int maxValue = Collections.max(tabEnchere.values());
        for (Entry<Joueur, Integer> entry : tabEnchere.entrySet()) {
            if (entry.getValue() == maxValue) {
                res = entry.getKey();
            }
        }
        return res;
    }

    public Joueur enchereMin() {
        Joueur res = null;
        int minValue = Collections.min(tabEnchere.values());
        for (Entry<Joueur, Integer> entry : tabEnchere.entrySet()) {
            if (entry.getValue() == minValue) {
                res = entry.getKey();
            }
        }
        return res;
    }

    public boolean encherePositionCanal(PositionSegment canal, Joueur joueur, int montant) {
        boolean reussi;
        if (montant > 0 && montant < joueur.getSolde()) {
            // seter enchère
            joueur.setEnchereConstructeur(montant);
            if (enchereConstr.containsKey(canal)) {
                System.out.println("Canal existe, ajouté à la liste");
                // ajouter le joueur à l'arrayList si position de
                // canal déjà proposée
                enchereConstr.get(canal).add(joueur);
            } else { // ajouter dans la hashmap
                System.out.println("Canal n'existe pas, ajoute canal et joueur");
                ArrayList<Joueur> enchJoueur = new ArrayList<>();
                enchJoueur.add(joueur);
                enchereConstr.put(canal, enchJoueur);
            }
            reussi = true;
        } else {
            reussi = false;
        }
        return reussi;
    }

    public boolean placerCanalChoisi(int x, int y, int x2, int y2) {
        // mettre à jour le solde des joueurs
        // Quand le constructeur à choisi un des canal proposé par les joueurs
        boolean reussi = false;
        PositionSegment canal = new PositionSegment(x, y, x2, y2);
        ArrayList<Joueur> joueursEnch = enchereConstr.get(canal);
        Joueur constructeur = listJoueurs.get(positionConstructeur());
        try {
            if (joueursEnch != null) {
                for (Joueur joueur : joueursEnch) {
                    joueur.enleverArgent(joueur.getEnchereConstructeur());
                    constructeur.ajouterArgent(joueur.getEnchereConstructeur());
                }
                reussi = true;
            } else {
                throw new MauvaisePositionCanalException();
            }
        } catch (MauvaisePositionCanalException e) {
            e.printStackTrace();
            reussi = false;
        }
        return reussi;
    }

    public boolean constructeurPeutEncherir() {
        boolean peutEncherir;
        int total = 0, indiceJoueurCourant;
        ArrayList<Joueur> joueursEnch = new ArrayList<>();
        for (Map.Entry<PositionSegment, ArrayList<Joueur>> entry : enchereConstr.entrySet()) {
            indiceJoueurCourant = 0;
            joueursEnch = entry.getValue();
            while (indiceJoueurCourant < joueursEnch.size()) {
                total += joueursEnch.get(indiceJoueurCourant).getEnchereConstructeur();
                indiceJoueurCourant++;
            }
        }
        if (total > listJoueurs.get(positionConstructeur()).getSolde()) {
            peutEncherir = false;
        } else {
            peutEncherir = true;
        }
        return peutEncherir;
    }

    public void resetEnchereVars() {
        int indiceJoueurCourant;
        ArrayList<Joueur> joueursEnch = new ArrayList<>();
        for (Map.Entry<PositionSegment, ArrayList<Joueur>> entry : enchereConstr.entrySet()) {
            indiceJoueurCourant = 0;
            joueursEnch = entry.getValue();
            while (indiceJoueurCourant < joueursEnch.size()) {
                joueursEnch.get(indiceJoueurCourant).setEnchereConstructeur(0);
                joueursEnch.get(indiceJoueurCourant).setEnchereCarte(0);
                indiceJoueurCourant++;
            }
        }
        enchereConstr.clear();
        tabEnchere.clear();
    }

    @Override
    public String toString() {
        return "Santiago : \n" + listJoueurs.toString() + ",\nnbCanaux=" + nbCanaux + ",\nniveau=" + niveau + ",\navecPalmier="
                + avecPalmier + ",\n" + plateau.toString() + ",\nnbTours=" + nbTours + "]\n";
    }

    public void repercuterModification() {
        setChanged();
        notifyObservers();
    }

    // GETs & SETs
    public int getNbCanaux() {
        return nbCanaux;
    }

    public int getNiveau() {
        return niveauSource;
    }

    public boolean isAvecPalmier() {
        return avecPalmier;
    }

    public void setNbCanaux(int nbCanaux) {
        this.nbCanaux = nbCanaux;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public void setNbTours(int nbTours) {
        this.nbTours = nbTours;
    }

    public void setNiveau(int niveauSrc) {
        niveauSource = niveauSrc;
    }

    public void setAvecPalmier(boolean avecPalmier) {
        this.avecPalmier = avecPalmier;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public int getNbTours() {
        return nbTours;
    }

    public HashMap<Joueur, Integer> getTabEnchere() {
        return tabEnchere;
    }

    public ArrayList<Joueur> getListJoueurs() {
        return listJoueurs;
    }

    public void setListJoueurs(ArrayList<Joueur> listJoueurs) {
        this.listJoueurs = listJoueurs;
    }

    public Joueur getJoueurGagnant() {
        return joueurGagnant;
    }

    public void setJoueurGagnant(Joueur joueur) {
        joueurGagnant = joueur;
    }

    public HashMap<PositionSegment, ArrayList<Joueur>> getEnchereContructeur() {
        return enchereConstr;
    }

    public boolean isConfigured() {
        return configured;
    }
}
