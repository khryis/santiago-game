package model;

public class Position {
	private int x;
	private int y;
	private boolean occupe;
	
	public Position(){
		this.x = 0;
		this.y = 0;
		this.occupe = false;
	}
	
	/**
	 * @param x
	 * @param y
	 * @param occupe
	 */
	public Position(int x, int y, boolean occupe) {
		super();
		this.x = x;
		this.y = y;
		this.occupe = occupe;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isOccupe() {
		return occupe;
	}

	public void setOccupe(boolean occupe) {
		this.occupe = occupe;
	}

	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + ", occupe=" + occupe + "]";
	}
	
	
}
