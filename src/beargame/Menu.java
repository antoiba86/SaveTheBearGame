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
    private static Button buttonPlay, buttonConf, buttonIntr, buttonScore, buttonExit;
    private static Label copyrigth;
    
    
    @Override
    public void start(Stage primaryStage) {
        paneRoot = new Pane();
        confToXML.loadFromXML();
        if (confToXML.getConfLanguage().equals("Castellano")) {
            Languages.readLanguageFile("/resources/lang/spa.lang");
            Configuration.setPrincipalLang("Castellano");
        }
        else {
            Languages.readLanguageFile("/resources/lang/eng.lang");
            Configuration.setPrincipalLang("English");
        }
        if (confToXML.getConfSound().equals("On")) Configuration.setSound(true);
        else Configuration.setSound(false);
        primaryStage.setTitle("Save the bear!");
        primaryStage.setResizable(false);
        Label titleGame = new Label(" Save the Bear! \n"
                + "Save the World!");
        titleGame.setId("title");
        primaryStage.getIcons().add(new Image("resources/img/oso1.png"));
        buttonPlay = new Button(Languages.getText(0));
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
        buttonConf = new Button(Languages.getText(1));
        buttonConf.setOnAction((ActionEvent e) -> {
            primaryStage.setScene(Configuration.configuration(scene, primaryStage));
        });
        buttonIntr = new Button(Languages.getText(2));
        buttonIntr.setOnAction((ActionEvent e) -> {
            primaryStage.setScene(Configuration.instructions(scene, primaryStage));
        });
        buttonScore = new Button(Languages.getText(3));
        buttonScore.setOnAction((ActionEvent e) -> {
            primaryStage.setScene(Configuration.listScore(scene, primaryStage));
        });
        buttonExit = new Button(Languages.getText(4));
        buttonExit.setOnAction((ActionEvent e) -> {
            System.exit(0);
        });
        copyrigth= new Label (Languages.getText(5));
        VBox menuIni = new VBox(20);
        //Para que todos los botones tenga la misma medida
        menuIni.setPrefWidth(200);
        menuIni.setSpacing(10);
        menuIni.setPadding(new Insets(0, 20, 10, 20));
        buttonPlay.setMaxWidth(menuIni.getPrefWidth());
        buttonConf.setMaxWidth(menuIni.getPrefWidth());
        buttonIntr.setMaxWidth(menuIni.getPrefWidth());
        buttonExit.setMaxWidth(menuIni.getPrefWidth());
        buttonScore.setMaxWidth(menuIni.getPrefWidth());
        menuIni.getChildren().addAll(titleGame,buttonPlay,buttonConf, buttonIntr, buttonScore, buttonExit, copyrigth);
        menuIni.setAlignment(Pos.CENTER);
        scene = new Scene(menuIni, WIDTH_PIXELS, HEIGHT_PIXELS, Color.WHITE);
        scene.getStylesheets().add(BearGame.class.getResource("/resources/css/Menu.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * @param args the command line arguments to launch the project
     */
    public static void main(String[] args) {
        launch(args);
        
    }

    public static Button getButtonPlay() {
        return buttonPlay;
    }

    public static void setButtonPlay(Button buttonPlay) {
        Menu.buttonPlay = buttonPlay;
    }

    public static Button getButtonConf() {
        return buttonConf;
    }

    public static void setButtonConf(Button buttonConf) {
        Menu.buttonConf = buttonConf;
    }

    public static Button getButtonIntr() {
        return buttonIntr;
    }

    public static void setButtonIntr(Button buttonIntr) {
        Menu.buttonIntr = buttonIntr;
    }

    public static Button getButtonScore() {
        return buttonScore;
    }

    public static void setButtonScore(Button buttonScore) {
        Menu.buttonScore = buttonScore;
    }

    public static Button getButtonExit() {
        return buttonExit;
    }

    public static void setButtonExit(Button buttonExit) {
        Menu.buttonExit = buttonExit;
    }

    public static Label getCopyrigth() {
        return copyrigth;
    }

    public static void setCopyrigth(Label copyrigth) {
        Menu.copyrigth = copyrigth;
    }
    
    
    
}
