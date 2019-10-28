package org.openjfx.view;


import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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

    public void refreshEnemy(int x, int y, int w, int h, long ID){
        EnemyView enemy;
        if(currentNodes.containsKey(ID)){
            enemy = (EnemyView) currentNodes.get(ID);
            enemy.setTranslateX(x);
            enemy.setTranslateY(y);
        } else {
            enemy = new EnemyView(x, y, w, h, Color.RED);
            currentNodes.put(ID, enemy);
            getChildren().add(enemy);
        }
    }

    public void refreshBullet(int x, int y, int w, int h, long ID){
        BulletView bullet;
        if(currentNodes.containsKey(ID)){
            bullet = (BulletView) currentNodes.get(ID);
            bullet.setTranslateX(x);
            bullet.setTranslateY(y);
        } else {
            bullet = new BulletView(x, y, w, h, Color.BLACK);
            currentNodes.put(ID, bullet);
            getChildren().add(bullet);
        }
    }

    public void refreshSpacecraft(int x, int y, int w, int h, long ID){
        SpacecraftView spacecraft;

        if(currentNodes.containsKey(ID) ){
            spacecraft = (SpacecraftView) currentNodes.get(ID);
            spacecraft.setTranslateX(x);
            spacecraft.setTranslateY(y);
        } else {
            spacecraft = new SpacecraftView(x,y,w,h,Color.CYAN);
            getChildren().add(spacecraft);
            currentNodes.put(ID,spacecraft);
        }
    }

    public Map<Long, Node> getCurrentNodes() {
        return currentNodes;
    }

    public void setCurrentNodes(Map<Long, Node> currentNodes) {
        this.currentNodes = currentNodes;
    }

}
