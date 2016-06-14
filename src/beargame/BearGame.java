package beargame;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Principal Game's class
 * @author Antonio Jesús Ibáñez Garcia
 */
public class BearGame {
    private Scene scene, play;
    private Stage window, primary;
    private Pane paneRoot;
    static Hero iHero;
    /*Si se utiliza la base de datos
    private ArrayList<EnemyLocation> enemies = new ArrayList<>();
    int tiempo = 0;
    int maxId;
    int id = 0;*/
    private Coin coin;
    private Gemstone gemstone;
    private Group skyLine = new Group();
    private static Timeline timeline;
    private final Image[] polarBear = new Image[4];
    private final Image[] explosion = new Image[15];
    private final Image[] pirate_boat = new Image[4];
    private final Image[] coin_image = new Image[8];
    private final Image[] gem_image = new Image[6];
    private final Image[] shark_image = new Image[9];
    private final Image[] plane_image = new Image[9];
    private final Random random = new Random();
    private final DisplayObject display = new DisplayObject();
    GamePlayLoop playGame;
    private static final String GAME_MUSIC_PATH = "/resources/Sound/sound_track.mp3";
    public static MediaPlayer gameMusicPlayer; //Si no esta declarado aquí el, recolector de basura de Java lo detiene en diez segundos.
    
    /**
     * This class is the playgame menu 
     * @param menu It is the scene to return to the menu
     * @param window It is the stage of the menu
     * @param primary It is this stage
     * @return This scene to the game's menu
     */
    public Scene play(Scene menu, Stage window, Stage primary) {
        scene = menu;
        this.window = window;
        this.primary = primary;
        paneRoot = new Pane();
        paneRoot.setId("paneRoot");
        paneRoot.getStylesheets().add(BearGame.class.getResource("/resources/css/Fondo.css").toExternalForm());
        Slidding sky = new Slidding();
        skyLine = sky.sky();
        if (Configuration.isSound()) playMusic();
        loadImageAssets();
        createBear();
        setTime();
        timeline.play();
        addGameObjectNodes();
        createStartGameLoop();
        play = new Scene(paneRoot, Menu.WIDTH_PIXELS, Menu.HEIGHT_PIXELS);
        createSceneEventHandling(play);
        return play;
    }
    
