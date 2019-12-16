package org.openjfx.assetManager;

import javafx.scene.image.Image;
import org.openjfx.fileManager.FileController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PreBossAssets {

    private Image enemyStation;
    private Image tier1unevolved;
    private List<Image> spacecraft = new ArrayList<>();
    private Image bullet;
    private Image radarEnemy;
    private Image radarEnemySpaceStation;
    private Image radarSpacecraft;
    private Image radarMeteor;

    PreBossAssets(){
        try {
            enemyStation = new Image(FileController.getFileStream("assets/images/buildings/allybuilding.png"));
            tier1unevolved = new Image(FileController.getFileStream("assets/images/enemy.png"));
            spacecraft.add(new Image(FileController.getFileStream("assets/images/Spaceship_01_BLUE.png")));
            spacecraft.add(new Image(FileController.getFileStream("assets/images/Spaceship_01_GREEN.png")));
            bullet = new Image(FileController.getFileStream("assets/images/bullet.png"));
            radarEnemySpaceStation = new Image(FileController.getFileStream("assets/images/enemySpaceStation.png"));
            radarSpacecraft = new Image(FileController.getFileStream("assets/images/radar_spacecraft.png"));
            radarEnemy = new Image(FileController.getFileStream("assets/images/enemyRadar.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Image getEnemyStation() {
        return enemyStation;
    }

    public Image getTier1unevolved() {
        return tier1unevolved;
    }

    public List<Image> getSpacecraft() {
        return spacecraft;
    }

    public Image getBullet() {
        return bullet;
    }

    public Image getRadarEnemySpaceStation() {
        return radarEnemySpaceStation;
    }

    public Image getRadarSpacecraft() {
        return radarSpacecraft;
    }

    public Image getRadarEnemy() {
        return radarEnemy;
    }
}
