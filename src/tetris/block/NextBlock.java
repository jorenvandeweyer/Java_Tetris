package tetris.block;

public class NextBlock extends PanelBlock{

    /**
     * Constructor for next random block
     */
    public NextBlock() {
        setBlock((int)Math.floor(Math.random()*7));
        setX(templates[getBlock()][0]);
        setY(templates[getBlock()][1]);
    }    
       
}
