package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Observable;

import model.Carte.TypeChamp;

public class Plateau extends Observable {

    private PositionIntersection source;
    private ArrayList<PositionSegment> canaux;
    private ArrayList<PositionCase> cases;
    private ArrayList<Carte> cartes;
    private ArrayList<Carte> cartesPosees;
    private ArrayList<Carte> cartesDevoilees;
    private ArrayList<PositionCase> palmiers;
    private final int nbJoueurs;
    private final NiveauPartie niveau;
    private final int niveauSource;

    public Plateau(NiveauPartie niveau, int nbJ) {
        this.niveau = niveau;
        niveauSource = niveau.getNiveauSource();
        canaux = new ArrayList<>(31);
        cases = new ArrayList<>(48);
        palmiers = new ArrayList<>(3);
        cartesPosees = new ArrayList<>();
        cartesDevoilees = new ArrayList<>();
        nbJoueurs = nbJ;
        cartes = new ArrayList<>(45);
        initPlateau();
    }

    private void initPlateau() {
        // Avec palmiers
        if (niveau == NiveauPartie.MOYEN || niveau == NiveauPartie.DIFFICILE) {
            for (int i = 0; i < palmiers.size(); i++) {
                palmiers.add(PositionCase.aleatoire());
            }
        }

        // Positionnement de la source
        source = PositionIntersection.aleatoire(niveau.getNiveauSource());

        // la liste des cartes
        for (TypeChamp type : TypeChamp.values()) {
            for (int i = 0; i < 9; i++) {
                if (i < 6) {
                    cartes.add(new Carte(2, type));
                } else {
                    cartes.add(new Carte(1, type));
                }
            }
        }
        // En fonction du nombres de joueurs, nombre de cartes
        if (nbJoueurs == 3 || nbJoueurs == 4) {
            cartes.remove(cartes.size() - 1);
        }
        // On mélange le packet de carte
        Collections.shuffle(cartes);

        // la liste des cases
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 6; j++) {
                cases.add(new PositionCase(i, j, false));
            }
        }
    }

    public void placerPalmier() {
        for (int i = 0; i < 3; i++) {
            palmiers.add(PositionCase.aleatoire());
        }
    }

    public ArrayList<Carte> tirerCarte() {
        // TEST tirerCarte Chris
        ArrayList<Carte> carteTirage = null;
        if (this.nbJoueurs == 5) {
            carteTirage = this.popArrayList(5);
        } else {
            carteTirage = this.popArrayList(4);
        }
        return carteTirage;
    }

    public boolean poserUneCarte(Carte carteAPoser, int x, int y) {
        // TEST poser une carte Chris
        boolean pose = false;

        Position positionChoisie = new PositionCase(x, y, false);
        if (cases.contains(positionChoisie)) {
            int indexPosition = cases.indexOf(positionChoisie);
            positionChoisie = cases.get(indexPosition);
            if (positionChoisie.isOccupe()) {
                System.out.println("Cette Case est déjà occupée...");
            } else {
                cartesPosees.add(carteAPoser);
                PositionCase positionCase = cases.get(indexPosition);
                positionCase.setOccupe(true);
                carteAPoser.setPosition(positionCase);
                pose = true;
            }
        } else {
            // TODO une exception
            System.out.println("Cette Position n'existe pas sur le plateau");
            // pour sortir de la boucle
            pose = false;
        }
        return pose;
    }

    public void placerCanal(int x, int y, int x1, int y1) {
        PositionSegment canal = new PositionSegment(x, y, x1, y1, true);
        canaux.add(canal);
    }

    public void placerCanal(PositionSegment canal) {
        canaux.add(canal);
    }

    public PositionIntersection getSource() {
        return source;
    }

    public ArrayList<Carte> getCartesDevoilees() {
        return cartesDevoilees;
    }

    public void setCartesDevoilees(ArrayList<Carte> cartesDevoilees) {
        this.cartesDevoilees = cartesDevoilees;
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

    public int getNiveauSource() {
        return niveauSource;
    }

    public void popCarteDevoilees(Carte carteAPoser) {
        int indexCarteChoisie = getCartesDevoilees().indexOf(carteAPoser);
        getCartesDevoilees().remove(indexCarteChoisie);
    }

    public ArrayList<PositionCase> getPalmiers() {
        return palmiers;
    }

    public void setPalmiers(ArrayList<PositionCase> palmiers) {
        this.palmiers = palmiers;
    }

    public void secheresse() {
        // TEST secheresse partie 2 Chris
        // décrémenter le nombre de marqueurs des cartes non irriguées
        // mettre en sécheresse les cartes sans marqueurs
        for (Iterator<Carte> iterator = this.cartesPosees.iterator(); iterator.hasNext();) {
            Carte carte = iterator.next();
            PositionCase pc = carte.getPositionCase();
            if (!pc.isIrriguee()) {
                if (!carte.isEstDeserte()) {
                    if (carte.getNbMarqueurActuel() > 0) {
                        carte.setNbMarqueurActuel(carte.getNbMarqueurActuel() - 1);
                    } else {
                        carte.setEstDeserte(true);
                    }
                }
            }
        }
    }

    public void majIrrigationTotale() {
        // TEST majIrrigationTotale Chris
        // XXX ajouté cette maj a chaque fois que l'on pose un canal
        for (Iterator<Carte> iterator = this.cartesPosees.iterator(); iterator.hasNext();) {
            // on récupère la carte posé de l'itération
            Carte carte = iterator.next();
            this.majIrrigation1Carte(carte);
        }
    }

    public void majIrrigation1Carte(Carte carte) {
        // TEST irrigation pour une carte Chris
        // appele ceci quand on pose une carte

        // On initialise une position, renseigné par la carte
        Position pc = carte.getPosition();
        // Et seux segments
        PositionSegment ps1 = new PositionSegment();
        PositionSegment ps2 = new PositionSegment();

        // On va chercher les deux segments qui sont adjacents à la carte
        // posé qu'on récupère à chaque itération
        // les segments ps1 et ps2 sont modifiés dans la méthodes, on les
        // récupère avec des valeurs valides
        PositionSegment.determineAdjacentAPosition(pc, ps1, ps2);

        // On change l'attribut irrguée des cases (PositionCase) à true si
        // on les segments adjacent
        // que l'on a calculé sont occupé (déjà posée)
        PositionCase cas = carte.getPositionCase();
        int index, index2;
        if (this.canaux.contains(ps1) || this.canaux.contains(ps2)) {
            index = this.canaux.indexOf(ps1);
            index2 = this.canaux.indexOf(ps2);
            if (this.canaux.get(index).isOccupe() || this.canaux.get(index2).isOccupe()) {
                cas.setIrriguee(true);
            } else {
                cas.setIrriguee(false);
            }
        }
    }

    private ArrayList<Carte> popArrayList(int nbCartes) {
        // TEST popArrayList Chris
        ArrayList<Carte> carteTirage = new ArrayList<>(nbCartes);
        for (int i = 0; i < nbCartes; i++) {
            carteTirage.add(this.cartes.get(this.cartes.size() - 1));
            this.cartes.remove(this.cartes.size() - 1);
        }
        return carteTirage;
    }

    @Override
    public String toString() {
        return "Plateau : \nSource : " + source + ",\ncanaux=" + canaux + ",\ncases=" + cases + ",\ncartes=\n" + cartes
                + ",\ncartesPosees=" + cartesPosees + ",\npalmiers=" + palmiers + ",\nnbJoueurs=" + nbJoueurs + ",\ncartesDevoilees="
                + cartesDevoilees + "\n]\n";
    }
}
