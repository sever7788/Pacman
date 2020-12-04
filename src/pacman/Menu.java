package pacman;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Menu {
    Game game;
    Stage stage;
    Scene scene;
    Replay replay;
    public Menu(Stage stage, Game game){
        this.game = game;
        this.stage = stage;
        this.replay = null;
    }
    public void initMenu(){
        StackPane root = new StackPane();
        Image imageFon = new Image(getClass().getResourceAsStream("./img/5.gif"));
        ImageView imgFon = new ImageView(imageFon);
        Image imageLogo = new Image(getClass().getResourceAsStream("./img/6.png"));
        ImageView imgLogo = new ImageView(imageLogo);

        imgFon.setFitHeight(600);
        imgFon.setFitWidth(900);
        imgLogo.setFitHeight(121);
        imgLogo.setFitWidth(512);
        StackPane.setMargin(imgLogo, new Insets(25, 0, 0, 0));
        StackPane.setAlignment(imgLogo, Pos.TOP_CENTER);

        MenuItem newGame = new MenuItem("НОВАЯ ИГРА");
        MenuItem replayGame = new MenuItem("ПОВТОР");
        MenuItem exitGame = new MenuItem("ВЫХОД");
        SubMenu mainMenu = new SubMenu(
                newGame, replayGame, exitGame
        );
        MenuBox menuBox = new MenuBox(mainMenu);
        menuBox.setTranslateX(250);
        menuBox.setTranslateY(200);

        newGame.setOnMouseClicked(event->{
            replay = new Replay();
            game = new Game(stage, this, replay);
            game.initGame();
            replay = null;
        });
        replayGame.setOnMouseClicked(event->{
            replay = new Replay();
            game = new Game(stage, this, replay);
            game.flReplay = true;
            game.initGame();
            replay = null;
        });
        exitGame.setOnMouseClicked(event->System.exit(0));
        root.getChildren().addAll(imgFon,imgLogo, menuBox);

        scene = new Scene(root, 900, 600);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                FadeTransition ft = new FadeTransition(Duration.seconds(1),menuBox);
                if (!menuBox.isVisible()) {
                    ft.setFromValue(0);
                    ft.setToValue(1);
                    ft.play();
                    menuBox.setVisible(true);
                }
                else{
                    ft.setFromValue(1);
                    ft.setToValue(0);
                    ft.setOnFinished(evt ->menuBox.setVisible(false));
                    ft.play();

                }
            }
        });
        stage.setScene(scene);
    }

}
