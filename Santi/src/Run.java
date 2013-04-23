import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import model.Carte;
import model.Joueur;
import model.NiveauPartie;
import model.Plateau;
import model.PositionSegment;
import model.Santiago;
import model.Santiago.TypeEnchere;
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
        String message = "\nDifférents niveaux vous sont proposés (entrez le numéro) :\n" + "\t1 - Pas de palmiers - FACILE\n"
                + "\t2 - Avec palmiers - MOYEN\n" + "\t3 - Avec palmiers, source sur un bord - DIFFICILE\n";
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
        TypeEnchere typeEnchere = TypeEnchere.CARTE;
        int enchereJoueur = 0, positionConstructeurDepart = -1, indiceJoueurCourant = 0;
        boolean constructeurTrouve = false;
        HashMap<Joueur, Integer> tabEnchere = santiago.getTabEnchere();
        tabEnchere.clear();
        // notre liste des joueurs
        ArrayList<Joueur> listJoueurs = santiago.getListJoueurs();

        // On dévoile les cartes tirées
        System.out.println("Tirage des cartes :");
        santiago.devoilerCarte();
        System.out.println("Voici les cartes tirées :");
        System.out.println(santiago.getPlateau().getCartesDevoilees().toString());

        System.out.println("Phase d'enchère");

        // Trouver constructeur
        positionConstructeurDepart = santiago.positionConstructeur();
        indiceJoueurCourant = santiago.positionApresConstructeur();

        while (indiceJoueurCourant != positionConstructeurDepart) {
            while (indiceJoueurCourant < listJoueurs.size() && indiceJoueurCourant != positionConstructeurDepart) {
                enchereJoueur = encherir(indiceJoueurCourant);
                Joueur joueur = santiago.getListJoueurs().get(indiceJoueurCourant);

                switch (typeEnchere) {
                    case CARTE:
                        joueur.setEnchereCarte(enchereJoueur);
                        break;
                    case CONSTRUCTEUR:
                        joueur.setEnchereConstructeur(enchereJoueur);
                        break;
                    default:
                        joueur.setEnchereCarte(enchereJoueur);
                        joueur.setEnchereConstructeur(enchereJoueur);
                }

                // on regarde si on trouve le nouveau constructeur
                if (enchereJoueur == 0 && !constructeurTrouve) {
                    santiago.setConstructeur(indiceJoueurCourant, true);
                    constructeurTrouve = true;
                }
                tabEnchere.put(listJoueurs.get(indiceJoueurCourant), enchereJoueur);
                listJoueurs.get(indiceJoueurCourant).setEnchereCarte(enchereJoueur);
                indiceJoueurCourant++;
            }
            // On revient à 0 dans la liste si on arrive au bout
            if (indiceJoueurCourant == listJoueurs.size()) {
                indiceJoueurCourant = 0;
            }
        }
        // lorsque l'on revient sur constructeur, il faut demander son enchère,
        // donc refaire 1 fois le traitement
        enchereJoueur = encherir(indiceJoueurCourant);
        if (enchereJoueur == 0 && !constructeurTrouve) {
            santiago.setConstructeur(indiceJoueurCourant, true);
            constructeurTrouve = true;
        } else {
            santiago.setConstructeur(indiceJoueurCourant, false);
        }
        tabEnchere.put(listJoueurs.get(indiceJoueurCourant), enchereJoueur);
        listJoueurs.get(indiceJoueurCourant).setEnchereCarte(enchereJoueur);

        // Chercher constructeur si personne n'a passé (=joueur avec plus petite
        // enchère)
        if (!constructeurTrouve) {
            Joueur JoueurEnchereMin = santiago.enchereMin();
            JoueurEnchereMin.setConstructeur(true);
        }
    }

    private int encherir(int indexJoueur) {
        int enchere = 0;
        int solde = santiago.getListJoueurs().get(indexJoueur).getSolde();
        Joueur joueur = santiago.getListJoueurs().get(indexJoueur);
        HashMap<Joueur, Integer> tabEnchere = santiago.getTabEnchere();
        do {
            if (enchere > solde) {
                System.out.println("Pas assez d'argent pour encherir de la sorte (max " + solde + ")");
            } else if (tabEnchere.containsValue(enchere) && enchere != 0) {
                System.out.println("Quelqu'un a déjà enchèri par " + enchere + "€");
            }
            enchere = Saisie.IN.nextIntBlank("Mauvais Format", joueur.toString() + "\n"
                    + "Veuillez indiquer le montant de votre enchère : (rien ou 0 pour passer son tour)");
            if (enchere == -1) {
                enchere = 0;
            }
        } while ((enchere > solde || tabEnchere.containsValue(enchere)) && enchere != 0);

        return enchere;
    }

    public void placementDesPlantations() {
        /*
         * Placement des cartes Anthony mettre à jour possesseur de la carte
         * pose des cartes dans l'ordre décroissant des enchères mettre à jour
         * les marqueurs mettre à jour solde des joueurs
         */
        // on récupère les objets de santiago et on les modifies
        Plateau plateau = santiago.getPlateau();
        int indexCarteChoisie = 0;
        Carte carteAPoser = null;
        Joueur joueur = santiago.enchereMax();
        // On retient Le joueur qui a le plus encheri
        santiago.setJoueurGagnant(joueur);
        ArrayList<Carte> carteDevoilees = plateau.getCartesDevoilees();
        HashMap<Joueur, Integer> tabEnchere = santiago.getTabEnchere();
        while (!carteDevoilees.isEmpty()) {
            // Dans une partie à 3, le meilleur enchérisseur pose 1 carte de
            // plus
            if (tabEnchere.isEmpty()) {
                joueur = santiago.getJoueurGagnant();
            } else {
                joueur = santiago.enchereMax();
            }
            System.out.println(joueur.toString());
            System.out.println("Joueur " + joueur.getNom() + " avec une enchère de : " + joueur.getEnchereCarte());
            if (!tabEnchere.isEmpty()) {
                System.out.println("Choisissez une carte : ");
                for (int i = 0; i < carteDevoilees.size(); i++) {
                    System.out.println(carteDevoilees.get(i).toString() + " " + (i + 1));
                }
                indexCarteChoisie = Saisie.IN.nextIntWithRangeNotBlank(1, carteDevoilees.size(), "", "",
                        "Entrez le numéro de la carte voulu de cette liste");

                // Car indice + 1 dane le menu
                indexCarteChoisie--;
            } else {
                // quand on est au dernier joueur partie à 3
                indexCarteChoisie = 0;
            }
            carteAPoser = carteDevoilees.get(indexCarteChoisie);
            System.out.println("Carte à poser : " + carteAPoser.toString());

            // Choisir position carte et la poser
            choisirPositionCarte(carteAPoser, joueur);
        }
        for (Joueur j : santiago.getListJoueurs()) {
            System.out.println(j.toString());
            System.out.println("Carte possédé");
            for (Carte carte : plateau.getCartesPosees()) {
                if (carte.getPossesseur().getNom().compareTo(j.getNom()) == 0) {
                    System.out.println(carte.toString());
                }
            }
        }
    }

    public void choisirPositionCarte(Carte carteAPoser, Joueur joueur) {
        // Demander à l'utilisateur de choisir une position pour poser la carte
        boolean pose = false;
        while (!pose) {
            System.out.println("Voici le plateau, choississez une position pour poser votre carte");
            AffichageConsole.afficheMatrice(8, 6);

            int x = Saisie.IN.nextIntWithRangeNotBlank(0, 7, "Ne pas laisser blanc", "Erreur", "Première coordonnée, les abscisse : ");

            int y = Saisie.IN.nextIntWithRangeNotBlank(0, 5, "Ne Pas laisser blanc", "Erreur", "Deuxième coordonnée, les abscisse : ");

            if (santiago.poserUneCarte(joueur, carteAPoser, x, y)) {
                pose = true;
            } else {
                System.out.println("Carte non posée..");
            }
        }
    }

    public void soudoyerConstructeur() {

        // on récupère les objets de santiago
        ArrayList<Joueur> listJoueurs = santiago.getListJoueurs();

        // Tour d'enchères :
        PositionSegment canal = null;
        String c = "", message = "";
        int montant = 0;
        int x, y, x1, y1;
        Joueur joueurCourant = null;

        // Tour joueurs, à démarrer à la gauche du constructeur !!
        int indiceJoueurCourant = santiago.positionApresConstructeur();

        // Boucle sur les joueurs :
        while (!listJoueurs.get(indiceJoueurCourant).isConstructeur()) {
            while (indiceJoueurCourant < listJoueurs.size() && !listJoueurs.get(indiceJoueurCourant).isConstructeur()) {
                joueurCourant = listJoueurs.get(indiceJoueurCourant);
                message = "\nJoueur : " + joueurCourant.getNom();
                message += "\nVoulez-vous soudoyer le constructeur de canal ? (o/n)";
                c = Saisie.IN.validStringNotBlank("o|O|n|N", "invalid", "erreur", message);
                if (c.compareToIgnoreCase("o") == 0) {
                    System.out.println("Indiquez où vous souhaitez poser le canal : ");

                    System.out.println("1 ère coordonnées: ");
                    x = Saisie.IN.nextIntWithRangeNotBlank(0, 7, "il faut ecrire quelque chose", "erreur", "x ?\n");
                    y = Saisie.IN.nextIntWithRangeNotBlank(0, 5, "il faut ecrire quelque chose", "erreur", "y ?\n");

                    System.out.println("2 ème coordonnées: ");
                    x1 = Saisie.IN.nextIntWithRangeNotBlank(0, 7, "il faut ecrire quelque chose", "erreur", "x ?\n");
                    y1 = Saisie.IN.nextIntWithRangeNotBlank(0, 5, "il faut ecrire quelque chose", "erreur", "y ?\n");

                    // transformer cette position en positionSegment
                    canal = new PositionSegment(x, y, x1, y1);

                    // Prix pour cette position
                    montant = Saisie.IN.nextIntWithRangeNotBlank(0, joueurCourant.getSolde(), "il faut ecrire quelque chose", "erreur",
                            "Votre Prix ?\n");

                    if (!santiago.encherePositionCanal(canal, joueurCourant, montant)) {
                        System.out.println("Enchère incorrecte, vous ne pouvez pas poser de canal.");
                    }

                } else {
                    System.out.println("Vous avez passé votre tour.");
                }
                indiceJoueurCourant++;
            }
            if (indiceJoueurCourant == listJoueurs.size()) {
                indiceJoueurCourant = 0;
            }
        }
    }

    public void choixDuConstructeur() {
        // Tour joueurs, à démarrer à la gauche du constructeur !!
        int positionContructeurDepart = santiago.positionConstructeur();
        int indiceJoueurCourant = santiago.positionApresConstructeur();

        if (!santiago.getEnchereContructeur().isEmpty()) {
            afficherListPositionCanaux();
            // vérifie le solde du constructeur avant de lui proposer les canaux
            // diponible
            if (santiago.constructeurPeutEncherir()) {
                String message = "Souhaitez-vous une de ces positions ? (o/n)";
                String c = Saisie.IN.validStringNotBlank("o|O|n|N", "invalid", "erreur", message);
                if (c.compareToIgnoreCase("o") == 0) {
                    choixPositionCanalAConstruire();
                } else {
                    int montant = 0, total = 0;
                    Joueur constructeur = santiago.getListJoueurs().get(positionContructeurDepart);
                    ArrayList<Joueur> listJoueurs = santiago.getListJoueurs();
                    HashMap<PositionSegment, ArrayList<Joueur>> enchereConstr = santiago.getEnchereContructeur();
                    ArrayList<Joueur> joueursEnch;
                    boolean bonMontant = false;

                    System.out.println("Indiquez votre propre enchère:");
                    while (!bonMontant) {
                        montant = Saisie.IN.nextIntWithRangeNotBlank(0, constructeur.getSolde(), "il faut ecrire quelque chose", "erreur",
                                "Votre prix ?");
                        // vérif
                        for (Map.Entry<PositionSegment, ArrayList<Joueur>> entry : enchereConstr.entrySet()) {
                            indiceJoueurCourant = 0;
                            joueursEnch = entry.getValue();
                            while (indiceJoueurCourant < joueursEnch.size()) {
                                total = total + joueursEnch.get(indiceJoueurCourant).getEnchereConstructeur();
                                indiceJoueurCourant++;
                            }
                            if (montant <= total || montant > listJoueurs.get(positionContructeurDepart).getSolde()) {
                                bonMontant = false;
                            } else {
                                choixPositionCanalAConstruire();
                                listJoueurs.get(positionContructeurDepart).enleverArgent(montant);
                                bonMontant = true;
                            }
                        }
                        if (!bonMontant) {
                            System.out.println("Mauvais montant, recommencez :");
                        } else {
                            choixPositionCanalAConstruire();
                            listJoueurs.get(positionContructeurDepart).enleverArgent(montant);
                            bonMontant = true;
                        }
                    }
                }
            } else {
                System.out.println("Vous êtes obliger de choisir un de ces canaux au vu de votre solde");
                choixPositionCanalAConstruire();
            }
        } else {
            System.out.println("Vous ne pouvez pas surencherir et aucune proposition n'est faire, Ciao !");
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
                System.out.println("\tJoueur : " + joueursEnch.get(indiceJoueurCourant).getNom() + "\tMontant : "
                        + joueursEnch.get(indiceJoueurCourant).getEnchereConstructeur());
                indiceJoueurCourant++;
            }
        }
    }

    public void choixPositionCanalAConstruire() {
        int x, y, x1, y1;

        // vérif, poser canal
        do {
            afficherListPositionCanaux();
            System.out.println("Entrez la position choisie : ");

            System.out.println("1 ère coordonnées: ");
            x = Saisie.IN.nextIntWithRangeNotBlank(0, 7, "il faut ecrire quelque chose", "erreur", "x ?\n");
            y = Saisie.IN.nextIntWithRangeNotBlank(0, 5, "il faut ecrire quelque chose", "erreur", "y ?\n");

            System.out.println("2 ème coordonnées: ");
            x1 = Saisie.IN.nextIntWithRangeNotBlank(0, 7, "il faut ecrire quelque chose", "erreur", "x ?\n");
            y1 = Saisie.IN.nextIntWithRangeNotBlank(0, 5, "il faut ecrire quelque chose", "erreur", "y ?\n");

        } while (!santiago.placerCanalChoisi(x, y, x1, y1));
    }

    public void irrigationSupplementaire() {
        /*
         * Irrigation supplémentaire Anthony parcours des joueurs appeler
         * placerCanalSup changer boolean tuyauSup prévoir PASSER SON TOUR
         */
        // on récupère les objets de santiago et on les modifies
        Plateau plateau = santiago.getPlateau();
        ArrayList<Joueur> listJoueurs = santiago.getListJoueurs();

        String choix = "", message = "";
        System.out.println("Phase d'irrigation supplémentaire");
        for (int i = 0; i < listJoueurs.size(); i++) {
            message = "Joueur " + listJoueurs.get(i).getNom();
            message += "\nVoulez vous placer un canal supplémentaire ? O/N";
            choix = Saisie.IN.validStringNotBlank("o|O|n|N", "invalid", "erreur", message);
            if (choix.compareToIgnoreCase("O") == 0) {
                if (listJoueurs.get(i).hasTuyauSup()) {
                    System.out.println("Vous n'avez plus de canal supplémentaire!");
                    break;
                }
                // TODO faire ça correct, tester les positions saisies par
                // l'utilisateur
                System.out.println("Indiquez où placer le canal.");
                int x = Saisie.IN.nextIntWithRangeNotBlank(0, 7, "il faut ecrire quelque chose", "erreur", "Position x1 : \n");
                System.out.println("Position y1 : ");
                int y = Saisie.IN.nextIntWithRangeNotBlank(0, 5, "il faut ecrire quelque chose", "erreur", "Position y1 : \n");
                System.out.println("Position x2 : ");
                int x1 = Saisie.IN.nextIntWithRangeNotBlank(0, 7, "il faut ecrire quelque chose", "erreur", "Position x2 : \n");
                System.out.println("Position y2 : ");
                int y1 = Saisie.IN.nextIntWithRangeNotBlank(0, 5, "il faut ecrire quelque chose", "erreur", "Position y2 : \n");
                plateau.placerCanal(x, y, x1, y1);
                listJoueurs.get(i).setTuyauSup(false);
            } else if (choix.compareToIgnoreCase("N") == 0) {
                System.out.println("Vous n'avez pas placé de canal pour ce tour.");
            }
        }
    }

    public void secheresse() {
        if (santiago.getNbTours() > 1) {
            santiago.getPlateau().secheresse();
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
