/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.block;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joren
 */
public class GridTest {
    
    public GridTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testMatrix(){
        Grid grid = new Grid();
        grid.matrix(3, 5, 4);
        assertEquals(grid.getMatrix()[3][5], 4);
    }

    @Test
    public void testGetNewMatrix() {
        Grid instance = new Grid();
        int[][] expResult = new int[18][10];
        int[][] result = instance.getMatrix();
        assertArrayEquals(expResult, result);
    }
    
    @Test
    public void testSetGetMatrix() {
        int[][] matrix = new int [18][10];
        Grid instance = new Grid();
        instance.setMatrix(matrix);
        assertArrayEquals(instance.getMatrix(), matrix);
    }

    @Test
    public void testCheckEmptyMatrix() {
        Grid instance = new Grid();
        int expResult = 0;
        int result = instance.checkMatrix();
        assertEquals(expResult, result);
    }

    @Test
    public void testCheck2RowMatrix() {
        Grid instance = new Grid();
        for(int i=0;i<10;i++){
            instance.matrix(3, i, 1);
            instance.matrix(5, i, 3);
        }
        int expResult = 300;
        int result = instance.checkMatrix();
        assertEquals(expResult, result);
    }
    
}
