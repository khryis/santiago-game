import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import model.Joueur;
import model.Plateau;
import model.PositionSegment;
import model.Santiago;
import model.Santiago.NiveauPartie;
import singleton.Saisie;

public class Run {
    private final Santiago santiago;

    public Run() {
        santiago = new Santiago();
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
        ArrayList<Joueur> listJoueurs = new ArrayList<Joueur>(nbJ);

        // Liste des couleurs
        ArrayList<String> couleurs = new ArrayList<String>();
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
                    if (k < couleurs.size())
                        regExp += "|";
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

    public HashMap<Joueur, Integer> miseAuxEncheres() {
        /*
         * TEST Enchère carte + choix constructeur Anthony appeler une méthode
         * qui va chercher sur le plateau les 4 ou 5 prochaines cartes changer
         * statut du joueur ayant l'enchère la plus basse en constructeur
         */

        // initialisation pour les enchères
        int enchereJoueur = 0, positionConstructeurDepart = -1, i = 0;
        boolean constructeurTrouve = false;
        HashMap<Joueur, Integer> tabEnchere = new HashMap<Joueur, Integer>();
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
        i = positionConstructeurDepart + 1;

        while (i != positionConstructeurDepart) {
            while (i < listJoueurs.size() && i != positionConstructeurDepart) {
                enchereJoueur = encherir(i, tabEnchere);
                // on regarde si on trouve le nouveau constructeur
                if (enchereJoueur == 0 && constructeurTrouve == false) {
                    santiago.setConstructeur(i, true);
                    constructeurTrouve = true;
                }
                tabEnchere.put(listJoueurs.get(i), enchereJoueur);
                i++;
            }

            if (i == listJoueurs.size()) {
                i = 0;
            }
        }
        // lorsque l'on revient sur constructeur, il faut demander son enchère,
        // donc refaire 1 fois le traitement
        enchereJoueur = encherir(i, tabEnchere);
        if (enchereJoueur == 0 && constructeurTrouve == false) {
            santiago.setConstructeur(i, true);
            constructeurTrouve = true;
        } else {
            santiago.setConstructeur(i, false);
        }
        tabEnchere.put(listJoueurs.get(i), enchereJoueur);

        // Chercher constructeur si personne n'a passé (=joueur avec plus petite
        // enchère)
        if (constructeurTrouve == false) {
            Joueur JoueurEnchereMin = enchereMin(tabEnchere);
            JoueurEnchereMin.setEstConstructeur(true);
        }
        for (Iterator<Joueur> iterator = tabEnchere.keySet().iterator(); iterator.hasNext();) {
            Joueur joueur = iterator.next();
            Integer enchere = tabEnchere.get(joueur);
            joueur.majSolde(-enchere);
        }
        return tabEnchere;
    }

    private int encherir(int indexJoueur, HashMap<Joueur, Integer> tabEnchere) {
        int enchere = 0;
        int solde = santiago.getListJoueurs().get(indexJoueur).getSolde();
        do {
            if (enchere > solde) {
                System.out.println("Pas assez d'argent pour encherir de la sorte (max " + solde + ")");
            } else if (tabEnchere.containsValue(enchere) && enchere != 0) {
                System.out.println("Quelqu'un a déjà enchèri par " + enchere + "€");
            }
            enchere = Saisie.IN.nextIntBlank("Mauvais Format", santiago.getListJoueurs().get(indexJoueur).toString() + "\n"
                    + "Veuillez indiquer le montant de votre enchère : (rien ou 0 pour passer son tour)");
            if (enchere == -1)
                enchere = 0;
        } while ((enchere > solde || tabEnchere.containsValue(enchere)) && enchere != 0);
        return enchere;
    }

    public void placementDesPlantations(HashMap<Joueur, Integer> tabEnchere) {
        /*
         * TEST Placement des cartes Anthony mettre à jour possesseur de la
         * carte pose des cartes dans l'ordre décroissant des enchères mettre à
         * jour les marqueurs mettre à jour solde des joueurs
         */
        // on récupère les objets de santiago et on les modifies
        Plateau plateau = santiago.getPlateau();

        Scanner sc = new Scanner(System.in);
        int carteChoisie;
        while (!tabEnchere.isEmpty()) {
            Joueur JoueurGagnant = enchereMax(tabEnchere);
            System.out.println("Joueur " + JoueurGagnant.getNom() + " avec une enchère de : " + tabEnchere.get(JoueurGagnant).intValue());
            System.out.println("Liste des cartes : ");
            System.out.println(plateau.getCartesDevoilees().toString());
            System.out.println("Choisissez une carte : ");
            while (!plateau.getCartesDevoilees().isEmpty()) {
                for (int i = 0; i < plateau.getCartesDevoilees().size(); i++) {
                    System.out.println(plateau.getCartesDevoilees().get(i).toString() + " " + (i + 1));
                }
                carteChoisie = sc.nextInt();
                // Maj possesseur
                plateau.getCartesDevoilees().get(carteChoisie).setPossesseur(JoueurGagnant);
                // Maj marqueurs
                JoueurGagnant.setNbMarqueurDispos(JoueurGagnant.getNbMarqueurDispos()
                        - plateau.getCartesDevoilees().get(carteChoisie).getNbMarqueurMax());
                // Maj solde
                JoueurGagnant.setSolde(JoueurGagnant.getSolde() - tabEnchere.get(JoueurGagnant).intValue());
                // Pose de la carte
                plateau.poserUneCarte(plateau.getCartesDevoilees().get(carteChoisie));
                // MAJ marqueurs de la carte si enchère joueur = 0
                if (tabEnchere.get(JoueurGagnant).intValue() == 0) {
                    plateau.getCartesPosees().get(carteChoisie)
                            .setNbMarqueurActuel(plateau.getCartesPosees().get(carteChoisie).getNbMarqueurActuel() - 1);
                }
                plateau.majIrrigation1Carte(plateau.getCartesDevoilees().get(carteChoisie));
                plateau.getCartesDevoilees().remove(carteChoisie);
            }
            tabEnchere.remove(JoueurGagnant);
        }
    }

    public static Joueur enchereMax(HashMap<Joueur, Integer> tab) {
        Joueur res = null;
        int maxValue = (Collections.max(tab.values()));
        for (Entry<Joueur, Integer> entry : tab.entrySet()) {
            if (entry.getValue() == maxValue) {
                res = entry.getKey();
            }
        }
        return res;
    }

    public static Joueur enchereMin(HashMap<Joueur, Integer> tab) {
        Joueur res = null;
        int minValue = (Collections.min(tab.values()));
        for (Entry<Joueur, Integer> entry : tab.entrySet()) {
            if (entry.getValue() == minValue) {
                res = entry.getKey();
            }
        }
        return res;
    }

    public void soudoyerConstructeur() {
        // TEST tour soudoiement Flo

        // pose position canal temp
        // stocker la liste des propositions de placement de canal
        // associer une position à une liste de joueur
        // proposer montant (mettre à jour Joueur.enchereConstructeur)
        // prévoir PASSER SON TOUR

        // on récupère les objets de santiago et on les modifies
        Plateau plateau = santiago.getPlateau();
        ArrayList<Joueur> listJoueurs = santiago.getListJoueurs();

        // Tour d'enchères :
        Scanner sc = new Scanner(System.in);
        HashMap<PositionSegment, ArrayList<Joueur>> enchereConstr = new HashMap<PositionSegment, ArrayList<Joueur>>();
        PositionSegment canal = null;
        ArrayList<Joueur> joueursEnch = new ArrayList<Joueur>();
        String c = "";
        int montant = 0;
        int x;
        int y;
        int x1;
        int y1;
        Joueur j = null;

        // Sous quelle forme donne-t-on la position du canal temp ? Ac interface
        // graphique, cliquer sur l'endroit ? Dire: l2, case 3 ?
        // Tour joueurs, à démarrer à la gauche du constructeur !!
        int depart = santiago.positionConstructeur();
        int i = depart;

        // Boucle sur les joueurs :
        while (!listJoueurs.get(i).isEstConstructeur()) {
            while (i < listJoueurs.size() && !listJoueurs.get(i).isEstConstructeur()) {
                j = listJoueurs.get(i);
                System.out.println("Voulez-vous soudoyer le constructeur de canal ? (o/n) ");
                c = sc.next();
                if (c.compareTo("o") == 0) {
                    System.out.println("Indiquez où vous souhaitez poser le canal : ");
                    // récupérer la popsition, I don't know how, in which format
                    System.out.println("1 ère coordonnées: ");
                    System.out.println("x ?");
                    x = sc.nextInt();
                    System.out.println("y ?");
                    y = sc.nextInt();
                    System.out.println("2 ème coordonnées: ");
                    System.out.println("x ?");
                    x1 = sc.nextInt();
                    System.out.println("y ?");
                    y1 = sc.nextInt();
                    // transformer cette position en positionSegment
                    canal = new PositionSegment(x, y, x1, y1);

                    System.out.println("Combien proposez-vous pour votre canal ?");
                    montant = sc.nextInt();
                    if (montant > 0 && montant < j.getSolde()) {
                        // récupérer enchère
                        j.setEnchereConstructeur(montant);
                        if (enchereConstr.containsKey(canal)) {
                            // ajouter le joueur à l'arrayList si position de
                            // canal déjà proposée
                            enchereConstr.get(canal).add(j);
                        } else { // ajouter dans la hashmap
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
            constr = listJoueurs.size() - 1;
        } else {
            constr = depart - 1;
        }
        System.out.println("Voici les propositions de construction des canaux : ");
        // Indiquez quel joueur a proposé quoi ?
        // itérer sur la map, montrer les propositions
        for (Map.Entry<PositionSegment, ArrayList<Joueur>> entry : enchereConstr.entrySet()) {
            i = 0;
            System.out.println("Position canal : " + entry.getKey());
            joueursEnch = entry.getValue();
            while (i < joueursEnch.size()) {
                System.out.println("\tJoueur : " + joueursEnch.get(i).getNom() + "\tMontant : "
                        + joueursEnch.get(i).getEnchereConstructeur());
                i++;
            }
        }
        System.out.println("Souhaitez-vous une de ces positions ? (o/n)");
        c = sc.next();
        if (c.compareTo("o") == 0) {
            System.out.println("Entrez la position choisie :");
            System.out.println("1 ère coordonnées: ");
            System.out.println("x ?");
            x = sc.nextInt();
            System.out.println("y ?");
            y = sc.nextInt();
            System.out.println("2 ème coordonnées: ");
            System.out.println("x ?");
            x1 = sc.nextInt();
            System.out.println("y ?");
            y1 = sc.nextInt();
            // vérif, poser canal
            plateau.placerCanal(x, y, x1, y1);
            // mettre à jour le solde des joueurs
            canal = new PositionSegment(x, y, x1, y1);
            joueursEnch = enchereConstr.get(canal);
            for (Joueur joueur : joueursEnch) {
                joueur.setSolde(joueur.getSolde() - joueur.getEnchereConstructeur());
            }

        } else {
            System.out.println("Indiquez votre propre enchère:");
            montant = 0;
            int total = 0;
            int erreur = -1;
            while (erreur == -1) {
                montant = sc.nextInt();
                // vérif
                for (Map.Entry<PositionSegment, ArrayList<Joueur>> entry : enchereConstr.entrySet()) {
                    i = 0;
                    joueursEnch = entry.getValue();
                    while (i < joueursEnch.size()) {
                        total = total + joueursEnch.get(i).getEnchereConstructeur();
                        i++;
                    }
                    if (montant <= total || montant > listJoueurs.get(constr).getSolde()) {
                        erreur = 0;
                    } else {
                        erreur = 1;
                        listJoueurs.get(constr).setSolde(listJoueurs.get(constr).getSolde() - montant);
                        System.out.println("Indiquez la position du canal : ");
                        System.out.println("1 ère coordonnées: ");
                        System.out.println("x ?");
                        x = sc.nextInt();
                        System.out.println("y ? ");
                        y = sc.nextInt();
                        System.out.println("2 ème coordonnées: ");
                        System.out.println("x ?");
                        x1 = sc.nextInt();
                        System.out.println("y ?");
                        y1 = sc.nextInt();
                        plateau.placerCanal(x, y, x1, y1);
                    }
                }
                if (erreur == 0) {
                    System.out.println("Mauvais montant, recommencez :");
                    erreur = -1;
                }
            }
        }
    }

    public void irrigationSupplementaire() {
        /*
         * Irrigation supplémentaire Anthony parcours des joueurs appeler
         * placerCanalSup changer boolean tuyauSup prévoir PASSER SON TOUR
         */
        // on récupère les objets de santiago et on les modifies
        Plateau plateau = santiago.getPlateau();
        ArrayList<Joueur> listJoueurs = santiago.getListJoueurs();

        Scanner sc = new Scanner(System.in);
        String choix = "";
        System.out.println("Phase d'irrigation supplémentaire");
        for (int i = 0; i < listJoueurs.size(); i++) {
            System.out.println("Joueur " + listJoueurs.get(i).getNom());
            System.out.println("Voulez vous placer un canal supplémentaire ? O/N");
            choix = sc.nextLine();
            if (choix.compareTo("O") == 0 || choix.compareTo("o") == 0) {
                if (listJoueurs.get(i).isTuyauSup()) {
                    System.out.println("Vous n'avez plus de canal supplémentaire!");
                    break;
                }
                System.out.println("Indiquez où placer le canal.");
                System.out.println("Position x1 : ");
                int x = sc.nextInt();
                System.out.println("Position y1 : ");
                int y = sc.nextInt();
                System.out.println("Position x2 : ");
                int x1 = sc.nextInt();
                System.out.println("Position y2 : ");
                int y1 = sc.nextInt();
                plateau.placerCanal(x, y, x1, y1);
                listJoueurs.get(i).setTuyauSup(false);
            } else if (choix.compareTo("N") == 0 || choix.compareTo("n") == 0)
                System.out.println("Vous n'avez pas placé de canal pour ce tour.");
            // Prevoir cas de merde si ce n'est ni "O" ni "N"
            // Vraiment besoin de la méthode passer son tour ici ?
        }
    }

    public void secheresse() {
        // TEST secheresse partie 1 Chris
        // check si on est arrivé au dernier tour
        if (santiago.getNbTours() > 1) {
            santiago.getPlateau().secheresse();
        }
    }

    public void diaDePaga() {
        for (Iterator<Joueur> iterator = santiago.getListJoueurs().iterator(); iterator.hasNext();) {
            Joueur j = iterator.next();
            j.setSolde(j.getSolde() + 3);
        }
    }

    public boolean isFinish() {
        return santiago.isFinish();
    }

    // Execution du programme
    public static void main(String args[]) {
        Run r = new Run();
        r.configurer();
        System.out.println(r.santiago.toString());

        while (!r.isFinish()) {
            // Phase 1 : retourner plantation et mettre au enchère
            HashMap<Joueur, Integer> hm = r.miseAuxEncheres();
            System.out.println(hm.toString());

            // Phase 2 : changement du constructeur de canal

            // Phase 3 : choisir plantation et placer

            // Phase 4 : Soudoyer constructeur de canal

            // Phase 5 : Irrigation complémentaire

            // Phase 6 : régler la sécheresse

            // Phase 7 : Revenu d'aide au dévelloppement
        }
    }
}
