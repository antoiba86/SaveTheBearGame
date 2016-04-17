/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beargame;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Anto
 */
public class BearGame extends Application {
    static final int HEIGHT_PIXELS = 700;
    static final int WIDTH_PIXELS = 800;
    static Hero iHero;
    static Populate hero2;
    Populate petrol;
    Timeline timeline;
    Image polarBear;
    Image pB1;
    Image pB2;
    Image pB3;
    Image fondo;
    Image petrol_boat;
    DisplayObject display = new DisplayObject();;
    ImageView splashScreen;
    Button gameButton = new Button("Hola, a ver");
    Scene scene;
    StackPane root;
    static HBox buttonContainer = new HBox();
    GamePlayLoop playGame;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Save the bear!");
        root = new StackPane();
        scene = new Scene(root, WIDTH_PIXELS, HEIGHT_PIXELS, Color.WHITE);
        scene.getStylesheets().add(BearGame.class.getResource("Fondo.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
        createSceneEventHandling();
        loadImageAssets();
        createGameObjects();
        setTime();
        addGameObjectNodes();
        createStartGameLoop();
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    public static class SplashScreen {
        // The Java code that creates and displays your splashscreen is in here
    }
    private void loadImageAssets() {
        polarBear = new Image("polarBear.png", 58, 64, true, false, true);
        pB1 = new Image("osohielo1.png", 145,96, true, false, true );
        pB2 = new Image("osohielo2.png", 145,96, true, false, true );
        pB3 = new Image("osohielo3.png", 145,96, true, false, true );
        //fondo = new Image("fondo.png", 640, 480, true, false, true);
        petrol_boat = new Image("petrolero.png", 56, 77, true, false, true);
        
        //splashScreen = new ImageView(fondo);
    }
    private void createGameObjects() {
        iHero = new Hero(this, "M67,11 L67,11 48,15 42,38 16,43 4,64 3,74 10,80 70,87 121,86 133,77 133,66 125,46 99,39 99,28 83,14 Z", -200, 0, pB1, pB2, pB3);
        //hero2 = new Populate(this, "M150 0 L75 500 L225 200 Z", -100, 0, polarBear);
        //hero2 = new Populate();
    }
    
    private void addGameObjectNodes() {
        root.getChildren().addAll(iHero.spriteFrame);
    }
    
    private void addNewGameObjectNodes() {
        root.getChildren().add(petrol.spriteFrame);
    }
    
    public void setTime () {
        timeline = new Timeline(new KeyFrame(
        Duration.millis(5000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                createBoat();
                createDisplayedObject();
                addNewGameObjectNodes();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
    public void createBoat() {
        petrol = new Populate(this, "M28,3 L28,3 13,13 10,46 28,71 45,46 42,12 Z", 100, 100, petrol_boat);
    }
    
    public void createDisplayedObject() {
        display.addDisplayed_Object(petrol);
    }
    private void createStartGameLoop() {
        playGame = new GamePlayLoop(this);
        playGame.start();
    }
    private void createSceneEventHandling() {
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case UP: Hero.setUp(true); break;
                case DOWN: Hero.setDown(true); break;
                case LEFT: Hero.setLeft(true); break;
                case RIGHT: Hero.setRight(true); break;
                case W: Hero.setUp(true); break;
                case S: Hero.setDown(true); break;
                case A: Hero.setLeft(true); break;
                case D: Hero.setRight(true); break;
            }
        });
        scene.setOnKeyReleased((KeyEvent event) -> {
            switch (event.getCode()) {
                case UP: Hero.setUp(false); break;
                case DOWN: Hero.setDown(false); break;
                case LEFT: Hero.setLeft(false); break;
                case RIGHT: Hero.setRight(false); break;
                case W: Hero.setUp(false); break;
                case S: Hero.setDown(false); break;
                case A: Hero.setLeft(false); break;
                case D: Hero.setRight(false); break;
            }
        });
    }
    
}
