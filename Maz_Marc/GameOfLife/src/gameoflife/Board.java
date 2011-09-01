package gameoflife;

import java.util.HashSet;
import java.util.Set;

/**
 * Immutable representation of a generation.
 * 
 * @author marc
 */
public class Board {

	private final Set<Life> life = new HashSet<>();

	private Board(int width, int height, Set<Life> generation) {

	}

	public Board(String... pattern) {
		int y = 0;
		for (String line : pattern) {
			int x = 0;
			for (char c : line.toCharArray()) {
				if (c == '*') {
					life.add(new Life(x, y));
				}
			}
		}

	}

	public Set<Life> getLife() {
		return life;
	}

	public Board nextGeneration() {
		return null;
	}

	/**
	 * <pre>
	 * . . . .
	 * . * . .
	 * . * . .
	 * . * . .
	 * </pre>
	 */
	public String toString() {
		return "TODO";
	}

}
