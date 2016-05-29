package beargame;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * Class configuration of the game
 * @author Antonio Jesús Ibáñez García
 */
public class Configuration {
    private static boolean sound = true;
    
    /**
     * Method to show the sound configuration
     * @param menu It is the scene to return to the menu
     * @param window It is the stage of the menu
     * @return This scene to the game's menu
     */
    public static Scene configuration(Scene menu, Stage window) {
        Scene configuration = null;
        Label title = new Label("Configuración");
        Label titleSound = new Label("SONIDO");
        Button buttonOn = new Button("On");
        buttonOn.setOnAction((ActionEvent e) -> {
            sound = true;
        });
        Button buttonOff = new Button("Off");
        buttonOff.setOnAction((ActionEvent e) -> {
            sound = false;
        });
        Button buttonMenu = new Button("Exit");
        buttonMenu.setOnAction((ActionEvent e) -> {
            window.setScene(menu);
        });
        VBox conf = new VBox(20);
        conf.setPrefWidth(200);
        conf.setSpacing(10);
        conf.setPadding(new Insets(0, 20, 10, 20));
        buttonOn.setMaxWidth(conf.getPrefWidth());
        buttonOff.setMaxWidth(conf.getPrefWidth());
        HBox buttonSound = new HBox(20);
        buttonSound.setAlignment(Pos.CENTER);
        buttonSound.getChildren().addAll(titleSound, buttonOn, buttonOff);
        buttonMenu.setMaxWidth(conf.getPrefWidth());
        conf.getChildren().addAll(title,buttonSound, buttonMenu);
        conf.setAlignment(Pos.CENTER);
        configuration = new Scene(conf, Menu.WIDTH_PIXELS, Menu.HEIGHT_PIXELS);
        configuration.getStylesheets().add(BearGame.class.getResource("Menu.css").toExternalForm());
        window.setScene(configuration);
        window.show();
        return configuration;
    }
     
    /**
     * Method to show the game's instructions
     * @param menu It is the scene to return to the menu
     * @param window It is the stage of the menu
     * @return This scene to the game's menu
     */
    public static Scene instructions(Scene menu, Stage window) {
        Scene instructions = null;
        GridPane panel = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints(400,450,450);
        Label title = new Label("Bienvenido, compañero osezno");
        Button buttonMenu = new Button("Exit");
        buttonMenu.setOnAction((ActionEvent e) -> {
            window.setScene(menu);
        });
        Label instrText = new Label();
        instrText.setText("Soy el Oso Polar,\n"
                + "Consigue los máximos puntos posibles para ganar a tus competidores y salvar el Mundo Osezno\n"
                + "Colecciona los monedas para conseguirlo");
        Label instrText2 = new Label();
        instrText2.setText("Para ello utiliza las teclas siguientes para moverte por el escenario");
        instrText.setWrapText(true);
        instrText.setPrefWidth(450);
        instrText2.setWrapText(true);
        instrText2.setPrefWidth(450);
        VBox instr = new VBox(20);
        instr.setPrefWidth(300);
        instr.setSpacing(10);
        instr.setPadding(new Insets(0, 20, 10, 20));
        VBox instr2 = new VBox(20);
        instr2.setPrefWidth(300);
        instr2.setSpacing(10);
        instr2.setPadding(new Insets(0, 20, 10, 20));
        Image coin = new Image("resources/moneda1.png", 20,15,true,false,true);
        Image gem = new Image("resources/gema1.png", 20,20,true,false,true);
        Image flechas = new Image("resources/flechas.png", 100,63,true,false,true);
        ImageView photo1 = new ImageView();
        ImageView photo2 = new ImageView();
        ImageView flechaView = new ImageView();
        photo1.setImage(coin);
        photo2.setImage(gem);
        flechaView.setImage(flechas);
        buttonMenu.setMaxWidth(200);
        HBox photos = new HBox(20);
        HBox flechasAlign = new HBox(20);
        photos.getChildren().addAll(photo1,photo2);
        photos.setAlignment(Pos.CENTER);
        flechasAlign.getChildren().addAll(flechaView);
        flechasAlign.setAlignment(Pos.CENTER);
        StackPane pane = new StackPane();
        VBox newBox = new VBox(100);
        newBox.setMaxWidth(500);
        newBox.setMaxHeight(500);
        newBox.setAlignment(Pos.CENTER);
        newBox.setId("caja");
        panel.add(instr,0,0);
        panel.add(photos,0,1);
        panel.add(instr2,0,2);
        panel.getColumnConstraints().add(0, col1);
        instr.getChildren().addAll(title, instrText);
        instr2.getChildren().addAll(instrText2, flechasAlign);
        newBox.getChildren().addAll(panel, buttonMenu);
        pane.getChildren().addAll(newBox);
        instructions = new Scene(pane, Menu.WIDTH_PIXELS, Menu.HEIGHT_PIXELS);
        instructions.getStylesheets().add(BearGame.class.getResource("instructions.css").toExternalForm());
        window.setScene(instructions);
        window.show();
        return instructions;
     }
     
    /**
     * Method to show the Game Over Scene
     * @param menu It is the scene to return to the menu
     * @param window It is the stage of the menu
     * @return This scene to the game's menu
     */
     public static Scene gameOver(Scene menu, Stage window) {
        Scene gameOver = null;
        Button buttonCont = new Button("Continuar");
        buttonCont.setOnAction((ActionEvent e) -> {
            window.setScene(menu);
        });
        Button buttonExit = new Button("Salir");
        buttonExit.setOnAction((ActionEvent e) -> {
            System.exit(0);
        });
        soundOver();
        VBox conf = new VBox(20);
        conf.setPrefWidth(200);
        conf.setSpacing(10);
        conf.setPadding(new Insets(0, 20, 10, 20));
        buttonCont.setMaxWidth(conf.getPrefWidth());
        buttonExit.setMaxWidth(conf.getPrefWidth());
        HBox buttonMenu = new HBox(20);
        VBox moveButtons = new VBox(100);
        buttonMenu.setAlignment(Pos.CENTER);
        buttonMenu.getChildren().addAll(buttonCont, buttonExit);
        moveButtons.getChildren().add(buttonMenu);
        conf.getChildren().addAll(moveButtons);
        conf.setAlignment(Pos.CENTER);
        gameOver = new Scene(conf, Menu.WIDTH_PIXELS, Menu.HEIGHT_PIXELS);
        gameOver.getStylesheets().add(BearGame.class.getResource("GameOver.css").toExternalForm());
        window.setScene(gameOver);
        window.show();
        return gameOver;
    }
     
     /**
      * Method to get the variable sound to know if the sound is off or on
      * @return The variable sound
      */
    public static boolean isSound() {
        return sound;
    }
    
    /**
     * Method to set the sound off or on
     * @param sound It is the variable to set off or on the sound's game
     */
    public static void setSound(boolean sound) {
        Configuration.sound = sound;
    }
     
    /**
     * Method to play the game over sound
     */
    public static void soundOver() {
        String uriString = new File("gameOver.mp3").toURI().toString();
        MediaPlayer player = new MediaPlayer( new Media(uriString));
        player.play();
    }
    
}
