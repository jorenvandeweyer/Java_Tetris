package tetris.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

import tetris.screen.*;
import tetris.Launch;

public class StartFXMLController implements ControllerHack{
    
    ScreenHandler handler;
    
    @FXML
    private Button buttonStart;
    
    @FXML
    private Button levelUp;
    
    @FXML
    private Button levelDown;
    
    @FXML
    private Label level;
    
    @FXML
    private AnchorPane pane;
    
    private int levelCount = 1;
    
    @FXML
    public void initialize() {
        buttonStart.setOnAction(event -> startGame());
        levelUp.setOnAction(event -> level(true));
        levelDown.setOnAction(event -> level(false));
        level.setText(levelCount+"");

        BackgroundImage myBI= new BackgroundImage(new Image("/backgroundTetris.png"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, 
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(myBI)); 
    }
    
    /**
     * Make level harder
     * @param up increase level
     */
    private void level(boolean up){
        if(up){
            if(levelCount >=9) return;
            levelCount++;
        }else{
            if(levelCount <=1) return;
            levelCount--;
        }
        level.setText(levelCount+"");
    }

    /**
     * Load game screen
     */
    private void startGame(){
        Launch.level = levelCount;
        handler.load(Launch.screenGame, Launch.screenGameFXML);
        handler.show(Launch.screenGame);
    }
    
    /**
     * Sets the screens visible
     * @param handler handler
     */
    public void setVisible(ScreenHandler handler){
        this.handler = handler;
    }  

}
