package tetris;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import tetris.screen.ScreenHandler;

public class Launch extends Application {

    public static String screenStart = "start";
    public static String screenStartFXML = "StartFXML.fxml";
    public static String screenGame = "game";
    public static String screenGameFXML = "GameFXML.fxml";
    public static String screenHighscores = "highscores";
    public static String screenHighscoresFXML = "HighscoresFXML.fxml";
    
    private static ScreenHandler handler;
    public static int score;
    public static int level;
    
    @Override
    public void start(Stage stage) {
        
        handler = new ScreenHandler();
        handler.load(Launch.screenStart, Launch.screenStartFXML);
        //handler.load(Launch.screenGame, Launch.screenGameFXML);
        //handler.load(Launch.screenHighscores, Launch.screenHighscoresFXML);
        
        handler.show(Launch.screenStart);
        
        Group root = new Group();
        
        root.getChildren().addAll(handler);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}