package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

public class Santiago extends Observable {

    private ArrayList<Joueur> listJoueurs;
    private int nbCanaux;
    private int niveau;
    private boolean avecPalmier;
    private Plateau plateau;
    private int nbTours; // décrémenter à chaque fin de tour

    public enum NiveauPartie {
        FACILE("Sans Palmier"), MOYEN("Avec Palmier"), DIFFICILE("Avec Palmier, Source sur les côté");

        private String description;

        private NiveauPartie(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public Santiago() {
        super();
        this.listJoueurs = new ArrayList<Joueur>();
        this.nbCanaux = 0;
        this.niveau = 0;
        this.avecPalmier = false;
        this.plateau = null;
        this.nbTours = 0;
    }

    public int getNbCanaux() {
        return nbCanaux;
    }

    public int getNiveau() {
        return niveau;
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

    public void setNiveau(int niveau) {
        this.niveau = niveau;
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

    public ArrayList<Joueur> getListJoueurs() {
        return listJoueurs;
    }

    public void setConstructeur(int indexJoueurs, boolean estConstructeur) {
        listJoueurs.get(indexJoueurs).setEstConstructeur(estConstructeur);
    }

    public void setListJoueurs(ArrayList<Joueur> listJoueurs) {
        this.listJoueurs = listJoueurs;
    }

    public boolean isFinish() {
        if (nbTours == 0) {
            return true;
        }
        return false;
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
    }

    public void setNiveauPartie(Santiago.NiveauPartie niveauPartie) {
        switch (niveauPartie) {
        case FACILE:
            avecPalmier = false;
            niveau = 0;
            break;
        case MOYEN:
            avecPalmier = true;
            niveau = 0;
            break;
        case DIFFICILE:
            avecPalmier = true;
            niveau = 1;
            break;
        default:
            avecPalmier = false;
            niveau = 0;
            break;
        }
    }

    public void devoilerCarte() {
        plateau.setCartesDevoilees(plateau.tirerCarte());
    }

    public void determinerUnConstructeur() {
        // Constructeur aléatoire pour premier tour
        int rdm = (int) Math.random() * listJoueurs.size();
        listJoueurs.get(rdm).setEstConstructeur(true);
    }

    public void secheresse() {
        // TEST secheresse partie 1 Chris
        // check si on est arrivé au dernier tour
        if (this.nbTours > 1) {
            plateau.secheresse();
        }
    }

    public void diaDePaga() {
        for (Iterator<Joueur> iterator = this.listJoueurs.iterator(); iterator.hasNext();) {
            Joueur j = iterator.next();
            j.setSolde(j.getSolde() + 3);
        }
    }

    public int positionConstructeur() {
        int positionConstructeur = -1;
        int i = 0;
        // Trouver la position du constructeur
        while (i < listJoueurs.size() && positionConstructeur == -1) {
            if (listJoueurs.get(i).isEstConstructeur()) {
                positionConstructeur = i;
            }
            i++;
        }
        return positionConstructeur;
    }

    @Override
    public String toString() {
        return "Santiago [listJoueurs=" + listJoueurs.toString() + ", nbCanaux=" + nbCanaux + ", niveau=" + niveau + ", avecPalmier="
                + avecPalmier + ", plateau=" + plateau.toString() + ", nbTours=" + nbTours + "]";
    }
}
