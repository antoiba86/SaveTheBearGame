package beargame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 *
 * @author Anto
 */
public class Menu extends Application {
    static final int HEIGHT_PIXELS = 700;
    static final int WIDTH_PIXELS = 800;
    Scene scene;
    Pane paneRoot;
    
    @Override
    public void start(Stage primaryStage) {
        paneRoot = new Pane();
        primaryStage.setTitle("Save the bear!");
        primaryStage.setResizable(false);
        Label titleGame = new Label(" Save the Bear! \n"
                + "Save the World!");
        titleGame.setId("title");
        primaryStage.getIcons().add(new Image("resources/oso1.png"));
        Button buttonPlay = new Button("Play");
        buttonPlay.setOnAction((ActionEvent e) -> {
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            BearGame beargame = new BearGame();
            Scene play = beargame.play(scene, primaryStage, stage);
            stage.setScene(play);
            stage.show();
            //hide this current window
            primaryStage.getScene().getWindow().hide();
            BearGame.getTimeline().play();
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
        Button buttonExit = new Button("Exit");
        buttonExit.setOnAction((ActionEvent e) -> {
            System.exit(0);
        });
        Label copyrigth= new Label ("Creado por Antonio J. Ibáñez");
        VBox menuIni = new VBox(20);
        //Para que todos los botones tenga la misma medida
        menuIni.setPrefWidth(200);
        menuIni.setSpacing(10);
        menuIni.setPadding(new Insets(0, 20, 10, 20));
        buttonPlay.setMaxWidth(menuIni.getPrefWidth());
        buttonConf.setMaxWidth(menuIni.getPrefWidth());
        buttonIntr.setMaxWidth(menuIni.getPrefWidth());
        buttonExit.setMaxWidth(menuIni.getPrefWidth());
        menuIni.getChildren().addAll(titleGame,buttonPlay,buttonConf, buttonIntr, buttonExit, copyrigth);
        menuIni.setAlignment(Pos.CENTER);
        scene = new Scene(menuIni, WIDTH_PIXELS, HEIGHT_PIXELS, Color.WHITE);
        scene.getStylesheets().add(BearGame.class.getResource("Menu.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * @param args the command line arguments to launch the project
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
