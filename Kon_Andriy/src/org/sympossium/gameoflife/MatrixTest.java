package org.sympossium.gameoflife;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;


public class MatrixTest {
    @Test
    public void countNoNeighboursReturns0() {
        Matrix matrix = new Matrix();
        matrix.setCellState(3,3,true);
        int result = matrix.countNeighbours(3, 3);
        assertEquals(0, result);
    }
    
    @Test
    public void countAPairOfCellsVertical() {
        Matrix matrix = createMatrixWith4AliveCells();
        int result = matrix.countNeighbours(3, 3);
        assertEquals(2, result);
    }

    private Matrix createMatrixWith4AliveCells() {
        Matrix matrix = new Matrix();
        //.
        // .
        //  .
        //  .
        matrix.setCellState(1,1,true);
        matrix.setCellState(2,2,true);
        matrix.setCellState(3,3,true);
        matrix.setCellState(3,4,true);
        return matrix;
    }
    //TODO: boundary

    @Test
    public void isWithinMatrixNegativeXReturnsOutOfMatrix() {
        Matrix matrix = new Matrix();
        assertFalse(matrix.isWithinMatrix(-1, 0));
    }


    @Test
    public void isWithinMatrixOutofBoundsXReturnsOutOfMatrix() {
        Matrix matrix = new Matrix();
        assertFalse(matrix.isWithinMatrix(Matrix.WIDTH,0));
    }

    @Test
    public void isWithinMatrixOutofBoundsYReturnsOutOfMatrix() {
        Matrix matrix = new Matrix();
        assertFalse(matrix.isWithinMatrix(0,Matrix.HEIGHT));
        assertFalse(matrix.isWithinMatrix(0,-1));
    }



    @Test
    public void findSurroundingCellsShouldReturn8ForEachInnerCell() {
        Matrix matrix = createMatrixWith4AliveCells();
        assertEquals(8,matrix.findSurroundingCells(3,3).size());

    }


}
