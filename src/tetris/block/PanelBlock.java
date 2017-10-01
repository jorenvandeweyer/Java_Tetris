package tetris.block;

public abstract class PanelBlock {
    private int[] x;

    private int[] y;
    
    private int block;
    
    public int[][][] templates = new int[][][] {
        {
            {0,1,2,3},
            {0,0,0,0}
        },
        {
            {0,1,2,1},
            {0,0,0,1}
        },
        {
            {1,2,1,2},
            {0,0,1,1}
        },
        {
            {0,1,1,2},
            {0,0,1,1}
        },
        {
            {1,2,0,1},
            {0,0,1,1}
        },
        {
            {0,1,2,0},
            {0,0,0,1}
        },
        {
            {0,1,2,2},
            {0,0,0,1}
        }
    };  
    
    /**
     * Gettet for sort block
     * @return sort block
     */
    public int getBlock() {
        return block;
    }
    
    /**
     * Getter for x-values of the block
     * @return x-values block
     */
    public int[] getX() {
        return x;
    }

    /**
     * Getter for y-values of the block
     * @return y-values block
     */
    public int[] getY() {
        return y;
    }     

    /**
     * Setter for x-values of the block
     * @param x x-values
     */
    public void setX(int[] x) {
        this.x = x;
    }

    /**
     * Setter for y-values of the block
     * @param y y-values
     */
    public void setY(int[] y) {
        this.y = y;
    }

    /**
     * Setter for the sort block
     * @param block sort block
     */
    public void setBlock(int block) {
        this.block = block;
    }

}
