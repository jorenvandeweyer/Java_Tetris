package tetris.paint;

import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import tetris.block.*;

public class PaintNext extends Region{
    private Rectangle rectangle;
    private final ImagePattern[] blocks = new ImagePattern[]{
        new ImagePattern(new Image("/grayBlock.png")),
        new ImagePattern(new Image("/yellowBlock.png")),
        new ImagePattern(new Image("/purpleBlock.png")),
        new ImagePattern(new Image("/redBlock.png")),
        new ImagePattern(new Image("/blueBlock.png")),
        new ImagePattern(new Image("/lightBlueBlock.png")),
        new ImagePattern(new Image("/orangeBlock.png")),
        new ImagePattern(new Image("/greenBlock.png"))
    };    
    /**
     * Paints the sort block
     * @param nextBlock sort block
     */
    public PaintNext(PanelBlock nextBlock) {
        int[] x = nextBlock.getX();
        int[] y = nextBlock.getY();
        
        for (int i = 0; i<4; i++){
            for (int j = 0; j<3; j++){
                rectangle = new Rectangle(32*i, 32*j, 32,32);
                rectangle.setFill(blocks[0]);
                getChildren().addAll(rectangle);
            }
        }
        
        for (int i = 0; i<4; i++){
            rectangle = new Rectangle(32*x[i],32*y[i],32,32);
            rectangle.setFill(blocks[nextBlock.getBlock()+1]);
            getChildren().addAll(rectangle);
        }
    }    
}
