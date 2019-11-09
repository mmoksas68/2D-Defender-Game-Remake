package org.openjfx.view;

import javafx.geometry.Insets;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.openjfx.utilization.ModelToView;
import org.openjfx.utilization.ModelToViewSpaceCraft;
import org.openjfx.view.entities.BulletView;
import org.openjfx.view.entities.EnemyView;
import org.openjfx.view.entities.SpacecraftView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class MapView extends Pane {

    private static BackgroundImage image;

    static {
        try {
            image = new BackgroundImage(new Image(new FileInputStream("images/background.png")), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private java.util.Map<Long, Node> currentNodes = new HashMap<Long, Node>();

    public MapView(double widthSize, double heightSize) {
        setPrefSize(widthSize, heightSize);
    }

    public MapView(Node... nodes) {
        super(nodes);
    }

    public void refreshEnemy(ModelToView modelToView){
        EnemyView enemy;
        if(currentNodes.containsKey(modelToView.getID())){
            enemy = (EnemyView) currentNodes.get(modelToView.getID());
            if(modelToView.isDead()){
                getChildren().remove(enemy);
            }else {
                enemy.setTranslateX(modelToView.getLocationX() - modelToView.getCurrentViewLeft());
                enemy.setTranslateY(modelToView.getLocationY());
                enemy.setFitWidth(modelToView.getHitboxWidth());
                enemy.setFitHeight(modelToView.getHitboxHeight());
                if(enemy.getTranslateX() < -500 || enemy.getTranslateX() > 2300)
                    enemy.setVisible(false);
                else
                    enemy.setVisible(true);
            }

        } else {
            enemy = new EnemyView();
            enemy.setTranslateX(modelToView.getLocationX() - modelToView.getCurrentViewLeft());
            enemy.setTranslateY(modelToView.getLocationY());
            enemy.setFitWidth(modelToView.getHitboxWidth());
            enemy.setFitHeight(modelToView.getHitboxHeight());
            enemy.setCacheHint(CacheHint.SPEED);
            enemy.setCache(true);
            enemy.setSmooth(true);
            currentNodes.put(modelToView.getID(), enemy);
            getChildren().add(enemy);
        }
    }

    public void refreshBullet(ModelToView modelToView){
        BulletView bullet;
        if(currentNodes.containsKey(modelToView.getID())){
            bullet = (BulletView) currentNodes.get(modelToView.getID());
            if(modelToView.isDead()){
                getChildren().remove(bullet);
            }else {
                bullet.setTranslateX(modelToView.getLocationX() - modelToView.getCurrentViewLeft());
                bullet.setTranslateY(modelToView.getLocationY());
                bullet.setFitHeight(modelToView.getHitboxHeight());
                bullet.setFitWidth(modelToView.getHitboxWidth());
                if(bullet.getTranslateX() < -500 || bullet.getTranslateX() > 2300)
                    bullet.setVisible(false);
                else
                    bullet.setVisible(true);

            }

        } else {
            bullet = new BulletView();
            bullet.setTranslateX(modelToView.getLocationX() - modelToView.getCurrentViewLeft());
            bullet.setTranslateY(modelToView.getLocationY());
            bullet.setFitHeight(modelToView.getHitboxHeight());
            bullet.setFitWidth(modelToView.getHitboxWidth());
            bullet.setCacheHint(CacheHint.SPEED);
            bullet.setCache(true);
            currentNodes.put(modelToView.getID(), bullet);
            getChildren().add(bullet);
        }
    }

    public void refreshSpacecraft(ModelToViewSpaceCraft modelToView){
        SpacecraftView spacecraft;

        if(currentNodes.containsKey(modelToView.getID()) ){
            spacecraft = (SpacecraftView) currentNodes.get(modelToView.getID());
            spacecraft.setTranslateX(modelToView.getLocationX() - modelToView.getCurrentViewLeft());
            spacecraft.setTranslateY(modelToView.getLocationY());
            spacecraft.setFitHeight(modelToView.getHitboxHeight());
            spacecraft.setFitWidth(modelToView.getHitboxWidth());
            if(modelToView.isDirectionLeft())
                spacecraft.setRotate(-90);
            else
                spacecraft.setRotate(90);
        } else {
            spacecraft = new SpacecraftView();
            spacecraft.setTranslateX(modelToView.getLocationX() - modelToView.getCurrentViewLeft());
            spacecraft.setTranslateY(modelToView.getLocationY());
            spacecraft.setFitHeight(modelToView.getHitboxHeight());
            spacecraft.setFitWidth(modelToView.getHitboxWidth());
            spacecraft.setCacheHint(CacheHint.SPEED);
            spacecraft.setCache(true);
            spacecraft.setSmooth(true);
            getChildren().add(spacecraft);
            currentNodes.put(modelToView.getID(),spacecraft);
        }
    }

    public Map<Long, Node> getCurrentNodes() {
        return currentNodes;
    }

    public void setCurrentNodes(Map<Long, Node> currentNodes) {
        this.currentNodes = currentNodes;
    }

    @Override
    public void setPrefSize(double v, double v1) {
        super.setPrefSize(v, v1);
        Background background = new Background(image);
        this.setBackground(background);
    }
}
