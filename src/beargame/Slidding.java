package beargame;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcBuilder;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Class of the game's sky and the score
 * @author Antonio Jesús Ibáñez Garcia
 */
public class Slidding {
    static int gameScore = 0;
    static Text scoreText;
    protected Timeline timeline;
    double SUNX = 25;
    double SUNY = 75;
    static double HEIGTH_SKY = 300;
    private Group skyBack = new Group();
    Rectangle sky = new Rectangle(Menu.WIDTH_PIXELS, HEIGTH_SKY);
    Group sun = new Group();
    Circle sunCircle = new Circle(SUNX, SUNY, 60);
    
    /**
     * Method to create the score of the game
     */
    public void createScore() {
        scoreText = new Text();
        scoreText.setText("SCORE: " + String.valueOf(gameScore));
        scoreText.setLayoutY(25);
        scoreText.setLayoutX(650);
        Font scoreFont = new Font("Verdana", 20);
        scoreText.setFont(scoreFont);
        scoreText.setFill(Color.BLACK);
        scoreText.toFront();
    }
    
    /**
     * Method to create the sky for the game
     * @return The sky
     */
    public Group sky() {
        sky.setFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, new Stop(0.0, Color.LIGHTBLUE), new Stop(0.7, Color.LIGHTYELLOW), new Stop(1.0, Color.YELLOW)));
        skyBack.getChildren().add(sky);
        sunCircle.setFill(Color.YELLOW);
        sun.getChildren().add(sunCircle);
         ArcBuilder ab =  ArcBuilder.create()
                    .centerX(SUNX)
                    .centerY(SUNY)
                    .radiusX(150)
                    .radiusY(150)
                    .length(360 / 24).type(ArcType.ROUND).fill(Color.YELLOW).opacity(0.3);
        for (int i = 0; i < 12; i++) {
            Arc a = ab.startAngle(2 * i * (360 / 24)).build();
            sun.getChildren().add(a);
        }
        skyBack.getChildren().add(sun);
        
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(100 * 360), sun);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(360);        
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.setCycleCount(Timeline.INDEFINITE);
        rotateTransition.play();
        
        Cloud[] clouds = new Cloud[]{new Cloud(100, 100), new Cloud(150, 20), new Cloud(220, 150), new Cloud(260, 100), new Cloud(370, 150), new Cloud(700, 150), new Cloud(450, 30), new Cloud(600, 100)};
        Sliding cloudSlider = new Sliding(clouds, Menu.WIDTH_PIXELS);
        skyBack.getChildren().addAll(cloudSlider);
        
        Rectangle ground = new Rectangle(Menu.WIDTH_PIXELS, 100);
        ground.setTranslateY(200);
        ground.setFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, new Stop(0.2, Color.OLIVE), new Stop(0.1, Color.DARKOLIVEGREEN)));
        skyBack.getChildren().add(ground);
        Tree[] trees = new Tree[]{new Tree(20, 220), new Tree(80, 180), new Tree(120, 230), new Tree(140, 180), new Tree(180, 210), new Tree(220, 220),
            new Tree(260, 180), new Tree(280, 220), new Tree(300, 200), new Tree(400, 220), new Tree(500, 180), new Tree(500, 220)
        };
        Sliding forest = new Sliding(trees, Menu.WIDTH_PIXELS);
        skyBack.getChildren().add(forest);
        createScore();
        skyBack.getChildren().add(scoreText);
        return skyBack;
    }
    
    /**
     * Class to create a cloud
     */
    class Cloud extends Ellipse {
        /**
         * Constructor of the class
         * @param centerX It is the position in the X axis
         * @param centerY It is the position in the Y axis
         */
        public Cloud(double centerX, double centerY) {
            super(0, 0, 50, 25);
            this.setTranslateX(centerX);
            this.setTranslateY(centerY);
            this.setFill(Color.WHITESMOKE);
            this.setOpacity(0.7);
        }
    }
    
    /**
     * Class to create a tree
     */
    class Tree extends Polygon {
        
        /**
         * Constructor of the class
         * @param x It is the position in the X axis
         * @param y It is the position in the Y axis
         */
        public Tree(double x, double y) {
            super(0, 0, 10, 30, -10, 30);
            this.setTranslateX(x);
            this.setTranslateY(y);
        }
    }
    
    /**
     * Class to create the movement
     */
    class Sliding extends Group {
        final Node[] content;
        Timeline anim = new Timeline();
        
        /**
         * Constructor of the class
         * @param content It is the node to move
         * @param width It is the width of the screen
         */
        public Sliding(final Node[] content,final int width) {
            this.content = content;
            this.getChildren().addAll(content);
            anim.setCycleCount(Timeline.INDEFINITE);
            EventHandler onFinished = new EventHandler() {
                @Override
                public void handle(Event event) {
                    for(Node n : content) {
                        n.setTranslateX(n.getTranslateX() - 1.0);
                        if(n.getLayoutX() + n.getTranslateX() + n.getBoundsInLocal().getWidth() <= 0) {
                            n.setTranslateX(width - n.getLayoutX());
                        }
                    }
                }
            };
            KeyValue keyValueX = new KeyValue(Sliding.this.rotateProperty(),0);
            KeyFrame keyFrame = new KeyFrame(new Duration(100), onFinished , keyValueX); //, keyValueY);
            anim.getKeyFrames().add(keyFrame);
            anim.play();
        }
    }
}
