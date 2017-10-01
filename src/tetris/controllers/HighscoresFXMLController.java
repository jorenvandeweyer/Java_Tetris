package tetris.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

import tetris.screen.*;
import tetris.highscores.Highscores;
import tetris.Launch;

public class HighscoresFXMLController implements ControllerHack{

    ScreenHandler handler;
    private boolean state;

    @FXML
    private Button buttonRetry;

    @FXML
    private Label labelHighscores;

    @FXML
    private Label labelScore;
    
    @FXML
    private Label labelOwn;

    @FXML
    private TextField nameField;

    @FXML
    private Button buttonUpload;
    
    @FXML
    private AnchorPane pane;

    /**
     * Makes it possible to upload a score
     */
    public HighscoresFXMLController() {
        this.state = true;
    }
    
    @FXML
    public void initialize() {
        
        
        buttonRetry.setOnAction(event -> change());
        buttonUpload.setOnAction(event -> upload());
        //labelHighscores.setText(Highscores.getHighscores()); 
        labelScore.setText(Launch.score+"!");

        BackgroundImage myBI= new BackgroundImage(new Image("/backgroundTetris.png"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, 
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(myBI)); 
    }

    /**
     * Load game screen again
     */
    private void change(){
        handler.remove(Launch.screenGame);
        handler.load(Launch.screenGame, Launch.screenGameFXML);
        handler.show(Launch.screenGame);
    }
    
    /**
     * Upload highscore
     */
    private void upload(){
        if(state){
            Highscores.setHighscores(nameField.getText(), Launch.score);
            state=false;
            //labelHighscores.setText(Highscores.getHighscores());
            labelOwn.setText(Highscores.getRanking(nameField.getText(), Launch.score));
        }
    }

    /**
     * Sets the screens visible
     * @param handler handler
     */    
    @Override
    public void setVisible(ScreenHandler handler){
        this.handler = handler;
    }    

}
