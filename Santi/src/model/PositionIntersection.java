package model;

public class PositionIntersection extends Position{
	
	public PositionIntersection(int x, int y) {
		super(x,y);
	}

	public static PositionIntersection aleatoire(int niveau){
		// TEST source alé Anthony
		// l'aléatoire de la source (niveau 0 = normal, 1 = experimente) 
		PositionIntersection pi;
		if (niveau == 0){
			//FIXME en normal on la met où ?
			pi = new PositionIntersection(1,1);
		}
		else {
			int x, y;
			double random = Math.random()*8;
			random = Math.floor(random);
			Double d = new Double(random);
			x = d.intValue();
			random = Math.random()*6;
			random = Math.floor(random);
			d = new Double(random);
			y = d.intValue();
			pi = new PositionIntersection(x, y);
		}
		return pi;
	}

}
