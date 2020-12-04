package pacman;
import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.HashMap;

public class Main extends Application {
    Game game;
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Pacman");
        Menu menu = new Menu(primaryStage, game);
        menu.initMenu();
        primaryStage.show();
    }

}
