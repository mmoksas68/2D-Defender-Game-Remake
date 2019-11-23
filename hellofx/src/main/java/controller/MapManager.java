package controller;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.*;

import view.*;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class MapManager {
    private final double MAX_WIDTH = 800;
    private final double MAX_HEIGHT = 600;
    private final double MOVE_OFFSET = 10;
    private final double WAIT_TIME_LASER = 3.0;

    private double  moveleft = 0, moveright = 0;
    private double  moveup = 0, movedown = 0;
    private boolean isShot = false;

    private double  bossYdir = 1.0;
    private double bossMoveUp = 0, bossMoveDown = 0;
    private  boolean laserUsed = false;
    private boolean laserIndicatorUsed = false;
    private double laserTimer = 0.0;
    private int buffSize = 0;

    private Laser laser;
    private Laser laserIndicator;
    private LaserView laserView;
    private LaserView laserIndicatorView;

    private Scene gameScene;
    private Stage gameStage;

    private HashMap <Buff, BuffView> hashMap = new HashMap<>();

    private Map map;
    private MapView mapView;

    public MapManager () throws FileNotFoundException {
        initializeGame();
        Image background = new Image( new FileInputStream( "resources/black.png"));
        BackgroundImage backgroundImage = new BackgroundImage( background,null,null,null,null);
        mapView.setBackground( new Background(backgroundImage));
        gameScene = new Scene( mapView, MAX_WIDTH, MAX_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene( gameScene);
        initializeListeners ();
        initializeTimer();

    }
    public Stage getGameStage () {
        return gameStage;
    }
    private void initializeGame () throws FileNotFoundException {
        map = new Map();

        mapView = new MapView();
        mapView.addSpaceCraftView( map.getSpaceCraft().getLocation().getPositionX(), map.getSpaceCraft().getLocation().getPositionY(), map.getSpaceCraft().getWidth(), map.getSpaceCraft().getHeight());
        mapView.addBossOneView( map.getBossOne().getLocation().getPositionX(), map.getBossOne().getLocation().getPositionY(), map.getBossOne().getWidth(),map.getBossOne().getHeight() );
    }
    private void initializeListeners () {
        gameScene.setOnKeyPressed( e-> {
            switch ( e.getCode()) {
                case UP:
                    moveup = -1;
                    break;
                case DOWN:
                    movedown = 1;
                    break;
                case RIGHT:
                    moveright = 1;
                    break;
                case LEFT:
                    moveleft = -1;
                    break;
                case SPACE:
                    isShot = true;
                    break;
                default:
            }
        });
        gameScene.setOnKeyReleased( e-> {
            switch ( e.getCode()) {
                case UP:
                    moveup = 0;
                    break;
                case DOWN:
                    movedown = 0;
                    break;
                case RIGHT:
                    moveright = 0;
                    break;
                case LEFT:
                    moveleft = 0;
                    break;
                case SPACE:
                    isShot = false;
                    break;
                default:
            }
        });
    }

    private void initializeTimer () {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                try {
                    animationSpaceCraft();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    animationBullets();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        };
        timer.start();
    }
    private void animationSpaceCraft () throws FileNotFoundException {
       /* if ( map.getSpaceCraft().getX() >= MAX_WIDTH / 2 - 5*MOVE_OFFSET) moveright = 0;
        if ( map.getSpaceCraft().getX() <= 2*MOVE_OFFSET) moveleft = 0;
        if ( map.getSpaceCraft().getY() <= 2*MOVE_OFFSET) moveup = 0;
        if ( map.getSpaceCraft().getY() >= MAX_HEIGHT - map.getSpaceCraft().getHeight() - 2*MOVE_OFFSET) movedown = 0;*/
       double xDirection = moveleft + moveright;
       double yDirection = movedown + moveup;
        double multiplier = 1 / Math.sqrt( Math.pow( xDirection,2) + Math.pow( yDirection, 2));
        if ( xDirection != 0 || yDirection != 0) {
            map.getSpaceCraft().move(xDirection * multiplier, yDirection * multiplier);
            mapView.refreshSpaceCraftView( map.getSpaceCraft().getLocation().getPositionX(), map.getSpaceCraft().getLocation().getPositionY());
        }

        if ( isShot) {
            Bullet b = map.getSpaceCraft().fireBullet();
            map.getBullets().add( b);
            BulletView bulletView = new BulletView( map.getSpaceCraft().getLocation().getPositionX(),map.getSpaceCraft().getLocation().getPositionY(),b.getWidth(), b.getHeight());
            mapView.addBulletView( bulletView);
        }

           /* if (Math.random() < bossXdirProb) {
                bossMoveRight = (map.getBossOne().getX() + map.getBossOne().getWidth() + 2 * MOVE_OFFSET < MAX_WIDTH) ? 1 : 0;
                bossMoveLeft = 0;
                System.out.println( "Moved right");
            } else {
                bossMoveLeft = (map.getBossOne().getX() - MOVE_OFFSET >= (MAX_WIDTH - (MAX_WIDTH / 4))) ? -1 : 0;
                bossMoveRight = 0;
                System.out.println( "Moved left");
            }*/

        if ( laserTimer != 0.0 && laserTimer <= 1.0) {
            if ( laserTimer > 0.35 && laserUsed == false) {
                map.getLasers().remove( laserIndicator);
                mapView.removeLaserView( laserIndicatorView);

                 laser = map.getBossOne().sendLaser();
                 map.getLasers().add( laser);
                 laserView = new LaserView( laser.getLocation().getPositionX(),laser.getLocation().getPositionY(),laser.getWidth(),laser.getHeight(), Color.PINK);
                 mapView.addLaserView( laserView);
                laserUsed = true;
            }
            if ( laserTimer +0.016 >= 1.0) {
                mapView.removeLaserView(laserView);
                map.getLasers().remove( laser);
            }

            if ( laser != null) {
                map.checkCollisions(laser);
                if ( map.getSpaceCraft().getHealthPoint() <= 0) {
                    mapView.getChildren().remove(mapView.getSpaceCraftView());
                }
            }



            laserTimer = laserTimer + 0.016;
        }
        else {
            laser = null;
            laserUsed = false;
            laserIndicatorUsed = Math.random() < map.getBossOne().getLASER_FREQ();
            laserTimer = laserIndicatorUsed ? 0.016 : 0.0;
            if ( laserIndicatorUsed) {
                laserIndicator = map.getBossOne().sendLaserIndicator();
                map.getLasers().add( laserIndicator);
                laserIndicatorView = new LaserView( laserIndicator.getLocation().getPositionX(),laserIndicator.getLocation().getPositionY(),laserIndicator.getWidth(),laserIndicator.getHeight(),Color.CYAN);
                mapView.addLaserView( laserIndicatorView);

            }
            else {
                if (bossYdir == 1.0) {
                    bossMoveUp = ((map.getBossOne().getLocation().getPositionY() - (2 * MOVE_OFFSET)) >= 0) ? -1 : 0;
                    bossYdir = map.getBossOne().getLocation().getPositionY() <= MAX_HEIGHT / 5 ? 0.0 : 1.0;
                    map.getBossOne().move(0.0, bossMoveUp);
                    bossMoveDown = 0;
                } else {
                    bossMoveDown = ((map.getBossOne().getLocation().getPositionY() + map.getBossOne().getHeight() + (2 * MOVE_OFFSET)) <= MAX_HEIGHT) ? 1 : 0;
                    bossYdir = (map.getBossOne().getLocation().getPositionY() + map.getBossOne().getHeight() / 2 >= MAX_HEIGHT - MAX_HEIGHT / 4) ? 1.0 : 0.0;
                    bossMoveUp = 0;
                    map.getBossOne().move(0.0, bossMoveDown);
                }
                mapView.refreshBossOne(map.getBossOne().getLocation().getPositionX(), map.getBossOne().getLocation().getPositionY());
            }
        }



        if ( Math.random() < map.getBossOne().getGunFrequency()) {
            Bullet b = map.getBossOne().sendBullet();
            map.getBullets().add( b);
            BulletView bulletView = new BulletView( map.getBossOne().getLocation().getPositionX(),map.getBossOne().getLocation().getPositionY(),b.getWidth(), b.getHeight());
            mapView.addBulletView( bulletView);
        }


    }
    private void animationBullets () throws FileNotFoundException {

        for ( int i = 0; i < map.getBullets().size(); i++) {
            Bullet b = map.getBullets().get(i);
            if ( b.getSourceID() == map.getSpaceCraft().getID()) {
                b.move( 1.0,0.0);
                mapView.refreshBullet( mapView.getBulletViews().get(i), b.getLocation().getPositionX(), b.getLocation().getPositionY());
            }
           else if ( b.getSourceID() == map.getBossOne().getID()) {
                b.move( -1.0,0.0);
                mapView.refreshBullet(mapView.getBulletViews().get(i), b.getLocation().getPositionX(),b.getLocation().getPositionY());
            }
            map.checkCollisions( b);
            if ( b.getLocation().getPositionX() >= MAX_WIDTH || b.getLocation().getPositionX() <= 0 || b.isDead()) {
                map.getBullets().remove(i);
                mapView.removeBulletView( mapView.getBulletViews().get(i));
                i--;
            }

           if ( map.getBossOne().getHealthPoint() <= 0)
                mapView.getChildren().remove( mapView.getBossOneView());
            if ( map.getSpaceCraft().getHealthPoint() <= 0)
                mapView.getChildren().remove( mapView.getSpaceCraftView());
        }


        for (java.util.Map.Entry<Buff, Boolean> entry : map.getBuffs().entrySet()) {

            Buff buff = entry.getKey();
            if ( entry.getValue() == false) {
                BuffView buffView = new BuffView( buff.getName(),buff.getLocation().getPositionX(), buff.getLocation().getPositionY(), buff.getWidth(), buff.getHeight());
                mapView.addBuffView( buffView);
                hashMap.put( buff, buffView);
                entry.setValue( true);
            }
            buff.move( -1.0,0.0);
            mapView.refreshBuffView( hashMap.get( buff), buff.getLocation().getPositionX(),buff.getLocation().getPositionY());
        }
    }

}

