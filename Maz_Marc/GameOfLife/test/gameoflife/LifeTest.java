package gameoflife;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LifeTest {

	@Test
	public void testGetX() {
		Life life = new Life(12345, 0);

		assertEquals(12345, life.getX());
	}

	@Test
	public void testGetY() {
		Life life = new Life(0, 54321);

		assertEquals(54321, life.getY());
	}

	@Test
	public void testEqualsPositive() {
		Life lifeA = new Life(123, 456);
		Life lifeB = new Life(123, 456);
		assertTrue(lifeA.equals(lifeB));
	}

	@Test
	public void testEqualsNegative1() {
		Life lifeA = new Life(123, 456);
		Life lifeB = null;

		assertFalse(lifeA.equals(lifeB));
	}

	@Test
	public void testEqualsNegative2() {
		Life lifeA = new Life(123, 456);
		Object other = Integer.valueOf(1234567);

		assertFalse(lifeA.equals(other));
	}

	@Test
	public void testEqualsNegative3() {
		Life lifeA = new Life(123, 456);
		Life lifeB = new Life(555, 456);
		assertFalse(lifeA.equals(lifeB));
	}

	@Test
	public void testEqualsNegative4() {
		Life lifeA = new Life(123, 456);
		Life lifeB = new Life(123, 777);
		assertFalse(lifeA.equals(lifeB));
	}

	@Test
	public void testHashCodeEquals() {
		Life lifeA = new Life(123, 456);
		Life lifeB = new Life(123, 456);
		assertEquals(lifeA.hashCode(), lifeB.hashCode());
	}

	@Test
	public void testHashCodeNotEquals1() {
		Life lifeA = new Life(123, 456);
		Life lifeB = new Life(111, 456);
		assertFalse(lifeA.hashCode() == lifeB.hashCode());
	}

	@Test
	public void testHashCodeNotEquals2() {
		Life lifeA = new Life(123, 456);
		Life lifeB = new Life(123, 333);
		assertFalse(lifeA.hashCode() == lifeB.hashCode());
	}

	@Test
	public void testHashCodeNotEquals3() {
		Life lifeA = new Life(0, 456);
		Life lifeB = new Life(0, 222);
		assertFalse(lifeA.hashCode() == lifeB.hashCode());
	}

	@Test
	public void testtoString() {
		Life lifeA = new Life(0, 456);
		assertEquals("(0,456)", lifeA.toString());
	}
}
