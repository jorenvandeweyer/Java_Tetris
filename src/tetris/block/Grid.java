package tetris.block;

import java.util.ArrayList;
import java.util.Arrays;

public class Grid {
    private int[][] matrix;

    /**
     * Constructor for empty grid
     */
    public Grid(){
        this.matrix = new int[18][10];
    }
    
    /**
     * Set specific value in grid
     * 
     * @param row row
     * @param column column
     * @param value value
     */
    public void matrix(int row, int column, int value){
        this.matrix[row][column] = value;
    }    

    /**
     * getter for matrix
     * 
     * @return grid
     */
    public int[][] getMatrix() {
        return matrix;
    }

    /**
     * Setter for complete grid
     * 
     * @param matrix grid
     */
    public void setMatrix(int[][] matrix) {      
        for(int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, 10);
        }
    }
    
    
    /**
     * Checks in matrix if the grids has a complete row and deletes if possible
     * 
     * @return points gained
     */
    public int checkMatrix(){
        int points = 0;
        int factor = 1;
        for(int i = 0; i < 18; i++) {
            boolean delete = true;
            for(int j = 0; j < 10; j++) {
                if(this.matrix[i][j] == 0){
                    delete = false;
                    break;
                }
            }
            if (delete){
                ArrayList<int[]> list = new ArrayList<>(Arrays.asList(matrix));
                list.remove(i);
                list.add(0, new int[10]);
                matrix = list.toArray(matrix);
                points += 100*factor;
                factor++;
            }
        }
        return points;
    }

    
}
