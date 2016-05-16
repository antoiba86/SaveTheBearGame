/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beargame;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import static javafx.scene.layout.VBox.setMargin;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 *
 * @author DAW13
 */
public class Configuration {
    private static boolean sound = true;
    
     public static Scene configuration(Scene menu, Stage window) {
        Scene configuration = null;
        Label title = new Label("Configuration");
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
        //paneRoot.getStylesheets().add(BearGame.class.getResource("Fondo.css").toExternalForm());
        VBox conf = new VBox(20);
        //Para que todos los botones tenga la misma medida
        conf.setPrefWidth(300);
        conf.setSpacing(10);
        conf.setPadding(new Insets(0, 20, 10, 20));
        buttonOn.setMaxWidth(conf.getPrefWidth());
        buttonOff.setMaxWidth(conf.getPrefWidth());
        HBox buttonSound = new HBox(20);
        buttonSound.getChildren().addAll(buttonOn, buttonOff);
        buttonMenu.setMaxWidth(conf.getPrefWidth());
        conf.getChildren().addAll(title,buttonSound, buttonMenu);
        conf.setAlignment(Pos.CENTER);
        configuration = new Scene(conf, Menu.WIDTH_PIXELS, Menu.HEIGHT_PIXELS);
        configuration.getStylesheets().add(BearGame.class.getResource("Menu.css").toExternalForm());
        window.setScene(configuration);
        window.show();
        return configuration;
    }
     
     public static Scene instructions(Scene menu, Stage window) {
        Scene instructions = null;
        Label title = new Label("Instructions");
        Button buttonMenu = new Button("Exit");
        
        buttonMenu.setOnAction((ActionEvent e) -> {
            window.setScene(menu);
        });
        Text instructionsText = new Text();
        instructionsText.setText("Reglas de juego para Save the Bear!. El jugador debe esquivar todos los enemigos y conseguir "
                + "la máxima puntuación posible. ¡¡¡¡Es un juego sin fin!!!! Disfrutad");
        TextFlow textInst = new TextFlow(instructionsText);
        setMargin(textInst, new Insets(6));
        VBox instr = new VBox(20);
        instr.setPrefWidth(300);
        instr.setSpacing(10);
        instr.setPadding(new Insets(0, 20, 10, 20));
        buttonMenu.setMaxWidth(instr.getPrefWidth());
        instr.getChildren().addAll(title,textInst, buttonMenu);
        instr.setAlignment(Pos.CENTER);
        instructions = new Scene(instr, Menu.WIDTH_PIXELS, Menu.HEIGHT_PIXELS);
        instructions.getStylesheets().add(BearGame.class.getResource("Menu.css").toExternalForm());
        window.setScene(instructions);
        window.show();
        return instructions;
     }

    public static boolean isSound() {
        return sound;
    }

    public static void setSound(boolean sound) {
        Configuration.sound = sound;
    }
     
    
}
