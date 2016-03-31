/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beargame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Anto
 */
public class BearGame extends Application {
    static final int HEIGHT_PIXELS = 480;
    static final int WIDTH_PIXELS = 640;
    boolean up, down, left, right;
    Scene scene;
    StackPane root;
    static HBox buttonContainer = new HBox();
    GamePlayLoop playGame = new GamePlayLoop();
    @Override
    public void start(Stage primaryStage) {
        
        root = new StackPane();
        scene = new Scene(root, WIDTH_PIXELS, HEIGHT_PIXELS, Color.WHITE);
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case UP: up = true; break;
                case DOWN: down = true; break;
                case LEFT: left = true; break;
                case RIGHT: right = true; break;
                case W: up = true; break;
                case S: down = true; break;
                case A: left = true; break;
                case D: right = true; break;
            }
        });
        scene.setOnKeyReleased((KeyEvent event) -> {
            switch (event.getCode()) {
                case UP: up = false; break;
                case DOWN: down = false; break;
                case LEFT: left = false; break;
                case RIGHT: right = false; break;
                case W: up = false; break;
                case S: down = false; break;
                case A: left = false; break;
                case D: right = false; break;
            }
        });
        
        
        
        playGame.start();
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
