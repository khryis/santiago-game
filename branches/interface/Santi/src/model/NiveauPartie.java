package model;

public enum NiveauPartie {
    FACILE("Sans Palmier", false, 0), MOYEN("Avec Palmier", true, 0), DIFFICILE("Avec Palmier, Source sur les côté", true, 1);

    private String description;
    private boolean palmier;
    private int niveauSource;

    private NiveauPartie(String descr, boolean palm, int src) {
        description = descr;
        palmier = palm;
        niveauSource = src;
    }

    public String getDescription() {
        return description;
    }

    public boolean withPalmier() {
        return palmier;
    }

    public int getNiveauSource() {
        return niveauSource;
    }

}
