/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beargame;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
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
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import static javax.swing.Spring.width;

/**
 *
 * @author Anto
 */
public class Slidding {
    
    double SUNX = 100;
    double SUNY = 300;
    
    Rectangle sky = new Rectangle(BearGame.WIDTH_PIXELS, 400);
    Group sun = new Group();
    Circle sunCircle = new Circle(SUNX, SUNY, 60);
    
    public void sky() {
        sky.setFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, new Stop(0.0, Color.LIGHTBLUE), new Stop(0.7, Color.LIGHTYELLOW), new Stop(1.0, Color.YELLOW)));
        sunCircle.setFill(Color.YELLOW);
        sun.getChildren().add(sunCircle);
         ArcBuilder ab =  ArcBuilder.create()
                    .centerX(SUNX)
                    .centerY(SUNY)
                    .radiusX(500)
                    .radiusY(500)
                    .length(360 / 24).type(ArcType.ROUND).fill(Color.YELLOW).opacity(0.3);
        for (int i = 0; i < 12; i++) {
            Arc a = ab.startAngle(2 * i * (360 / 24)).build();
            sun.getChildren().add(a);
        }
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(100 * 360), sun);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(360);        
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.setCycleCount(Timeline.INDEFINITE);
        rotateTransition.play();
        
        Cloud[] clouds = new Cloud[]{new Cloud(100, 100), new Cloud(150, 20), new Cloud(220, 150), new Cloud(260, 200), new Cloud(310, 40), new Cloud(390, 150), new Cloud(450, 30), new Cloud(550, 100)};
        Sliding cloudSlider = new Sliding(clouds, BearGame.WIDTH_PIXELS);
        //root.getChildren().addAll(cloudSlider);
    }
    
    class Cloud extends Ellipse {

        public Cloud(double centerX, double centerY) {
            super(0, 0, 50, 25);
            this.setTranslateX(centerX);
            this.setTranslateY(centerY);
            this.setFill(Color.WHITESMOKE);
            this.setOpacity(0.5);
        }
    }
    
    class Sliding extends Group {
    
        final Node[] content;
        Timeline anim = new Timeline();

        public Sliding(final Node[] content,final int width) {
            this.content = content;
            this.getChildren().addAll(content);
            anim.setCycleCount(Timeline.INDEFINITE);
            EventHandler onFinished = new EventHandler() {
                public void handle(ActionEvent t) {
                    for(Node n : content) {
                        n.setTranslateX(n.getTranslateX() - 1.0);
                        if(n.getLayoutX() + n.getTranslateX() + n.getBoundsInLocal().getWidth() <= 0) {
                            n.setTranslateX(width - n.getLayoutX());
                        }
                    }
                }

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
