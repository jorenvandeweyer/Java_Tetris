package tetris.block;

public class SavedBlock extends PanelBlock{

    /**
     * Constructor for next random block
     * @param block Sort block
     */
    public SavedBlock(int block) {
        setBlock(block);
        setX(templates[getBlock()][0]);
        setY(templates[getBlock()][1]);
    }
    
    
}
