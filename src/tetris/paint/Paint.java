package tetris.paint;

import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import tetris.block.Grid;

public class Paint extends Region{
    

    private Rectangle rectangle;
    private final int[][] matrix;
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
     * Paints the playfield
     * @param grid playfield
     */
    public Paint(Grid grid){
        this.matrix = grid.getMatrix();
        
        for (int row = 0; row<18;row++){
            for (int column = 0; column<10;column++){
                rectangle = new Rectangle(32*column,32*row,32,32);
                rectangle.setFill(blocks[matrix[row][column]]);
                getChildren().addAll(rectangle);
            }
        }
    }        
}
