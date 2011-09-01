package gameoflife;

public class Main {

	public static void main(String[] args) {

		Board board = new Board(//
				"*....", //
				"*.*..", //
				"*.*..", //
				"*.*..");

		for (int count = 0; count < 10; count++) {
			System.out.printf("%s. Generation:\n\n%s\n", count, board);
			board = board.nextGeneration();

			if (board.getLife().isEmpty()) {
				System.out.println("All dead.");
				break;
			}
		}

	}
}
