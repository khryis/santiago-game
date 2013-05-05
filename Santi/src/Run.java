import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import model.Carte;
import model.Joueur;
import model.NiveauPartie;
import model.PositionSegment;
import model.Santiago;
import singleton.Saisie;
import vue.useless.AffichageConsole;

public class Run {
    private final Santiago santiago;

    public Run() {
        santiago = Santiago.getSantiago();
    }

    public void configurer() {
        // choix du niveau demandé à l'utilisateur
        niveauPartie();
        // init liste joueurs avec les utilisateurs
        initJoueur();
        // init plateau (avec positionnement source) qui avait besoin du niveau
        // et des joueurs
        santiago.initPartie();
    }

    public void niveauPartie() {
        int choix = 0;
        // Type enum de Santiago
        NiveauPartie niveauPartie = null;
        String message = "\nDifférents niveaux vous sont proposés (entrez le numéro) :\n" + "\t1 - Pas de palmiers - FACILE\n" + "\t2 - Avec palmiers - MOYEN\n"
                + "\t3 - Avec palmiers, source sur un bord - DIFFICILE\n";
        // Changement de la saisie car pour les test, le mock d'un scanner
        // marche pas
        choix = Saisie.IN.nextIntWithRangeNotBlank(1, 3, "Entrez quelque chose..", "Entré non valide", message);

        switch (choix) {
        case 1:
            niveauPartie = NiveauPartie.FACILE;
            break;
        case 2:
            niveauPartie = NiveauPartie.MOYEN;
            break;
        case 3:
            niveauPartie = NiveauPartie.DIFFICILE;
            break;
        default:
            niveauPartie = NiveauPartie.FACILE;
            break;
        }
        santiago.setNiveauPartie(niveauPartie);
    }

    public void initJoueur() {
        // init couleur et nom pour chaque joueur et remplir la liste
        // verif nombre joueur
        System.out.println("Veuillez entrez le nombre de joueur (entre 3 et 5)");
        int nbJ = Saisie.IN.nextIntWithRange(3, 5);

        // Joueurs crée
        ArrayList<Joueur> listJoueurs = new ArrayList<>(nbJ);

        // Liste des couleurs
        ArrayList<String> couleurs = new ArrayList<>();
        Collections.addAll(couleurs, "blanc", "noir", "violet", "gris", "beige");

        int i = 0;
        while (i < nbJ) {
            Joueur j = null;
            String nom = "";
            String couleur = "";

            nom = Saisie.IN.nextLineNotBlank("Indiquer un nom svp..", "Nom invalide", "Entrer un nom de joueur");

            while (couleur.compareTo("") == 0) {
                String message = "Indiquez votre couleur parmi : \n";
                String regExp = "";
                for (int k = 0; k < couleurs.size(); k++) {
                    message += "\t" + couleurs.get(k) + "\n";
                    regExp += couleurs.get(k);
                    if (k < couleurs.size()) {
                        regExp += "|";
                    }
                }
                couleur = Saisie.IN.validStringNotBlank(regExp, "Couleur Invalide", "Exception saisie", message);

                int index = couleurs.indexOf(couleur);
                couleurs.remove(index);
                j = new Joueur(nom, couleur);
            }
            listJoueurs.add(j);
            i++;
        }
        santiago.setListJoueurs(listJoueurs);
    }

    public void miseAuxEncheres() {
        // initialisation pour les enchères
        santiago.setPhaseFinie(false);
        santiago.getTabEnchere().clear();

        // On dévoile les cartes tirées
        System.out.println("Tirage des cartes :");
        santiago.devoilerCarte();
        System.out.println("Voici les cartes tirées :");
        System.out.println(santiago.getPlateau().getCartesDevoilees().toString());
        System.out.println("Phase d'enchère");

        // Trouver constructeur
        int indiceJoueurCourant = santiago.positionApresConstructeur();
        santiago.setIndiceJoueurCourant(indiceJoueurCourant);

        do {
            int enchereJoueur = 0;
            Joueur joueur = santiago.joueurPlaying();
            do {
                enchereJoueur = Saisie.IN.nextIntBlank("Mauvais Format", joueur.toString() + "\n" + "Veuillez indiquer le montant de votre enchère : (rien ou 0 pour passer son tour)");
            } while (!santiago.encherir(enchereJoueur));
        } while (!santiago.isPhaseFinie());
    }

