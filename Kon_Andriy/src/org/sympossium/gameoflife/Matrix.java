package org.sympossium.gameoflife;

import com.sun.xml.internal.bind.v2.runtime.reflect.Accessor;
import org.sympossium.gameoflife.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: kon
 * Date: 31/08/11
 * Time: 11:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class Matrix {
    public static final int WIDTH = 6;
    public static final int HEIGHT = 6;
    Cell[][] cells = new Cell[WIDTH][HEIGHT];

    public Matrix() {
        for(int i=0; i<WIDTH; i++){
            for(int j=0; j<HEIGHT; j++) {
                cells[i][j]=new Cell();
            }
        }
    }

    public boolean getCellState(int x, int y) {
        return cells[x][y].isAlive();
    }
    public void setCellState(int x, int y, boolean state) {
        cells[x][y].setAlive(state);
    }

    public int countNeighbours(int x, int y) {
//        return 0;
        // find surrounding cells
        List<Cell> surrounding = findSurroundingCells(x, y);

        return countAlive(surrounding);
    }

    private int countAlive(List<Cell> surrounding) {
        int result = 0;
        for(Cell cell : surrounding) {
            if (cell.isAlive())
                result++;
        }
        return result;
    }

    List<Cell> findSurroundingCells(int x, int y) {
        List<Cell> result = new ArrayList<Cell>();
        for (int i=x-1; i<x+1; i++) {
            for(int j=y-1; j<y+1; j++) {
                if (isWithinMatrix(i, j) ) {//&& !(x == i && y == j)) {
                    result.add(cells[i][j]);
                }
            }
        }
        return result;
    }

    boolean isWithinMatrix(int x, int y) {
        if (x <0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return false;
        }
        return true;

    }
}
