package gameoflife;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import org.junit.Test;

public class BoardTest {

	@Test
	public void testConstructor() {
		Board board = new Board();
		assertEquals(Collections.emptySet(), board.getLife());
	}

	@Test
	public void testConstructor2() {
		Board board = new Board("*");
		assertEquals(new HashSet<Object>(Arrays.asList(new Life(0, 0))),
				board.getLife());
	}
}