    public void placementDesPlantations() {
        // on récupère les objets de santiago
        int indexCarteChoisie = 0;
        do {
            indexCarteChoisie = choisirUnCarte();
            choisirPositionCarte(indexCarteChoisie);
        } while (!santiago.isPhaseFinie());

        // affichage des possession de chaque joueurs
        for (Joueur j : santiago.getListJoueurs()) {
            System.out.println(j.toString());
            System.out.println("Carte possédé");
            for (Carte carte : santiago.getPlateau().getCartesPosees()) {
                if (carte.getPossesseur().getNom().compareTo(j.getNom()) == 0) {
                    System.out.println(carte.toString());
                }
            }
        }
    }

    public int choisirUnCarte() {
        int indexCarteChoisie = -1;
        ArrayList<Carte> carteDevoilees = santiago.getPlateau().getCartesDevoilees();
        Joueur joueur = santiago.getChoisiCarte();
        System.out.println(joueur.toString());
        System.out.println("Joueur " + joueur.getNom() + " avec une enchère de : " + joueur.getEnchereCarte());
        if (!santiago.getTabEnchere().isEmpty()) {
            System.out.println("Choisissez une carte : ");
            for (int i = 0; i < carteDevoilees.size(); i++) {
                System.out.println(carteDevoilees.get(i).toString() + " " + (i + 1));
            }
            indexCarteChoisie = Saisie.IN.nextIntWithRangeNotBlank(1, carteDevoilees.size(), "", "", "Entrez le numéro de la carte voulu de cette liste");

            // Car indice + 1 dans le menu
            indexCarteChoisie--;
        } else {
            // quand on est au dernier joueur partie à 3
            indexCarteChoisie = 0;
        }
        return indexCarteChoisie;
    }

    public void choisirPositionCarte(int indexCarteChoisie) {
        // Demander à l'utilisateur de choisir une position pour poser la carte
        boolean pose = false;
        while (!pose) {
            System.out.println("Voici le plateau, choississez une position pour poser votre carte");
            AffichageConsole.afficheMatrice(8, 6);

            int x = Saisie.IN.nextIntWithRangeNotBlank(0, 7, "Ne pas laisser blanc", "Erreur", "Première coordonnée, les abscisse : ");

            int y = Saisie.IN.nextIntWithRangeNotBlank(0, 5, "Ne Pas laisser blanc", "Erreur", "Deuxième coordonnée, les abscisse : ");

            if (santiago.poserUneCarte(indexCarteChoisie, x, y)) {
                pose = true;
            } else {
                System.out.println("Carte non posée..");
            }
        }
    }

    public void soudoyerConstructeur() {

        // on récupère les objets de santiago
        santiago.setPhaseFinie(false);

        // Tour d'enchères :
        String saisie = "";
        int x, y, x1, y1;

        // Tour joueurs, à démarrer à la gauche du constructeur !!
        santiago.setIndiceJoueurCourant(santiago.positionApresConstructeur());
        Joueur joueurCourant;

        // Boucle sur les joueurs :
        do {
            joueurCourant = santiago.joueurPlaying();
            boolean termine = false;
            do {
                String message = "\nJoueur : " + joueurCourant.getNom();
                message += "\nVoulez-vous soudoyer le constructeur de canal ? (o/n)";
                saisie = Saisie.IN.validStringNotBlank("o|O|n|N", "invalid", "erreur", message);
                if (saisie.compareToIgnoreCase("o") == 0) {
                    System.out.println("Indiquez où vous souhaitez poser le canal : ");

                    System.out.println("1 ère coordonnées: ");
                    x = Saisie.IN.nextIntWithRangeNotBlank(0, 7, "il faut ecrire quelque chose", "erreur", "x ?\n");
                    y = Saisie.IN.nextIntWithRangeNotBlank(0, 5, "il faut ecrire quelque chose", "erreur", "y ?\n");

                    System.out.println("2 ème coordonnées: ");
                    x1 = Saisie.IN.nextIntWithRangeNotBlank(0, 7, "il faut ecrire quelque chose", "erreur", "x ?\n");
                    y1 = Saisie.IN.nextIntWithRangeNotBlank(0, 5, "il faut ecrire quelque chose", "erreur", "y ?\n");

                    // transformer cette position en positionSegment
                    PositionSegment canal = new PositionSegment(x, y, x1, y1);

                    // Prix pour cette position
                    int montant = Saisie.IN.nextIntWithRangeNotBlank(0, joueurCourant.getSolde(), "il faut ecrire quelque chose", "erreur", "Votre Prix ?\n");

                    if (!santiago.encherePositionCanal(canal, montant)) {
                        System.out.println("Enchère incorrecte, vous ne pouvez pas proposer de construction de canal.");
                        termine = false;
                    } else {
                        termine = true;
                    }
                } else {
                    System.out.println("Vous avez passé votre tour.");
                    termine = true;
                }
            } while (!termine);
        } while (!santiago.isPhaseFinie());
    }

