package controller;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.stage.Stage;
import model.*;

import view.*;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class BossGameController {
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
    private  boolean littleBossUsed = false;
    private double littleBossTimer = 0.0;
    private int buffSize = 0;

    private Laser laser;
    private Laser laserIndicator;
    private LaserView laserView;
    private LaserView laserIndicatorView;

    private LittleBoss littleBoss;
    private LittleBossView littleBossView;

    private Scene gameScene;
    private Stage gameStage;

    private HashMap <Buff, BuffView> hashMap = new HashMap<>();

    private Map map;
    private MapView mapView;

    private int level;
    private BossController bossController;
    public BossGameController(int level) throws FileNotFoundException {
        this.level = level;
        initializeGame();
        Image background = new Image( new FileInputStream( "resources/purple.png"));
        BackgroundImage backgroundImage = new BackgroundImage( background,null,null,null,null);
        mapView.setBackground( new Background(backgroundImage));
        gameScene = new Scene( mapView, MAX_WIDTH, MAX_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene( gameScene);
        bossController = new BossController( level, map, mapView);
        initializeListeners ();
        initializeTimer();

    }
    public Stage getGameStage () {
        return gameStage;
    }
    private void initializeGame () throws FileNotFoundException {
        map = new Map( level);

        mapView = new MapView();
        mapView.addSpaceCraftView( map.getSpaceCraft().getLocation().getPositionX(), map.getSpaceCraft().getLocation().getPositionY(), map.getSpaceCraft().getWidth(), map.getSpaceCraft().getHeight());
        mapView.addBossOneView( map.getBoss().getLocation().getPositionX(), map.getBoss().getLocation().getPositionY(), map.getBoss().getWidth(),map.getBoss().getHeight() );
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
                animationLittleBosses();

            }
        };
        timer.start();
    }
    private void animationSpaceCraft () throws FileNotFoundException {

        double [] boundArray = { moveright, moveleft, moveup, movedown};
        map.checkBoundry( boundArray, map.getSpaceCraft());

       double xDirection = boundArray [0] + boundArray [1]; // moveright + moveleft
       double yDirection = boundArray [2] + boundArray[3];  // moveup + movedown
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

      bossController.behave();

    }
    private void animationBullets () throws FileNotFoundException {

        for ( int i = 0; i < map.getBullets().size(); i++) {
            Bullet b = map.getBullets().get(i);
            if ( b.getSourceID() == map.getSpaceCraft().getID()) {
                b.move( 1.0,0.0);
                mapView.refreshBullet( mapView.getBulletViews().get(i), b.getLocation().getPositionX(), b.getLocation().getPositionY());
            }
           else if ( b.getSourceID() == map.getBoss().getID()) {
                b.move( -1.0,0.0);
                mapView.refreshBullet(mapView.getBulletViews().get(i), b.getLocation().getPositionX(),b.getLocation().getPositionY());
            }
            map.checkCollisions( b);
            if ( b.getLocation().getPositionX() >= MAX_WIDTH || b.getLocation().getPositionX() <= 0 || b.isDead()) {
                map.getBullets().remove(i);
                mapView.removeBulletView( mapView.getBulletViews().get(i));
                i--;
            }

           if ( map.getBoss().getHealthPoint() <= 0)
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

    private void animationLittleBosses() {
        for( int i = 0; i < map.getLittleBosses().size(); i++) {
            LittleBoss littleBoss = map.getLittleBosses().get(i);
            littleBoss.move( littleBoss.getXDir(), littleBoss.getYDir());
            mapView.refreshLittleBoss(mapView.getLittleBossViews().get(i), littleBoss.getLocation().getPositionX(), littleBoss.getLocation().getPositionY());
            map.checkCollisions(littleBoss);

            if( littleBoss.getHitNumber() == 4) {
                map.getLittleBosses().remove(i);
                mapView.removeLittleBossView( mapView.getLittleBossViews().get(i));
                i--;
            }
            else if ( littleBoss.getLocation().getPositionX() >= MAX_WIDTH || littleBoss.getLocation().getPositionX() < 0 ) {
                littleBoss.setHitNumber( littleBoss.getHitNumber() + 1);
                littleBoss.setXDir( - littleBoss.getXDir());
                littleBoss.setYDir( littleBoss.getYDir());
            }
            else if( littleBoss.getLocation().getPositionY() >= MAX_HEIGHT || littleBoss.getLocation().getPositionY() < 0  ){
                littleBoss.setHitNumber( littleBoss.getHitNumber() + 1);
                littleBoss.setXDir( littleBoss.getXDir());
                littleBoss.setYDir( - littleBoss.getYDir());
            }

            if ( map.getBoss().getHealthPoint() <= 0)
                mapView.getChildren().remove( mapView.getBossOneView());
            if ( map.getSpaceCraft().getHealthPoint() <= 0)
                mapView.getChildren().remove( mapView.getSpaceCraftView());
        }
    }

}



      /*     if ( level == 1) {
               if (laserTimer != 0.0 && laserTimer <= 1.0) {
                   if (laserTimer > 0.70 && laserUsed == false) {
                       map.getLasers().remove(laserIndicator);
                       mapView.removeLaserView(laserIndicatorView);

                       laser = ((BossOne) map.getBoss()).sendLaser();
                       map.getLasers().add(laser);
                       laserView = new LaserView(laser.getLocation().getPositionX(), laser.getLocation().getPositionY(), laser.getWidth(), laser.getHeight());
                       mapView.addLaserView(laserView);
                       laserUsed = true;
                   }
                   if (laserTimer + 0.016 >= 1.0) {
                       mapView.removeLaserView(laserView);
                       map.getLasers().remove(laser);
                       System.out.println("BossHP: " + map.getBoss().getHealthPoint());
                       System.out.println("SpaceCraft HP: " + map.getSpaceCraft().getHealthPoint());
                   }

                   if (laser != null) {
                       map.checkCollisions(laser);
                       if (map.getSpaceCraft().getHealthPoint() <= 0) {
                           mapView.getChildren().remove(mapView.getSpaceCraftView());
                       }
                   }

                   laserTimer = laserTimer + 0.016;
               } else {
                   laser = null;
                   laserUsed = false;
                   laserIndicatorUsed = Math.random() < ((BossOne) map.getBoss()).getLASER_FREQ();
                   laserTimer = laserIndicatorUsed ? 0.016 : 0.0;
                   if (laserIndicatorUsed) {
                       laserIndicator = ((BossOne) map.getBoss()).sendLaserIndicator();
                       map.getLasers().add(laserIndicator);
                       laserIndicatorView = new LaserView(laserIndicator.getLocation().getPositionX(), laserIndicator.getLocation().getPositionY(), laserIndicator.getWidth(), laserIndicator.getHeight(), Color.CYAN);
                       mapView.addLaserView(laserIndicatorView);

                   }
               }
           }*/


          /*   if ( map.useSpecialAbility( map.getBoss())) {

            for (int i = 0; i < map.getMarkers().size(); i++) {
                double x = map.getMarkers().get(i).getLocation().getPositionX();
                double y = map.getMarkers().get(i).getLocation().getPositionY();
                double rad = map.getMarkers().get(i).getRadius();
                mapView.refreshCrossView(x, y, rad, rad);
            }
        }*/
