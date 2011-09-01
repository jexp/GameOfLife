package org.sympossium.gameoflife;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: kon
 * Date: 31/08/11
 * Time: 11:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class CellTest {
    @org.junit.Test
    public void testIsAlive() throws Exception {
        Cell cell = new Cell();
        cell.setAlive(true);
        assertTrue(cell.isAlive());
    }

    @org.junit.Test
    public void testDead() throws Exception {
        Cell cell = new Cell();
        cell.setAlive(true);
        assertFalse(cell.isAlive());
    }
}
