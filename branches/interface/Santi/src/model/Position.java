package model;

public class Position {
	protected int x;
	protected int y;
	protected boolean occupe;
	
	public Position(){
		this.x = 0;
		this.y = 0;
		this.occupe = false;
	}
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
		this.occupe = false;
	}
	
	/**
	 * @param x
	 * @param y
	 * @param occupe
	 */
	public Position(int x, int y, boolean occupe) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (occupe ? 1231 : 1237);
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	
}
