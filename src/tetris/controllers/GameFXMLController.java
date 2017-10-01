package tetris.controllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.util.Duration;

import tetris.screen.*;
import tetris.Launch;
import tetris.block.*;
import tetris.highscores.Highscores;
import tetris.paint.*;

public class GameFXMLController implements ControllerHack{

    ScreenHandler handler;
    
    
    @FXML
    private Button down;

    @FXML
    private Button right;

    @FXML
    private Button left;

    @FXML
    private Button rotate;

    @FXML
    private AnchorPane game;

    @FXML
    private AnchorPane next;
    
    @FXML
    private AnchorPane saved;
    
    @FXML
    private Label labelHighscores;    

    @FXML
    private Label labelPoints; 
    
    @FXML
    private AnchorPane pane;
    
    
    private Grid grid;
    private Grid gameBlock;
    private ActiveBlock block;
    private NextBlock nextBlock;
    private SavedBlock savedBlock;
    private Timeline timeline;
    
    private int score = 0;   
    private final int interval = 1100;
    
    private int[] x;
    private int[] y;
            
     
    @FXML
    void initialize(){
        down.setOnAction(event -> {block.moveDown();draw();});
        rotate.setOnAction(event -> {block.rotate(grid);draw();});
        left.setOnAction(event -> {block.moveLeft(grid);draw();});
        right.setOnAction(event -> {block.moveRight(grid);draw();}); 
        game.setFocusTraversable(true);
        down.setFocusTraversable(false);
        left.setFocusTraversable(false);
        right.setFocusTraversable(false);
        rotate.setFocusTraversable(false);
        game.setOnKeyPressed(this::keys);
        //getHighscores();
        startGame();
        
        BackgroundImage myBI= new BackgroundImage(new Image("/backgroundTetris.png"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, 
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(myBI)); 
        
    }
    /**
     * End of the game
     */
    private void end(){
        Launch.score = score;
        handler.remove(Launch.screenHighscores);
        handler.load(Launch.screenHighscores, Launch.screenHighscoresFXML);
        handler.show(Launch.screenHighscores);
    }
    
    /**
     * Sets the screens visible
     * @param handler handler
     */
    @Override
    public void setVisible(ScreenHandler handler){
        this.handler = handler;
    }    
    
    /**
     * Start the game and initiate variables
     */
    public void startGame() {
        grid = new Grid();
        gameBlock = new Grid();
        nextBlock = new NextBlock();
        block = new ActiveBlock(nextBlock.getBlock());
        nextBlock = new NextBlock();
        savedBlock = new SavedBlock(0);
        draw();
        start();
    }
    
    /**
     * Play with keys
     * @param e keyevent
     */
    public void keys(KeyEvent e){
        switch(e.getCode()){
            case LEFT:
                block.moveLeft(grid);
                draw();
                break;
            case RIGHT:
                block.moveRight(grid);
                draw();
                break;
            case UP:
                block.rotate(grid);
                draw();
                break;
            case DOWN:
                block.moveDown();
                draw();
                break;
            case SPACE:
                block.fall(grid);
                draw();
                block.moveDown();
                draw();
                break;
            case SHIFT:
                change();
                draw();
                break;
        }        
    }
    
    
    /**
     * Starter for timer
     */    
    public void start(){
        timeline = new Timeline(new KeyFrame(
                Duration.millis(interval - 100*Launch.level),
                ae -> tick()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     * Plays every tick
     */
    public void tick(){
        block.moveDown();
        draw();
    }
        
    /**
     * Draw the playfield and Check for hit
     */
    public void draw(){
        
        x = block.getX();
        y = block.getY();        
        
        for (int i = 0; i<4; i++){
            if(y[i] < 18){
                if(grid.getMatrix()[y[i]][x[i]] != 0){
                    grid.setMatrix(gameBlock.getMatrix());
                    score += grid.checkMatrix();
                    if(grid.getMatrix()[1][4] != 0){
                        timeline.stop();
                        end();
                        return;
                    }
                    score += 10;        
                    block = new ActiveBlock(nextBlock.getBlock());
                    nextBlock = new NextBlock();
                    break;
                }
            } else {
                grid.setMatrix(gameBlock.getMatrix());
                score += grid.checkMatrix();
                score += 10;        
                block = new ActiveBlock(nextBlock.getBlock());
                nextBlock = new NextBlock();
                break;
            }
            
        }


        x = block.getX();
        y = block.getY();
        
        gameBlock.setMatrix(grid.getMatrix());
        for (int i = 0; i<4; i++){
            gameBlock.matrix(y[i],x[i],block.getBlock()+1);
        }
        
        Paint paint = new Paint(gameBlock);
        PaintNext paintNext = new PaintNext(nextBlock);
        PaintNext paintSaved = new PaintNext(savedBlock);
        
        game.getChildren().clear();
        game.getChildren().add(paint);
        
        next.getChildren().clear();
        next.getChildren().add(paintNext);
        
        saved.getChildren().clear();
        saved.getChildren().add(paintSaved);
        
        labelPoints.setText(score + "");
        
    }
    
    /**
     * Get highscores from database
     */
    public void getHighscores(){
        labelHighscores.setText(Highscores.getHighscores());        
    }
     
    /**
     * Changes block between playfield and savedfield 
     */
    public void change(){
        int tempInt = block.getBlock();
        block = new ActiveBlock(savedBlock.getBlock());
        savedBlock = new SavedBlock(tempInt);
    }
}
