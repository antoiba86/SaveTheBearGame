package beargame;

import java.io.File;
import java.net.MalformedURLException;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
    Population petrol;
    PirateBoat pirate;
    Shark shark;
    Plane plane;
    Coin coin;
    Gemstone gemstone;
    Group skyLine = new Group();
    Timeline timeline;
    Image[] polarBear = new Image[4];
    Image[] explosion = new Image[15];
    Image[] pirate_boat = new Image[4];
    Image[] coin_image = new Image[8];
    Image[] gem_image = new Image[6];
    Image[] shark_image = new Image[9];
    Image[] plane_image = new Image[9];
    Image petrol_boat;
    Image fondo;
    DisplayObject display = new DisplayObject();;
    ImageView splashScreen;
    //Button gameButton = new Button("Hola, a ver");
    private static final String GAME_MUSIC_PATH = "sound_track.mp3";
    private static MediaPlayer gameMusicPlayer; //Si no esta declarado aquí el, recolector de basura de Java lo detiene en diez segundos.
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
        primaryStage.getIcons().add(new Image("oso1.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
        createSceneEventHandling();
        //playMusic();
        loadImageAssets();
        createBear();
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
    /**
     * Method to load the images of every object of the game
     */
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
        for (int i = 0; i < gem_image.length;i++) {
            gem_image[i] = new Image("gema" + (i+1) + ".png", 40,40,true,false,true);
        }
        for (int i = 0; i < shark_image.length;i++) {
            shark_image[i] = new Image("tiburon" + (i+1) + ".png",140,86,true,false,true);
        }
        for (int i = 0; i < plane_image.length;i++) {
            plane_image[i] = new Image("plane" + (i+1) + ".png", 83,64,true,false,true);
        }
        
        //splashScreen = new ImageView(fondo);
    }
    
    /**
     * Method to create the principal character of the game
     */
    public void createBear() {
        iHero = new Hero(this, "M 58.50,13.75 L 58.50,13.75 41.25,20.25 37.50,29.50 35.75,40.75 20.75,45.50 11.75,60.50 22.75,63.25 90.25,63.25 102.50,58.75 102.25,48.25 92.25,42.25 93.00,34.75 89.75,26.25 88.25,21.25 75.00,16.50 62.25,14.50 Z", 0, 300, 
                polarBear[0], polarBear[1], polarBear[2], polarBear[3],
                explosion[0],explosion[1],explosion[2],explosion[3],
                explosion[4],explosion[5],explosion[6],explosion[7],explosion[8],
                explosion[9],explosion[10],explosion[11],explosion[12],explosion[13],
                explosion[14]);
    }
    
    /**
     * Method to set a timer in order to add objects to the game such as coins or boats
     */
    public void setTime () {
        timeline = new Timeline(new KeyFrame(
        Duration.millis(10000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                int n = (int)(Math.random()*2+1);
                createTreasure(n);
                createBoat();
                createDisplayedObject(n);
                addNewGameObjectNodes(n);
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
    /**
     * Method to create a coin or a gem to put in the game
     * @param n Is the variable to choose between a coin or a gem in order to put in the game
     */
    public void createTreasure(int n) {
        double width = (Math.random()*690+100);
        double height = (Math.random()*390+310);
        if (n == 1 )coin = new Coin(this, width,height, coin_image[0],coin_image[1],coin_image[2],coin_image[3],coin_image[4],coin_image[5],coin_image[6],coin_image[7]);
        else gemstone = new Gemstone(this, width,height, gem_image[0],gem_image[1],gem_image[2],gem_image[3],gem_image[4],gem_image[5]);
    }
    
    /**
     * Method to create enemies in the game
     */
    public void createBoat() {
        pirate = new PirateBoat(this, 400, 400, pirate_boat[0], pirate_boat[1], pirate_boat[2], pirate_boat[3]);
        shark = new Shark(this, 600,500, shark_image[0],shark_image[1],shark_image[2],shark_image[3],shark_image[4],shark_image[5],shark_image[6],shark_image[7],shark_image[8]);
        plane = new Plane(this, 600,100, plane_image[0],plane_image[1],plane_image[2],plane_image[3],plane_image[4],plane_image[5],plane_image[6],plane_image[7],plane_image[8]);
    }
    
    /**
     * Method to add to objects to the array of objects
     * @param n Is the variable that decide is if a coin or a gem to add to the array;
     */
    public void createDisplayedObject(int n) {
        if (n==1)display.addDisplayed_Object(coin);
        else display.addDisplayed_Object(gemstone);
        display.addDisplayed_Object(pirate);
        display.addDisplayed_Object(shark);
        display.addDisplayed_Object(plane);
    }
    
    /**
     * Method to add the sprite of every object to the game to display in the screen
     * @param n Is the variable that decide if is a coin or a gem
     */
    public void addNewGameObjectNodes(int n) {
        if (n ==1) root.getChildren().add(coin.spriteFrame);
        else root.getChildren().add(gemstone.spriteFrame);
        root.getChildren().add(pirate.spriteFrame);
        root.getChildren().add(shark.spriteFrame);
        root.getChildren().add(plane.spriteFrame);    }
    
    /**
     * Method to add objects which the game starts
     */
    public void addGameObjectNodes() {
        root.getChildren().addAll(skyLine, iHero.spriteFrame);
    }
    
    /**
     * Principal method to create the loop of the game
     */
    private void createStartGameLoop() {
        playGame = new GamePlayLoop(this);
        playGame.start();
    }
    
    /**
     * Method with the key to move el principal character
     */
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
    
    /**
     * Method with the principal theme of the game
     */
    /*private static void playMusic(){
        try {
            gameMusicPlayer = new MediaPlayer(new Media(new File(GAME_MUSIC_PATH).toURI().toURL().toString()));
        } catch (MalformedURLException ex) {
        }
        gameMusicPlayer.setOnEndOfMedia(() -> {
            gameMusicPlayer.seek(Duration.ZERO);
            gameMusicPlayer.play();
        });
        gameMusicPlayer.play();
    }*/
    
}
