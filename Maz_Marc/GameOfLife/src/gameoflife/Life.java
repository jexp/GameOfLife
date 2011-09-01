package gameoflife;

public class Life {
	private int x;
	private int y;

	public Life(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean equals(Object o) {
		if (o instanceof Life) {
			Life lo = (Life) o;
			return x == lo.getX() && y == lo.getY();
		}
		return false;
	}

	@Override
	public int hashCode() {
		return y ^ x;
	}

	@Override
	public String toString() {
		return String.format("(%s,%s)", x, y);
	}

}
