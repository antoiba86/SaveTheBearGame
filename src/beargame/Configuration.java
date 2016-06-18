package beargame;

import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
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
        Label title = new Label(Languages.getText(1));
        //7
        Label titleSound = new Label(Languages.getText(6));
        Button buttonOn = new Button();
        if (sound) buttonOn.setText("On");
        else buttonOn.setText("Off");
        buttonOn.setOnAction((ActionEvent e) -> {
                buttonOn.setText(configButtonSound());
        });
        Label labelLanguage = new Label(Languages.getText(25));
        ChoiceBox cbLanguage = new ChoiceBox();
        //27 y 28
        cbLanguage.getItems().addAll(Languages.getText(26), Languages.getText(27));
        cbLanguage.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number lang1, Number lang2) {
              String langSelected = cbLanguage.getItems().get((Integer) lang2).toString();
              switch (langSelected) {
                  case ("Castellano"):
                  case ("Spanish"): Languages.readLanguageFile("lang/spa.lang");break;
                  case ("Inglés"):
                  case ("English"): Languages.readLanguageFile("lang/eng.lang");break;
              }
            }
          });
        
        Button buttonMenu = new Button(Languages.getText(4));
        buttonMenu.setOnAction((ActionEvent e) -> {
            window.setScene(menu);
            String lang = cbLanguage.getSelectionModel().getSelectedItem().toString();
            String sound = buttonOn.getText();
            confToXML.setConfLanguage(lang);
            confToXML.setConfSound(sound);
            confToXML.saveToXML();
        });
        VBox conf = new VBox(20);
        conf.setPrefWidth(200);
        conf.setSpacing(10);
        conf.setPadding(new Insets(0, 20, 10, 20));
        buttonOn.setMaxWidth(conf.getPrefWidth());
        HBox buttonSound = new HBox(20);
        buttonSound.setAlignment(Pos.CENTER);
        buttonSound.getChildren().addAll(titleSound, buttonOn);
        HBox buttonLanguage = new HBox(20);
        buttonLanguage.setAlignment(Pos.CENTER);
        buttonLanguage.getChildren().addAll(labelLanguage, cbLanguage);
        buttonMenu.setMaxWidth(conf.getPrefWidth());
        conf.getChildren().addAll(title,buttonSound, buttonLanguage, buttonMenu);
        conf.setAlignment(Pos.CENTER);
        configuration = new Scene(conf, Menu.WIDTH_PIXELS, Menu.HEIGHT_PIXELS);
        configuration.getStylesheets().add(BearGame.class.getResource("/resources/css/Menu.css").toExternalForm());
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
        //8
        Label title = new Label(Languages.getText(7));
        //5
        Button buttonMenu = new Button(Languages.getText(4));
        buttonMenu.setOnAction((ActionEvent e) -> {
            window.setScene(menu);
        });
        Label instrText = new Label();
        //9
        instrText.setText(Languages.getText(8));
        Label instrText2 = new Label();
        //10
        instrText2.setText(Languages.getText(9));
        Label instrText3 = new Label();
        //11
        instrText3.setText(Languages.getText(10));
        instrText.setWrapText(true);
        instrText.setPrefWidth(450);
        instrText2.setWrapText(true);
        instrText2.setPrefWidth(450);
        instrText3.setWrapText(true);
        instrText3.setPrefWidth(450);
        VBox instr = new VBox(20);
        instr.setPrefWidth(300);
        instr.setSpacing(10);
        instr.setPadding(new Insets(0, 20, 10, 20));
        VBox instr2 = new VBox(20);
        instr2.setPrefWidth(300);
        instr2.setSpacing(10);
        instr2.setPadding(new Insets(0, 20, 10, 20));
        Image coin = new Image("resources/img/moneda1.png", 20,15,true,false,true);
        Image gem = new Image("resources/img/gema1.png", 20,20,true,false,true);
        Image flechas = new Image("resources/img/flechas.png", 100,98,true,false,true);
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
        VBox newBox = new VBox(50);
        newBox.setMaxWidth(500);
        newBox.setMaxHeight(600);
        newBox.setAlignment(Pos.CENTER);
        newBox.setId("caja");
        panel.add(instr,0,0);
        panel.add(photos,0,1);
        panel.add(instr2,0,2);
        panel.getColumnConstraints().add(0, col1);
        instr.getChildren().addAll(title, instrText);
        instr2.getChildren().addAll(instrText2, flechasAlign, instrText3);
        newBox.getChildren().addAll(panel, buttonMenu);
        pane.getChildren().addAll(newBox);
        instructions = new Scene(pane, Menu.WIDTH_PIXELS, Menu.HEIGHT_PIXELS);
        instructions.getStylesheets().add(BearGame.class.getResource("/resources/css/instructions.css").toExternalForm());
        window.setScene(instructions);
        window.show();
        return instructions;
     }
     
    /**
     * Method to show the Game Over Scene
     * @param menu It is the scene to return to the menu
     * @param window It is the stage of the menu
     * @param score
     * @return This scene to the game's menu
     */
     public Scene gameOver(Scene menu, Stage window, boolean score) {
        Scene gameOver = null;
        //12
        Label titleGame = new Label(Languages.getText(11)
                + " ");
        titleGame.setId("title");
        //13
        Button buttonCont = new Button(Languages.getText(12));
        buttonCont.setOnAction((ActionEvent e) -> {
            window.setScene(menu);
        });
        //14
        Button buttonExit = new Button(Languages.getText(13));
        buttonExit.setOnAction((ActionEvent e) -> {
            System.exit(0);
        });
        AudioClip soundOver = new AudioClip(this.getClass().getResource("/resources/Sound/gameOver.mp3").toExternalForm());
        if (isSound()) soundOver.play();;
        VBox conf = new VBox(20);
        VBox moveButtons = new VBox(100);
        conf.setPrefWidth(200);
        conf.setSpacing(10);
        conf.setPadding(new Insets(0, 20, 10, 20));
        buttonCont.setMaxWidth(conf.getPrefWidth());
        buttonExit.setMaxWidth(conf.getPrefWidth());
        HBox hboxNameScore = new HBox(20);
        HBox hboxButtonScore = new HBox(20);
        //15
        Label congratLabel = new Label(Languages.getText(14));
        //16
        Label nameLabel = new Label(Languages.getText(15));
        nameLabel.setId("nameLabel");
        TextField textName = new TextField();
        //17
        Button buttonAccept = new Button(Languages.getText(16));
        buttonAccept.setOnAction((ActionEvent e) -> {
            String name = textName.getText();
            if (name.length() >= 12) {
                Alert alert = new Alert(AlertType.INFORMATION);
                //18
                alert.setTitle(Languages.getText(17));
                //19
                alert.setHeaderText(Languages.getText(18));
                //20
                alert.setContentText(Languages.getText(19));
                alert.showAndWait();
            }
            else {
                if ("".equals(name)) name = "Anto";
                Score.setMaxScore(Slidding.gameScore, name);
                Score.saveToXML();
                congratLabel.setVisible(false);
                hboxNameScore.setVisible(false);
                hboxButtonScore.setVisible(false);
                moveButtons.setVisible(true);
                Slidding.gameScore = 0;
            }
        });
        //21
        Button buttonCancel = new Button(Languages.getText(21));
        buttonCancel.setOnAction((ActionEvent e) -> {
            Score.setMaxScore(Slidding.gameScore, "AAA");
            Score.saveToXML();
            congratLabel.setVisible(false);
            hboxNameScore.setVisible(false);
            hboxButtonScore.setVisible(false);
            moveButtons.setVisible(true);
            Slidding.gameScore = 0;
        });
        hboxNameScore.getChildren().addAll(nameLabel,textName);
        hboxButtonScore.getChildren().addAll(buttonAccept,buttonCancel);
        HBox buttonMenu = new HBox(20);
        hboxNameScore.setAlignment(Pos.CENTER);
        hboxButtonScore.setAlignment(Pos.CENTER);
        buttonMenu.setAlignment(Pos.CENTER);
        buttonMenu.getChildren().addAll(buttonCont, buttonExit);
        moveButtons.getChildren().add(buttonMenu);
        conf.getChildren().addAll(titleGame, congratLabel, hboxNameScore, hboxButtonScore, moveButtons);
        conf.setAlignment(Pos.CENTER);
        congratLabel.setVisible(false);
        hboxNameScore.setVisible(false);
            hboxButtonScore.setVisible(false);
        if (score) {
            congratLabel.setVisible(true);
            hboxNameScore.setVisible(true);
            hboxButtonScore.setVisible(true);
            moveButtons.setVisible(false);
        }
        gameOver = new Scene(conf, Menu.WIDTH_PIXELS, Menu.HEIGHT_PIXELS);
        gameOver.getStylesheets().add(BearGame.class.getResource("/resources/css/GameOver.css").toExternalForm());
        if(!score) conf.setId("gameover");
        else conf.setId("trophy");
        congratLabel.setId("congrat");
        window.setScene(gameOver);
        window.show();
        return gameOver;
    }
     
    public static Scene listScore(Scene menu, Stage window) {
        Scene listScore = null;
        Score.loadFromXML();
        //22
        Label title = new Label(Languages.getText(21));
        ArrayList<String> autores = Score.getListAuthor();
        ArrayList<String> puntos = Score.getListScores();
        GridPane listWinner = new GridPane();
        listWinner.setHgap(10);
        listWinner.setVgap(12);
        //23
        listWinner.add(new Label(Languages.getText(22)), 0, 0);
        //24
        listWinner.add(new Label(Languages.getText(23)), 1, 0);
        //25
        if (Score.getListScores().isEmpty()) listWinner.add(new Label(Languages.getText(24)), 0,1);
        else  {
            for (int i = 0; i < puntos.size();i++) {
                String[] point = puntos.get(i).split("\\."); 
                listWinner.add(new Label(autores.get(i)), 0,i+1);
                listWinner.add(new Label(point[0]), 1,i+1);
            }
        }
        //5
        Button buttonMenu = new Button(Languages.getText(4));
        buttonMenu.setMaxWidth(150);
        buttonMenu.setOnAction((ActionEvent e) -> {
            window.setScene(menu);
        });
        VBox newBox = new VBox(100);
        newBox.setMaxWidth(500);
        newBox.setMaxHeight(500);
        newBox.setAlignment(Pos.CENTER);
        newBox.setId("caja");
        title.setId("title");
        newBox.getChildren().addAll(title, listWinner, buttonMenu);
        listWinner.setAlignment(Pos.CENTER);
        StackPane pane = new StackPane();
        pane.getChildren().add(newBox);
        listScore = new Scene(pane, Menu.WIDTH_PIXELS, Menu.HEIGHT_PIXELS);
        listScore.getStylesheets().add(BearGame.class.getResource("/resources/css/puntuacion.css").toExternalForm());
        return listScore;
        
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
    
    public static String configButtonSound() {
        String text = "";
        if (sound) {
            sound = false;
            text = "Off";
        }
        else {
            sound = true;
            text = "On";
        }
        return text;
    }
    
}