    public void choixDuConstructeur() {
        // Tour joueurs, à démarrer à la gauche du constructeur !!
        PositionSegment positionSegment = null;
        boolean choisiProposition = false;

        // S'il y a des proposition, on les affiche
        if (!santiago.getEnchereContructeur().isEmpty()) {
            afficherListPositionCanaux();
            // le constructeur a assez de solde pour encherir
            if (santiago.constructeurPeutEncherir()) {
                // il choisir ou non une des proposition
                String message = "Souhaitez-vous une de ces positions ? (o/n)";
                String c = Saisie.IN.validStringNotBlank("o|O|n|N", "invalid", "erreur", message);
                if (c.compareToIgnoreCase("o") == 0) {
                    // choisi une des proposition
                    choisiProposition = true;
                } else {
                    // veut proposer sa propre position
                    choisiProposition = false;
                }
            } else {
                // Pas assez de solde pour encherir sur le proposition
                System.out.println("Vous êtes obliger de choisir un de ces canaux au vu de votre solde");
                choisiProposition = true;
            }
        }

        if (choisiProposition) {
            do {
                System.out.println("Vous allez choisir une de proposition faite");
                positionSegment = choixPositionCanalAConstruire();
            } while (!santiago.placerCanalChoisi(positionSegment));
        } else {
            if (santiago.constructeurPeutEncherir()) {
                do {
                    System.out.println("Vous allez choisir un position de canal a construire");
                    positionSegment = choixPositionCanalAConstruire();
                } while (!santiago.placerCanalDeSonChoix(positionSegment));
            } else {
                System.out.println("Vous ne pouvez pas surencherir et aucune proposition n'est faite, Ciao !");
            }
        }
    }

    public void afficherListPositionCanaux() {
        int indiceJoueurCourant;
        HashMap<PositionSegment, ArrayList<Joueur>> enchereConstr = santiago.getEnchereContructeur();

        System.out.println("Voici les propositions de construction des canaux : ");
        ArrayList<Joueur> joueursEnch = new ArrayList<>();
        // Indiquez quel joueur a proposé quoi ?
        // itérer sur la map, montrer les propositions
        for (Map.Entry<PositionSegment, ArrayList<Joueur>> entry : enchereConstr.entrySet()) {
            indiceJoueurCourant = 0;
            System.out.println("Position canal : " + entry.getKey());
            joueursEnch = entry.getValue();
            while (indiceJoueurCourant < joueursEnch.size()) {
                System.out.println("\tJoueur : " + joueursEnch.get(indiceJoueurCourant).getNom() + "\tMontant : " + joueursEnch.get(indiceJoueurCourant).getEnchereConstructeur());
                indiceJoueurCourant++;
            }
        }
    }

    public PositionSegment choixPositionCanalAConstruire() {
        int x, y, x1, y1;

        // vérif, poser canal
        afficherListPositionCanaux();
        System.out.println("Entrez la position choisie : ");

        System.out.println("1 ère coordonnées: ");
        x = Saisie.IN.nextIntWithRangeNotBlank(0, 7, "il faut ecrire quelque chose", "erreur", "x ?\n");
        y = Saisie.IN.nextIntWithRangeNotBlank(0, 5, "il faut ecrire quelque chose", "erreur", "y ?\n");

        System.out.println("2 ème coordonnées: ");
        x1 = Saisie.IN.nextIntWithRangeNotBlank(0, 7, "il faut ecrire quelque chose", "erreur", "x ?\n");
        y1 = Saisie.IN.nextIntWithRangeNotBlank(0, 5, "il faut ecrire quelque chose", "erreur", "y ?\n");

        return (new PositionSegment(x, y, x1, y1));
    }

