package model;

public class PositionSegment extends Position {
	private Position bout;
	
	public PositionSegment(int x, int y, boolean occupe) {
		super(x, y, occupe);
	}

	public Position getBout() {
		return bout;
	}

	public void setBout(Position bout) {
		this.bout = bout;
	}
}
