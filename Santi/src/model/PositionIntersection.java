package model;

import java.util.ArrayList;

public class PositionIntersection extends Position {
    private final int niveauSource;

    public PositionIntersection(int x, int y, int n) {
        super(x, y);
        this.niveauSource = n;
    }

    public static PositionIntersection aleatoire(int niveauSource) {
        // l'aléatoire de la source (niveau 0 = normal, 1 = experimente)
        PositionIntersection pi = new PositionIntersection(-1, -1, -1);
        int n = 2;
        ArrayList<PositionIntersection> tabPos = new ArrayList<>();
        tabPos.add(new PositionIntersection(0, 0, 1));
        tabPos.add(new PositionIntersection(0, 2, 1));
        tabPos.add(new PositionIntersection(0, 4, 1));
        tabPos.add(new PositionIntersection(0, 6, 1));
        tabPos.add(new PositionIntersection(2, 0, 1));
        tabPos.add(new PositionIntersection(2, 6, 1));
        tabPos.add(new PositionIntersection(4, 0, 1));
        tabPos.add(new PositionIntersection(4, 6, 1));
        tabPos.add(new PositionIntersection(4, 8, 1));
        tabPos.add(new PositionIntersection(6, 0, 1));
        tabPos.add(new PositionIntersection(6, 6, 1));
        tabPos.add(new PositionIntersection(8, 0, 1));
        tabPos.add(new PositionIntersection(8, 2, 1));
        tabPos.add(new PositionIntersection(8, 4, 1));
        tabPos.add(new PositionIntersection(8, 6, 1));
        tabPos.add(new PositionIntersection(2, 2, 0));
        tabPos.add(new PositionIntersection(2, 4, 0));
        tabPos.add(new PositionIntersection(4, 2, 0));
        tabPos.add(new PositionIntersection(4, 4, 0));
        tabPos.add(new PositionIntersection(6, 2, 0));
        tabPos.add(new PositionIntersection(6, 4, 0));
        if (niveauSource == 0) {
            while (n != 0) {
                double rand = Math.random() * tabPos.size();
                rand = Math.floor(rand);
                Double d = new Double(rand);
                int rdm = d.intValue();
                n = tabPos.get(rdm).niveauSource;
                pi = tabPos.get(rdm);
            }
        } else {
            while (n != 1) {
                double rand = Math.random() * tabPos.size();
                rand = Math.floor(rand);
                Double d = new Double(rand);
                int rdm = d.intValue();
                n = tabPos.get(rdm).niveauSource;
                pi = tabPos.get(rdm);
            }
        }
        return pi;
    }

    @Override
    public String toString() {
        String res = super.toString();
        res += "[niveauSource=" + niveauSource + "]";
        return res;
    }
}