    public void irrigationSupplementaire() {
        /*
         * Irrigation supplémentaire Anthony parcours des joueurs appeler
         * placerCanalSup changer boolean tuyauSup prévoir PASSER SON TOUR
         */
        // on récupère les objets de santiago et on les modifies
        ArrayList<Joueur> listJoueurs = santiago.getListJoueurs();

        String choix = "", message = "";
        System.out.println("Phase d'irrigation supplémentaire");
        for (int i = 0; i < listJoueurs.size(); i++) {
            boolean reussi = false;
            do {
                message = "Joueur " + listJoueurs.get(i).getNom();
                message += "\nVoulez vous placer un canal supplémentaire ? O/N";
                choix = Saisie.IN.validStringNotBlank("o|O|n|N", "invalid", "erreur", message);
                if (choix.compareToIgnoreCase("O") == 0) {
                    System.out.println("Indiquez où placer le canal.");
                    int x = Saisie.IN.nextIntWithRangeNotBlank(0, 7, "il faut ecrire quelque chose", "erreur", "Position x1 : \n");
                    System.out.println("Position y1 : ");
                    int y = Saisie.IN.nextIntWithRangeNotBlank(0, 5, "il faut ecrire quelque chose", "erreur", "Position y1 : \n");
                    System.out.println("Position x2 : ");
                    int x1 = Saisie.IN.nextIntWithRangeNotBlank(0, 7, "il faut ecrire quelque chose", "erreur", "Position x2 : \n");
                    System.out.println("Position y2 : ");
                    int y1 = Saisie.IN.nextIntWithRangeNotBlank(0, 5, "il faut ecrire quelque chose", "erreur", "Position y2 : \n");
                    if (!santiago.irrigationSupplementaire(new PositionSegment(x, y, x1, y1))) {
                        System.out.println("Mauvaise position de canal propose, recommecez !");
                        reussi = false;
                    } else {
                        reussi = true;
                    }
                } else if (choix.compareToIgnoreCase("N") == 0) {
                    System.out.println("Vous n'avez pas placé de canal pour ce tour.");
                    santiago.incrementerJoueurCourantCanalSup(false);
                    reussi = true;
                }
            } while (!reussi);
        }
    }

    public void secheresse() {
        if (santiago.getNbTours() > 1) {
            santiago.secheresse();
        }
    }

    public void diaDePaga() {
        santiago.diaDePaga();
    }

    public boolean isFinish() {
        return santiago.isFinish();
    }

    // Execution du programme
    public static void main(String args[]) {
        Run r = new Run();
        // r.configurer();

        // Pour tester à la place de configurer
        r.santiago.setNiveauPartie(NiveauPartie.FACILE);
        r.santiago.getListJoueurs().add(new Joueur("Jean", "gris"));
        r.santiago.getListJoueurs().add(new Joueur("Tullandre", "noir"));
        r.santiago.getListJoueurs().add(new Joueur("Kill", "blanc"));
        r.santiago.initPartie();
        // ------------

        // System.out.println(r.santiago.toString());
        while (!r.isFinish()) {
            // Phase 1 : retourner plantation et mettre au enchère
            r.miseAuxEncheres();

            // Phase 2 : changement du constructeur de canal
            // Dans la phase 1

            // Phase 3 : choisir plantation et placer
            r.placementDesPlantations();

            // Phase 4 : Soudoyer constructeur de canal
            r.soudoyerConstructeur();
            r.choixDuConstructeur();

            // Phase 5 : Irrigation complémentaire
            r.irrigationSupplementaire();

            // Phase 6 : régler la sécheresse
            r.secheresse();

            // Phase 7 : Revenu d'aide au dévelloppement
            r.diaDePaga();
            System.out.println(r.santiago.getPlateau().getSource().toString());
        }
    }
}
