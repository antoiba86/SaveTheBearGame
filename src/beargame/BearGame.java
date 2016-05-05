/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beargame;

import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
    static Population hero2;
    Group skyLine = new Group();
    Population petrol;
    PirateBoat pirate;
    Coin coin;
    Timeline timeline;
    Image polarBear[] = new Image[4];
    Image explosion[] = new Image[15];
    Image pirate_boat[] = new Image[4];
    Image[] coin_image = new Image[8];
    String pirate_svPath = "M 120,14 L 120,14 106,98 89,51 80,91 67,89 16,109 68,133 73,144 104,152 152,152 180,143 189,104 189,80 177,78 166,48 144,45 126,44 134,25 133,15 Z";
    Image fondo;
    Image petrol_boat;
    DisplayObject display = new DisplayObject();;
    ImageView splashScreen;
    Button gameButton = new Button("Hola, a ver");
    Scene scene;
    Pane root;
    static HBox buttonContainer = new HBox();
    GamePlayLoop playGame;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Save the bear!");
        root = new Pane();
        scene = new Scene(root, WIDTH_PIXELS, HEIGHT_PIXELS, Color.WHITE);
        scene.getStylesheets().add(BearGame.class.getResource("Fondo.css").toExternalForm());
        Slidding sky = new Slidding();
        skyLine = sky.sky();
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
    public void SplashScreen() {
       
    }
    public void loadImageAssets() {
        for (int i=0; i < polarBear.length; i++) {
            polarBear[i] = new Image("osohieloagua" + (i+1) + ".png", 120,73, true, false, true );
        }
        for (int i=0; i< explosion.length;i++) {
            explosion[i] = new Image("explosion" + (i+1) + ".png", 120,73,true,false,true);
        }
        for (int i=0; i< pirate_boat.length;i++) {
            pirate_boat[i] = new Image("barcoestela" + (i+1) + ".png", 150,122,true,false,true);
        }
        for (int i=0; i < coin_image.length;i++) {
            coin_image[i] = new Image("moneda" + (i+1) + ".png", 40,39,true,false,true);
        }
        
        /*
        polarBear[0] = new Image("osohieloagua1.png", 120,73, true, false, true );
        polarBear[1] = new Image("osohieloagua2.png", 120,73, true, false, true );
        polarBear[2] = new Image("osohieloagua3.png", 120,73, true, false, true );
        polarBear[3] = new Image("osohieloagua4.png", 120,73, true, false, true );  */
        //fondo = new Image("fondo.png", 640, 480, true, false, true);
        petrol_boat = new Image("petrolero.png", 56, 77, true, false, true);
        
        //splashScreen = new ImageView(fondo);
    }
    public void createGameObjects() {
        iHero = new Hero(this, "M 58.50,13.75 L 58.50,13.75 41.25,20.25 37.50,29.50 35.75,40.75 20.75,45.50 11.75,60.50 22.75,63.25 90.25,63.25 102.50,58.75 102.25,48.25 92.25,42.25 93.00,34.75 89.75,26.25 88.25,21.25 75.00,16.50 62.25,14.50 Z", 0, 300, 
                polarBear[0], polarBear[1], polarBear[2], polarBear[3],
                explosion[0],explosion[1],explosion[2],explosion[3],
                explosion[4],explosion[5],explosion[6],explosion[7],explosion[8],
                explosion[9],explosion[10],explosion[11],explosion[12],explosion[13],
                explosion[14]);
        //hero2 = new Population(this, "M150 0 L75 500 L225 200 Z", -100, 0, polarBear);
        //hero2 = new Population();
    }
    
    public void addGameObjectNodes() {
        root.getChildren().addAll(skyLine, iHero.spriteFrame);
    }
    
    public void addNewGameObjectNodes() {
        root.getChildren().add(coin.spriteFrame);
        root.getChildren().add(pirate.spriteFrame);
    }
    
    public void setTime () {
        timeline = new Timeline(new KeyFrame(
        Duration.millis(5000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                createCoin();
                createBoat();
                createDisplayedObject();
                addNewGameObjectNodes();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
    public void createBoat() {
        pirate = new PirateBoat(this, pirate_svPath, 400, 400, pirate_boat[0], pirate_boat[1], pirate_boat[2], pirate_boat[3]);
        
    }
    
    public void createCoin() {
            double width = (Math.random()*700+100);
            double height = (Math.random()*400+300);
            coin = new Coin(this, width,height, coin_image[0],coin_image[1],coin_image[2],coin_image[3],coin_image[4],coin_image[5],coin_image[6],coin_image[7]);
    }
    
    public void createDisplayedObject() {
        display.addDisplayed_Object(coin);
        display.addDisplayed_Object(pirate);
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
