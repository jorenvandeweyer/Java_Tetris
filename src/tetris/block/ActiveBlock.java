package tetris.block;

public class ActiveBlock {
    private final int[] x;
    private final int[] y;
    
    private final int block;
    private int spiegel;
        
    private final int[][][] templates = new int[][][] {
        {
            {3,4,5,6,4},
            {0,0,0,0,0}
        },
        {
            {3,4,5,4,4},
            {0,0,0,1,1}
        },
        {
            {4,5,4,5,4},
            {0,0,1,1,0}
        },
        {
            {3,4,4,5,4},
            {0,0,1,1,0}
        },
        {
            {4,5,3,4,4},
            {0,0,1,1,0}
        },
        {
            {3,4,5,3,4},
            {0,0,0,1,0}
        },
        {
            {3,4,5,5,4},
            {0,0,0,1,0}
        }
    };
    
    /**
     * Constructor for block in playfield
     * @param nextBlock sort block
     */
    public ActiveBlock(int nextBlock) {
        this.block = nextBlock;
        spiegel = 1;
        x = templates[this.block][0];
        y = templates[this.block][1];
    }
    
    /**
     * Moves block to the left if possible
     * @param grid playfield
     * 
     */
    public void moveLeft(Grid grid){
        for (int i = 0;i<4;i++){
            if(x[i]==0 || grid.getMatrix()[y[i]][x[i]-1] != 0){
                return;
            }            
        }
        for (int i = 0;i<5;i++){
            x[i]--; 
        }            
    }
    
    /**
     * Moves block to the right if possible
     * @param grid playfield
     */
    public void moveRight(Grid grid){
        for (int i = 0;i<4;i++){
            if(x[i]==9 || grid.getMatrix()[y[i]][x[i]+1] != 0){
                return;
            }
        }
        for (int i = 0;i<5;i++){
            x[i]++; 
        }            
    }
    
    /**
     * Moves block down
     */
    public void moveDown(){
        for (int i = 0;i<5;i++){
            y[i]++; 
        }             
    }
    
    /**
     * Rotates the block if possible
     * @param grid playfield
     */
    public void rotate(Grid grid){   
        if( block == 2 ) {
            return;
        }
        
        int[] tempy = new int[4];
        int[] tempx = new int[4];
        
        for (int i = 0; i<4; i++){          
            tempx[i] = spiegel * (-y[i] + y[4]) + x[4];
            tempy[i] = spiegel * (x[i] - x[4]) + y[4];
        }

        if(block == 0 || block == 3 || block == 4) {
            spiegel = -spiegel;
        }
        
        for (int i = 0; i<4; i++){
            if(tempx[i]>9 || tempx[i]<0 || tempy[i]>17 || tempy[i] < 0){
                return;                    
            } else {
                if(grid.getMatrix()[tempy[i]][tempx[i]] != 0){
                    return;                
                }
            }
        }
        
        for(int i = 0; i<4; i++){
            x[i] = tempx[i];
            y[i] = tempy[i];
        }
    }
    
    /**
     * Getter for the x-values of the block
     * @return x-values block
     */
    public int[] getX() {
        return x;
    }

    /**
     * Getter for the y-values of the block
     * @return y-values block
     */
    public int[] getY() {
        return y;
    }

    /**
     * Getter for sort of block
     * @return sort block
     */
    public int getBlock() {
        return block;
    }

    /**
     * Move block to down bottom
     * @param grid playfield
     */
    public void fall(Grid grid) {
        while(true){
            
            for (int i = 0;i<4;i++){
                if(y[i]==17 || grid.getMatrix()[y[i]+1][x[i]] != 0){
                    return;
                }
            }

            for (int i = 0;i<5;i++){
                y[i]++; 
            }            
            
        }
        
    }
    
}