    /**
     * Method to load the images of every object of the game
     */
    public void loadImageAssets() {
        for (int i=0; i < polarBear.length; i++) {
            polarBear[i] = new Image("resources/img/osohieloagua" + (i+1) + ".png", 120,73, true, false, true );
        }
        for (int i=0; i< explosion.length;i++) {
            explosion[i] = new Image("resources/img/explosion" + (i+1) + ".png", 120,73,true,false,true);
        }
        for (int i=0; i< pirate_boat.length;i++) {
            pirate_boat[i] = new Image("resources/img/barcoestela" + (i+1) + ".png", 150,122,true,false,true);
        }
        for (int i=0; i < coin_image.length;i++) {
            coin_image[i] = new Image("resources/img/moneda" + (i+1) + ".png", 40,39,true,false,true);
        }
        for (int i = 0; i < gem_image.length;i++) {
            gem_image[i] = new Image("resources/img/gema" + (i+1) + ".png", 40,40,true,false,true);
        }
        for (int i = 0; i < shark_image.length;i++) {
            shark_image[i] = new Image("resources/img/tiburon" + (i+1) + ".png",140,86,true,false,true);
        }
        for (int i = 0; i < plane_image.length;i++) {
            plane_image[i] = new Image("resources/img/plane" + (i+1) + ".png", 83,64,true,false,true);
        }
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
    public void setTime() {
        timeline = new Timeline(new KeyFrame(
        Duration.millis(10000), (ActionEvent ae) -> {
            int n = 1+random.nextInt(2);
            createTreasure(n);
            createDisplayedTreasure(n);
            addNewTreasureNodes(n);
            getEnemy();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
    }
    
    
    
    /**
     * Method to add every enemy in the game
     */
    public void getEnemy() {
        int n = 3+random.nextInt(3);
        for (int i = 0; i < n; i++) {
            int type = 1+random.nextInt(3);
            double width;
            double height;
            if (type == 3) {
                width = random.nextDouble()*350+850;
                height = random.nextDouble()*150+100;
            }
            else {
                width = random.nextDouble()*350+850;
                height = random.nextDouble()*300+310;
            }
            double vY;
            double vX = -(1+random.nextInt(2));
            int velocity9 = 1+random.nextInt(5);
            if (velocity9 == 5) vY = -(1+random.nextInt(2));
            else vY = 0;
            ObjectGame object = createEnemy(type,width,height,vX, vY);
            createDisplayedObject(object);
            addNewGameObjectNodes(object.spriteFrame);
        }
    }
    
    /**
     * Method to create a coin or a gem to put in the game
     * @param n It is the variable to choose between a coin or gem in order to put in the game
     */
    public void createTreasure(int n) {
        double width = random.nextDouble()*690+100;
        double height = random.nextDouble()*390+310;
        if (n == 1 )coin = new Coin(this, width,height, coin_image[0],coin_image[1],coin_image[2],coin_image[3],coin_image[4],coin_image[5],coin_image[6],coin_image[7]);
        else gemstone = new Gemstone(this, width,height, gem_image[0],gem_image[1],gem_image[2],gem_image[3],gem_image[4],gem_image[5]);
    }
    
    /**
     * Method to create enemies in the game
     * @param type It is the type of enemy, there is three types
     * @param width It is the X axis of the game where you put the enemy
     * @param height It is the Y axis of the game
     * @param vX It is the velocity of the enemy in the X axis
     * @param vY It is the velocity of the enemy in the Y axis
     * @return The enemy created
     */
    public ObjectGame createEnemy(int type, double width, double height, double vX, double vY) {
        ObjectGame object = new Population();
        if (type == 1) object = new PirateBoat(this, width, height, vX, vY, pirate_boat[0], pirate_boat[1], pirate_boat[2], pirate_boat[3], explosion[0],explosion[1],explosion[2],explosion[3],
                explosion[4],explosion[5],explosion[6],explosion[7],explosion[8],
                explosion[9],explosion[10],explosion[11],explosion[12],explosion[13],
                explosion[14]);
        if (type == 2) object = new Shark(this, width, height, vX, vY, shark_image[0],shark_image[1],shark_image[2],shark_image[3],shark_image[4],shark_image[5],shark_image[6],shark_image[7],shark_image[8], explosion[0],explosion[1],explosion[2],explosion[3],
                explosion[4],explosion[5],explosion[6],explosion[7],explosion[8],
                explosion[9],explosion[10],explosion[11],explosion[12],explosion[13],
                explosion[14]);
        if (type == 3) object = new Plane(this, width, height, vX, vY, plane_image[0],plane_image[1],plane_image[2],plane_image[3],plane_image[4],plane_image[5],plane_image[6],plane_image[7],plane_image[8]);
        return object;
    }
    
    /**
     * Method to add to objects to the array of objects
     * @param n It is the variable that decide is if a coin or a gem to add to the array;
     */
    public void createDisplayedTreasure(int n) {
        if (n==1)display.addToObjectToAdd(coin);
        else display.addToObjectToAdd(gemstone);
    }
    
    /**
     * Method to add the enemy into the array of objects
     * @param object It is the enemy
     */
    public void createDisplayedObject(ObjectGame object) {
        display.addToObjectToAdd(object);
    }
    
    /**
     * Method to add the sprite of every object to the game to display in the screen
     * @param n It is the variable that decide if is a coin or a gem
     */
    public void addNewTreasureNodes(int n) {
        if (n ==1) display.addToSpriteToAdd(coin.spriteFrame);
        else display.addToSpriteToAdd(gemstone.spriteFrame);   
    }
    
    /**
     * Method to add the sprite of an object into the game to display in the screen
     * @param spriteFrame It is the sprite of the object
     */
    public void addNewGameObjectNodes(ImageView spriteFrame) {
        display.addToSpriteToAdd(spriteFrame);    
    }
    
    /**
     * Method to add objects which the game starts
     */
    public void addGameObjectNodes() {
        paneRoot.getChildren().addAll(skyLine, iHero.spriteFrame);
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
    private void createSceneEventHandling(Scene scene) {
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case UP: Hero.setUp(true); break;
                case DOWN: Hero.setDown(true); break;
                case LEFT: Hero.setLeft(true); break;
                case RIGHT: Hero.setRight(true); break;
                case SPACE: Hero.setSpace(true); break;
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
                case SPACE: Hero.setSpace(false); break;
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
    private void playMusic(){
        gameMusicPlayer = new MediaPlayer(new Media(this.getClass().getResource(GAME_MUSIC_PATH).toExternalForm()));
        gameMusicPlayer.setOnEndOfMedia(() -> {
            gameMusicPlayer.setVolume(0.5);
            gameMusicPlayer.seek(Duration.ZERO);
            gameMusicPlayer.play();
        });
    }

    public Pane getPaneRoot() {
        return paneRoot;
    }

    public void setPaneRoot(Pane paneRoot) {
        this.paneRoot = paneRoot;
    }

    public static Timeline getTimeline() {
        return timeline;
    }

    public static void setTimeline(Timeline timeline) {
        BearGame.timeline = timeline;
    }
    
    /**
     * Method to stop the variables of the game when the Hero dies
     */
    public void bearAlive() {
        Configuration conf = new Configuration();
        Score.loadFromXML();
        boolean maxScore = Score.verifyMaxScore(Slidding.gameScore);
        Collision.setCollisionAnimation(false);
        playGame.stop();
        paneRoot.getChildren().clear();
        display.resetDisplayed_Object();
        gameMusicPlayer.stop();
        timeline.stop();
        primary.close();
        if(maxScore) window.setScene(conf.gameOver(scene, window,true));
        else {
            Slidding.gameScore = 0;
            window.setScene(conf.gameOver(scene, window,false));
        }
        //enemies.clear();
    }
    
    /**
     * Method to get the arraylist of the Display's game
     * @return The arrayList of the display of enemies
     */
    public DisplayObject getDisplay() {
        return display;
    }
    
    public void AddSpriteToPane(List<ImageView> spriteFrame) {
        paneRoot.getChildren().addAll(spriteFrame);
    }
    
}
