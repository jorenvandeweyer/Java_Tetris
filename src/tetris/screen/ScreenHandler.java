package tetris.screen;

import java.io.IOException;
import java.util.HashMap;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class ScreenHandler extends StackPane{
    
    //Makes it easier to refer to a screens fxml (1)
    private final HashMap<String, Node> screens = new HashMap<>();

    /**
     * Constructor with super()
     */
    public ScreenHandler(){
        //override methods StackPane (2)
        super();
    }
    
    /**
     * Load a screen 
     * @param name screen name
     * @param fxml screen fxml file
     */
    public void load(String name, String fxml){
        try {
            //regular FXMLLoader & load()
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();
            
            //handy shape to put itself (handler) in the new class
            ControllerHack hack = (ControllerHack) loader.getController();
            hack.setVisible(this);
            
            screens.put(name, root);
        } catch (IOException ex) {
            System.out.println("loading '" + name + "' failed... :(\r\n");
        }
    }
    
    /**
     * Unload a screen
     * @param name screen name
     */
    public void remove(String name){
        boolean status = (screens.remove(name)==null);
        if(status) System.out.println("Screen '" + name + "' not found... :(\r\n");
    }
    
    /**
     * Set a screen to active
     * @param name screen name
     * added fade effect see source (3)
     */
    public void show(String name){
        if (screens.get(name) != null) {
            DoubleProperty opacity = opacityProperty();

            if (!getChildren().isEmpty()) {
                Timeline fade = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                        new KeyFrame(new Duration(1000), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        getChildren().remove(0);                    
                        getChildren().add(0, screens.get(name));
                        Timeline fadeIn = new Timeline(
                                new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                                new KeyFrame(new Duration(800), new KeyValue(opacity, 1.0)));
                        fadeIn.play();
                    }
                }, new KeyValue(opacity, 0.0)));
                fade.play();

            } else {
                setOpacity(0.0);
                getChildren().add(screens.get(name));
                Timeline fadeIn = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                        new KeyFrame(new Duration(2500), new KeyValue(opacity, 1.0)));
                fadeIn.play();
            }
        } else {
            System.out.println("screen hasn't been loaded!!! \n");
        }        

    }
  
}
