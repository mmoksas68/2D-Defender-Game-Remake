package org.openjfx.view;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.openjfx.utilization.ModelToView;
import org.openjfx.view.entities.BulletView;
import org.openjfx.view.entities.EnemyView;
import org.openjfx.view.entities.SpacecraftView;

import java.util.HashMap;
import java.util.Map;

public class MapView extends Pane {


    private java.util.Map<Long, Node> currentNodes = new HashMap<Long, Node>();

    public MapView() {
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
                enemy.setWidth(modelToView.getHitboxWidth());
                enemy.setHeight(modelToView.getHitboxHeight());
            }

        } else {
            enemy = new EnemyView(modelToView.getLocationX() - modelToView.getCurrentViewLeft(),
                                    modelToView.getLocationY(), modelToView.getHitboxWidth(),
                                    modelToView.getHitboxHeight(), Color.RED);
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
                bullet.setWidth(modelToView.getHitboxWidth());
                bullet.setWidth(modelToView.getHitboxHeight());
            }

        } else {
            bullet = new BulletView(modelToView.getLocationX() - modelToView.getCurrentViewLeft(),
                    modelToView.getLocationY(), modelToView.getHitboxWidth(),
                    modelToView.getHitboxHeight(), Color.BLACK);
            currentNodes.put(modelToView.getID(), bullet);
            getChildren().add(bullet);
        }
    }

    public void refreshSpacecraft(ModelToView modelToView){
        SpacecraftView spacecraft;

        if(currentNodes.containsKey(modelToView.getID()) ){
            spacecraft = (SpacecraftView) currentNodes.get(modelToView.getID());
            spacecraft.setTranslateX(modelToView.getLocationX() - modelToView.getCurrentViewLeft());
            spacecraft.setTranslateY(modelToView.getLocationY());
            spacecraft.setWidth(modelToView.getHitboxWidth());
            spacecraft.setHeight(modelToView.getHitboxHeight());
        } else {
            spacecraft = new SpacecraftView(modelToView.getLocationX() - modelToView.getCurrentViewLeft(),
                    modelToView.getLocationY(), modelToView.getHitboxWidth(),
                    modelToView.getHitboxHeight(), Color.CYAN);
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

}
