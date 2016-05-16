package beargame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 *
 * @author Anto
 */
public class Menu extends Application {
    static final int HEIGHT_PIXELS = 700;
    static final int WIDTH_PIXELS = 800;
    Scene scene, play;
    BearGame beargame = new BearGame();
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Save the bear!");
        primaryStage.setResizable(false);
        Label titleGame = new Label("Save the Bear!");
        Label titleGame2 = new Label("Save the World!");
        primaryStage.getIcons().add(new Image("oso1.png"));
        Button buttonPlay = new Button("Play");
        buttonPlay.setOnAction((ActionEvent e) -> {
            primaryStage.setScene(play);
            BearGame.timeline.play();
            if (Configuration.isSound()) BearGame.gameMusicPlayer.play();
        });
                //setOnAction(e -> primaryStage.setScene(play));
        Button buttonConf = new Button("Configuración");
        buttonConf.setOnAction((ActionEvent e) -> {
            primaryStage.setScene(Configuration.configuration(scene, primaryStage));
        });
        Button buttonIntr = new Button("Instrucciones");
        buttonIntr.setOnAction((ActionEvent e) -> {
            primaryStage.setScene(Configuration.configuration(scene, primaryStage));
        });
        buttonIntr.setOnAction(e -> primaryStage.setScene(Configuration.instructions(scene, primaryStage)));
        Label copyrigth= new Label ("Creado por Antonio J. Ibáñez");
        VBox menuIni = new VBox(20);
        //Para que todos los botones tenga la misma medida
        menuIni.setPrefWidth(300);
        menuIni.setSpacing(10);
        menuIni.setPadding(new Insets(0, 20, 10, 20));
        buttonPlay.setMaxWidth(menuIni.getPrefWidth());
        buttonConf.setMaxWidth(menuIni.getPrefWidth());
        buttonIntr.setMaxWidth(menuIni.getPrefWidth());
        menuIni.getChildren().addAll(titleGame,titleGame2,buttonPlay,buttonConf, buttonIntr, copyrigth);
        menuIni.setAlignment(Pos.CENTER);
        //String css = Menu.class.getResource("menu.css").toExternalForm();
        //scene.getStylesheets().add(css);
        scene = new Scene(menuIni, WIDTH_PIXELS, HEIGHT_PIXELS, Color.WHITE);
        scene.getStylesheets().add(BearGame.class.getResource("Menu.css").toExternalForm());
        play = beargame.play(scene, primaryStage);
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
