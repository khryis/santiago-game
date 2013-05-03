package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;

import exception.AucunConstructeurException;
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

    private boolean configured;

    private boolean phaseFinie;

    private int indiceJoueurCourant;
    private boolean trouveConstructeur;
    private Joueur deviendraConstructeur;

    private Joueur choisiCarte;
    private Joueur joueurGagnant;

    private PositionCase positionCaseCourante;
    private PositionSegment positionSegmentCourant;

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

        configured = false;

        phaseFinie = false;

        trouveConstructeur = false;
        deviendraConstructeur = null;

        choisiCarte = null;
        joueurGagnant = null;

        positionCaseCourante = null;
        positionSegmentCourant = null;
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
        phaseFinie = false;
        configured = true;
        repercuterModification();
    }

    /**
     * Permet de setter un joueur en constructeur et d'enlever l'ancien
     * 
     * @param indexJoueurs
     * @param estConstructeur
     */
    public void changerConstructeur(Joueur joueur) {
        listJoueurs.get(positionConstructeur()).setConstructeur(false);
        joueur.setConstructeur(true);
        repercuterModification();
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
    public void setNiveauPartie(NiveauPartie level) {
        this.niveau = level;
        avecPalmier = niveau.withPalmier();
        niveauSource = niveau.getNiveauSource();
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
        repercuterModification();
    }

    /**
     * Distribution de l'argent à la fin de chaque tour
     */
    public void diaDePaga() {
        for (Joueur j : listJoueurs) {
            j.ajouterArgent(3);
        }
        // TODO tour - 1
        resetEnchereVars();
        repercuterModification();
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

    public void incrementerJoueurCourantEnchere() {
        indiceJoueurCourant++;
        if (indiceJoueurCourant == listJoueurs.size()) {
            indiceJoueurCourant = 0;
        }
        if (indiceJoueurCourant == positionApresConstructeur()) {
            setPhaseFinie(true);
            if (trouveConstructeur) {
                changerConstructeur(deviendraConstructeur);
                deviendraConstructeur = null;
            } else if (!trouveConstructeur) {
                changerConstructeur(enchereMin());
            } else {
                try {
                    throw new AucunConstructeurException();
                } catch (AucunConstructeurException e) {
                    e.printStackTrace();
                }
            }
            choisiCarte = enchereMax();
            indiceJoueurCourant = listJoueurs.indexOf(choisiCarte);
        }
    }

    public void incrementerJoueurCourantSoudoiement() {
        indiceJoueurCourant++;
        if (indiceJoueurCourant == listJoueurs.size()) {
            indiceJoueurCourant = 0;
        }
        if (indiceJoueurCourant == positionConstructeur()) {
            setPhaseFinie(true);
        }
    }

    public void incrementerJoueurCourant() {
        indiceJoueurCourant++;
        if (indiceJoueurCourant == listJoueurs.size()) {
            indiceJoueurCourant = 0;
        }
        if (indiceJoueurCourant == positionApresConstructeur()) {
            setPhaseFinie(true);
        }
        repercuterModification();
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

    /**
     * Permet de déterminer si le joueur devient constructeur pendant le tour
     * d'enchère
     * 
     * @param indexJoueur
     * @param enchere
     * @param dernier
     * @return
     */
    public boolean deviendraConstructeur(int enchere) {
        boolean deviendra = false;
        // on regarde si on trouve le nouveau constructeur
        if (enchere == 0 && !trouveConstructeur) {
            deviendraConstructeur = joueurPlaying();
            trouveConstructeur = true;
            deviendra = true;
        }
        return deviendra;
    }

    public boolean encherir(int enchere) {
        boolean reussi = false;
        Joueur joueur = getListJoueurs().get(indiceJoueurCourant);
        int solde = joueur.getSolde();
        enchere = (enchere < 0 ? 0 : enchere);

        if (enchere > solde) {
            System.out.println("Pas assez d'argent pour encherir de la sorte (max " + solde + ")");
            reussi = false;
        } else if (enchere < 0) {
            // Jamais dans ce cas avec le ternaire au dessus
            System.out.println("Problème, enchère négative");
            reussi = false;
        } else if (tabEnchere.containsValue(enchere) && enchere != 0) {
            System.out.println("Quelqu'un a déjà enchèri par " + enchere + "€");
            reussi = false;
        } else {
            reussi = true;
            tabEnchere.put(joueur, enchere);
            joueur.setEnchereCarte(enchere);
            if (deviendraConstructeur(enchere)) {
                System.out.println("joueur : " + joueur + ", enchere " + tabEnchere.get(joueur) + " deviendra constructeur");
            } else {
                System.out.println("joueur : " + joueur + ", enchere " + tabEnchere.get(joueur));
            }
            incrementerJoueurCourantEnchere();
            repercuterModification();
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
    public boolean poserUneCarte(int indexCarteAPoser, int x, int y) {
        // on récupère le joueur entrain de poser une carte
        Joueur joueur;
        if (!tabEnchere.isEmpty()) {
            joueur = enchereMax();
            // Dans le cas du premier joueur et quans on est a trois, dernière
            // carte
            // placé par la meilleur enchere
            if (listJoueurs.size() == tabEnchere.size()) {
                joueurGagnant = joueur;
            }
        } else {
            joueur = joueurGagnant;
        }

        Carte carteAPoser = plateau.getCartesDevoilees().get(indexCarteAPoser);
        boolean estPosee = false;
        // on esseye de poser la carte et après on set tous les attributs
        // qui vont biens
        if (plateau.poserUneCarte(carteAPoser, x, y)) {
            // Maj possesseur
            carteAPoser.setPossesseur(joueur);

            // Maj marqueurs
            joueur.setNbMarqueurDispos(joueur.getNbMarqueurDispos() - carteAPoser.getNbMarqueurMax());

            // Maj solde
            // somme de tabEnchere pour le joueur gagnant
            joueur.enleverArgent(tabEnchere.isEmpty() ? 0 : tabEnchere.get(joueur));

            // MAJ marqueurs de la carte si enchère joueur = 0
            if (joueur.getEnchereCarte() == 0) {
                carteAPoser.setNbMarqueurActuel(carteAPoser.getNbMarqueurActuel() - 1);
            }

            // verifie si la carte est irrigué
            plateau.majIrrigation1Carte(carteAPoser);

            // On enlève la carte des cartes dévoilées
            plateau.popCarteDevoilees(carteAPoser);

            // réinitialise l'attribut enchère du joueur
            joueur.setEnchereCarte(0);
            // on retire de tabEnchere les joueur et son enchère
            if (!tabEnchere.isEmpty()) {
                tabEnchere.remove(joueur);
            }
            if (plateau.getCartesDevoilees().isEmpty()) {
                phaseFinie = true;
                indiceJoueurCourant = positionApresConstructeur();
            }
            estPosee = true;
            repercuterModification();
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
    public boolean encherePositionCanal(PositionSegment canal, int montant) {
        boolean reussi;
        Joueur joueur = joueurPlaying();

        if (montant > 0 && montant < joueur.getSolde()) {
            // seter enchère
            joueur.setEnchereConstructeur(montant);
            if (enchereConstr.containsKey(canal)) {
                System.out.println("Canal existe, ajouté à la liste");
                // ajouter le joueur à l'arrayList si position de
                // canal déjà proposée
                enchereConstr.get(canal).add(joueur);
            } else { // ajouter dans la hashmap
                System.out.println("proposition n'existe pas, ajoute canal et joueur au tabConstruction");
                ArrayList<Joueur> enchJoueur = new ArrayList<>();
                enchJoueur.add(joueur);
                enchereConstr.put(canal, enchJoueur);
            }
            incrementerJoueurCourantSoudoiement();
            repercuterModification();
            reussi = true;
        } else {
            reussi = false;
        }
        return reussi;
    }

    /**
     * Permet au constructeur de positionner un canal dans ceux qu'on lui à
     * proposé en renseignant la position
     * 
     * @param x
     * @param y
     * @param x2
     * @param y2
     * @return
     */
    public boolean placerCanalChoisi(PositionSegment canal) {
        // mettre à jour le solde des joueurs
        // Quand le constructeur à choisi un des canal proposé par les joueurs
        boolean reussi = false;
        ArrayList<Joueur> joueursEnch = enchereConstr.get(canal);
        Joueur constructeur = listJoueurs.get(positionConstructeur());
        try {
            if (joueursEnch != null) {
                if (plateau.placerCanal(canal)) {
                    for (Joueur joueur : joueursEnch) {
                        joueur.enleverArgent(joueur.getEnchereConstructeur());
                        constructeur.ajouterArgent(joueur.getEnchereConstructeur());
                        joueur.setEnchereConstructeur(0);
                    }
                    reussi = true;
                    phaseFinie = true;
                } else {
                    reussi = false;
                }
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
     * Permet au constructeur de canal de poser le canal de son choix
     * 
     * @param canal
     * @return
     */
    public boolean placerCanalDeSonChoix(PositionSegment canal) {
        boolean reussi = false;
        Joueur constructeur = getListJoueurs().get(positionConstructeur());
        if (constructeurPeutEncherir()) {
            if (plateau.placerCanal(canal)) {
                constructeur.enleverArgent(plusGrossePropositionCanal() + 1);
                reussi = true;
                phaseFinie = true;
            }
        }
        return reussi;
    }

    public boolean irrigationSupplementaire(PositionSegment canal) {
        boolean place = false;

        if (!listJoueurs.get(indiceJoueurCourant).hasTuyauSup()) {
            System.out.println("Vous n'avez plus de canal supplémentaire!");
            place = true;
            incrementerJoueurCourant();
        } else if (plateau.placerCanal(canal)) {
            listJoueurs.get(indiceJoueurCourant).setTuyauSup(false);
            place = true;
            incrementerJoueurCourant();
        }

        return place;
    }

    /**
     * Permet de déterminer si le constructeur à les moyens de surencherir sur
     * les sudoiements pour placer un canal ailleurs que les porpositions
     * 
     * @return
     */
    public boolean constructeurPeutEncherir() {
        return (plusGrossePropositionCanal() < listJoueurs.get(positionConstructeur()).getSolde());
    }

    public int plusGrossePropositionCanal() {
        int total = 0, totalTmp = 0, indiceJCourant;
        ArrayList<Joueur> joueursEnch = new ArrayList<>();
        for (Map.Entry<PositionSegment, ArrayList<Joueur>> entry : enchereConstr.entrySet()) {
            indiceJCourant = 0;
            totalTmp = 0;
            joueursEnch = entry.getValue();
            while (indiceJCourant < joueursEnch.size()) {
                totalTmp += joueursEnch.get(indiceJCourant).getEnchereConstructeur();
                indiceJCourant++;
            }
            if (totalTmp > total) {
                total = totalTmp;
            }
        }
        return total;
    }

    /**
     * Permet de réinitialiser les objets stockant les infos sur le enchères
     */
    public void resetEnchereVars() {
        int indiceJoueur;
        ArrayList<Joueur> joueursEnch = new ArrayList<>();
        for (Map.Entry<PositionSegment, ArrayList<Joueur>> entry : enchereConstr.entrySet()) {
            indiceJoueur = 0;
            joueursEnch = entry.getValue();
            while (indiceJoueur < joueursEnch.size()) {
                joueursEnch.get(indiceJoueur).setEnchereConstructeur(0);
                joueursEnch.get(indiceJoueur).setEnchereCarte(0);
                indiceJoueur++;
            }
        }
        enchereConstr.clear();
        tabEnchere.clear();
    }

    @Override
    public String toString() {
        return "Santiago : \n" + listJoueurs.toString() + ",\nnbCanaux=" + nbCanaux + ",\nniveau=" + niveau + ",\navecPalmier=" + avecPalmier + ",\n" + plateau.toString() + ",\nnbTours=" + nbTours
                + "]\n";
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

    public boolean isPhaseFinie() {
        return phaseFinie;
    }

    public void setPhaseFinie(boolean phaseFinie) {
        this.phaseFinie = phaseFinie;
    }

    public boolean hasTrouveConstructeur() {
        return trouveConstructeur;
    }

    public void setTrouveConstructeur(boolean trouveConstructeur) {
        this.trouveConstructeur = trouveConstructeur;
    }

    public Joueur getDeviendraConstructeur() {
        return deviendraConstructeur;
    }

    public Joueur getChoisiCarte() {
        return choisiCarte;
    }

    public PositionCase getPositionCaseCourante() {
        return positionCaseCourante;
    }

    public PositionSegment getPositionSegmentCourant() {
        return positionSegmentCourant;
    }

    public void setPositionCaseCourante(PositionCase pc) {
        this.positionCaseCourante = pc;
    }

    public void setPositionSegmentCourant(PositionSegment ps) {
        this.positionSegmentCourant = ps;
    }
}
