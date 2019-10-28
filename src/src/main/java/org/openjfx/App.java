package org.openjfx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.openjfx.controller.Game;


/**
 * JavaFX App
 */
public class App extends Application {
    Game game;
/*
    private Pane root = new Pane();
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean accelerate = false;

    private static int t = 0;
    private Parent createContent(){
        root.setPrefSize(600,800);
        return root;
    }
*/

    @Override
    public void start(Stage stage) {
        game = new Game();
/*        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");

        var rectangle = new Rect(300, 700, 50,50, Color.AQUA);

        root.getChildren().add(rectangle);

        var scene = new Scene(createContent(), 640, 880);

        scene.setOnKeyPressed( e -> {
            accelerate = true;

            switch (e.getCode()){
                case UP:
                    upPressed = true;
                    break;
                case DOWN:
                    downPressed = true;
                    break;
                case LEFT:
                    leftPressed = true;
                    break;
                case RIGHT:
                    rightPressed = true;
                    break;
            }
        });

        scene.setOnKeyReleased( e -> {
            t = 0;
            accelerate = false;
            switch (e.getCode()){

                case UP:
                    upPressed = false;
                    break;
                case DOWN:
                    downPressed = false;
                    break;
                case LEFT:
                    leftPressed = false;
                    break;
                case RIGHT:
                    rightPressed = false;
                    break;
            }
        });

        new AnimationTimer(){

            @Override
            public void handle(long l) {
                if(t < 50 && accelerate)
                    t++;
                if(upPressed)
                    rectangle.moveUp();
                if(downPressed)
                    rectangle.moveDown();
                if(leftPressed)
                    rectangle.moveLeft();
                if(rightPressed)
                    rectangle.moveRight();
            }
        }.start();
        */
        stage.setScene(game.getScene());
        stage.show();
    }

   /* private static class Rect extends Rectangle{
        boolean dead = false;
        static long currentID = 1;
        final long ID;

        Rect(int x, int y, int w, int h, Color color){
            super(w,h,color);
            ID = currentID++;
            setTranslateX(x);
            setTranslateY(y);
        }

        void moveLeft(){
            setTranslateX(getTranslateX() - t/10);
        }

        void moveRight(){
            setTranslateX(getTranslateX() + t/10);
        }

        void moveUp(){
            setTranslateY(getTranslateY() - t/10);
        }

        void moveDown(){
            setTranslateY(getTranslateY() + t/10);
        }
    }*/

    public static void main(String[] args) {
        launch();
    }

}