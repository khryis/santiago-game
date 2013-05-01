package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;

import exception.AucunJoueurParoleException;
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
    private int indiceJoueurCourant;

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

    /**
     * Pour initialiser la partie il faut déjà avoir le niveau et la liste des
     * joueurs
     */
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
        indiceJoueurCourant = positionApresConstructeur();
        configured = true;
        repercuterModification();
    }

    /**
     * Permet de changer le statu constructeur d'un joueur donnée
     * 
     * @param indexJoueurs
     * @param estConstructeur
     */
    public void setConstructeur(int indexJoueurs, boolean estConstructeur) {
        listJoueurs.get(indexJoueurs).setConstructeur(estConstructeur);
    }

    /**
     * Permet de déterminer si le joueur devient constructeur pendant le tour
     * d'enchère
     * 
     * @param indexJoueur
     * @param enchereJoueur
     * @param dernier
     * @return
     */
    public boolean devientConstructeurApresEnchere(int indexJoueur, int enchereJoueur, boolean dernier) {
        boolean constructeurTrouve = false;

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

    /**
     * Tester si la partie est finie
     * 
     * @return
     */
    public boolean isFinish() {
        if (nbTours == 0) {
            return true;
        }
        return false;
    }

    /**
     * Setter le niveau de la partie, les palmiers et la position de la source
     * 
     * @param niv
     */
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

    /**
     * On tire nos cartes de la liste de carte et on les dévoile
     */
    public void devoilerCarte() {
        plateau.setCartesDevoilees(plateau.tirerCarte());
    }

    /**
     * Permet de déterminer un constructeur au début du jeu
     */
    public void determinerUnConstructeur() {
        // Constructeur aléatoire pour premier tour
        int rdm = (int) (Math.random() * listJoueurs.size());
        listJoueurs.get(rdm).setConstructeur(true);
    }

    /**
     * Applique la sécherresse à toute les cartes non irrigué Appel à sécheresse
     * de plateau si on est pas au dernier tour de la partie
     */
    public void secheresse() {
        // TEST secheresse partie 1 Chris
        // check si on est arrivé au dernier tour
        if (nbTours > 1) {
            plateau.secheresse();
        }
    }

    /**
     * Distribution de l'argent à la fin de chaque tour
     */
    public void diaDePaga() {
        for (Joueur j : listJoueurs) {
            j.ajouterArgent(3);
        }
        resetEnchereVars();
    }

    /**
     * Retourne l'index du constructeur dans la liste des joueurs
     */
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

    /**
     * Retourne l'index du joueur après le constructeur dans la liste des
     * joueurs
     * 
     * @return
     */
    public int positionApresConstructeur() {
        int positionApresContructeur = positionConstructeur() + 1;
        if (positionApresContructeur == listJoueurs.size()) {
            positionApresContructeur = 0;
        }
        return positionApresContructeur;
    }

    public void incrementerJoueurCourant() {
        int indice = getIndiceJoueurCourant() + 1;
        if (indice == listJoueurs.size()) {
            indice = 0;
        }
    }

    public Joueur joueurPlaying() {
        Joueur joueur = listJoueurs.get(indiceJoueurCourant);
        if (joueur == null) {
            try {
                throw new AucunJoueurParoleException();
            } catch (AucunJoueurParoleException e) {
                e.printStackTrace();
            }
        }
        return joueur;
    }

    public boolean encherir(int value) {
        boolean reussi = false;
        int enchere = value <= 0 ? 0 : value;
        Joueur joueur = getListJoueurs().get(indiceJoueurCourant);
        int solde = joueur.getSolde();

        if (enchere > solde) {
            System.out.println("Pas assez d'argent pour encherir de la sorte (max " + solde + ")");
        } else if (enchere < 0) {
            System.out.println("Problème, enchère négative");
        } else if (tabEnchere.containsValue(enchere) && enchere != 0) {
            System.out.println("Quelqu'un a déjà enchèri par " + enchere + "€");
        } else {
            reussi = true;
            incrementerJoueurCourant();
            // TODO suite incrémenter joueur courant
        }

        return reussi;
    }

    public void passerSonTour() {
        incrementerJoueurCourant();
    }

    /**
     * Permet à un joueur de poser une carte à une position (x;y) Appel la
     * fonction poserUneCarte de plateau, si on à réussi à poser la carte sur le
     * plateau On fait tout les autres traitements, affectation de la carte au
     * joueur, à la case, etc
     * 
     * @param joueur
     * @param carteAPoser
     * @param x
     * @param y
     * @return
     */
    public boolean poserUneCarte(Joueur joueur, Carte carteAPoser, int x, int y) {

        boolean estPosee = false;
        if (plateau.poserUneCarte(carteAPoser, x, y)) {
            // Maj possesseur
            carteAPoser.setPossesseur(joueur);

            // Maj marqueurs
            joueur.setNbMarqueurDispos(joueur.getNbMarqueurDispos() - carteAPoser.getNbMarqueurMax());

            // Maj solde
            // FIXME bizarre, pourquoi tabEnchere.isEmpty ? plutot tester la
            // somme de tabEnchere pour le joueur
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

    /**
     * Permet de trouver le joueur qui à poser la plus grosse enchère dans
     * tabEnchère
     * 
     * @return
     */
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

    /**
     * Pareil avec l'enchère minimum
     * 
     * @return
     */
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

    /**
     * Permet à un joueur de soudoyer d'une somme le constructeur à une position
     * de segment (Ou appuyé une proposition d'un autre joueur si elle est déjà
     * faite)
     * 
     * @param canal
     * @param joueur
     * @param montant
     * @return
     */
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
                System.out.println("proposition n'existe pas, ajoute canal et joueur");
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

    /**
     * Permet au constructeur de positionner un canal, choisi par lui ou dans
     * ceux qu'on lui à proposé en renseignant la position
     * 
     * @param x
     * @param y
     * @param x2
     * @param y2
     * @return
     */
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
                plateau.placerCanal(canal);
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

    /**
     * Permet de déterminer si le constructeur à les moyens de surencherir sur
     * les sudoiements pour placer un canal ailleurs que les porpositions
     * 
     * @return
     */
    public boolean constructeurPeutEncherir() {
        boolean peutEncherir;
        int total = 0, indiceJCourant;
        ArrayList<Joueur> joueursEnch = new ArrayList<>();
        for (Map.Entry<PositionSegment, ArrayList<Joueur>> entry : enchereConstr.entrySet()) {
            indiceJCourant = 0;
            joueursEnch = entry.getValue();
            while (indiceJCourant < joueursEnch.size()) {
                total += joueursEnch.get(indiceJCourant).getEnchereConstructeur();
                indiceJCourant++;
            }
        }
        if (total > listJoueurs.get(positionConstructeur()).getSolde()) {
            peutEncherir = false;
        } else {
            peutEncherir = true;
        }
        return peutEncherir;
    }

    /**
     * Permet de réinitialiser les objets stockant les infos sur le enchères
     */
    public void resetEnchereVars() {
        int indiceJCourant;
        ArrayList<Joueur> joueursEnch = new ArrayList<>();
        for (Map.Entry<PositionSegment, ArrayList<Joueur>> entry : enchereConstr.entrySet()) {
            indiceJCourant = 0;
            joueursEnch = entry.getValue();
            while (indiceJCourant < joueursEnch.size()) {
                joueursEnch.get(indiceJCourant).setEnchereConstructeur(0);
                joueursEnch.get(indiceJCourant).setEnchereCarte(0);
                indiceJCourant++;
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

    /**
     * Méthode à ajouter à la fin de chaque fonction qui modifie le modèle
     * Permet de notifier les obsevers (tous le JPANEL) de déclancher leur
     * méhode update()
     */
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

    public int getIndiceJoueurCourant() {
        return indiceJoueurCourant;
    }

    public void setIndiceJoueurCourant(int joueurCourant) {
        this.indiceJoueurCourant = joueurCourant;
    }

}
