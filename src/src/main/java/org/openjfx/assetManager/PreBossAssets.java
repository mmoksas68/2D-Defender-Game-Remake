package org.openjfx.assetManager;

import javafx.scene.image.Image;
import org.openjfx.fileManager.FileController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PreBossAssets {

    private List<Image> station = new ArrayList<>();
    private Image tier1unevolved;
    private Image tier1evolved;
    private List<Image> spacecraft = new ArrayList<>();
    private Image bullet;
    private Image radarEnemy;
    private Image radarEnemySpaceStation;
    private Image radarSpacecraft;
    private Image radarMeteor;

    PreBossAssets(){
        try {
            station.add(new Image(FileController.getFileStream("assets/images/buildings/enemybuilding2t1.png")));
            station.add(new Image(FileController.getFileStream("assets/images/buildings/enemybuilding2t3.png")));
            tier1unevolved = new Image(FileController.getFileStream("assets/images/enemy.png"));
            tier1evolved = new Image(FileController.getFileStream("assets/images/evolvedTier1.png"));
            spacecraft.add(new Image(FileController.getFileStream("assets/images/Spaceship_01_BLUE.png")));
            spacecraft.add(new Image(FileController.getFileStream("assets/images/Spaceship_01_GREEN.png")));
            spacecraft.add(new Image(FileController.getFileStream("assets/images/Spaceship_01_PURPLE.png")));
            bullet = new Image(FileController.getFileStream("assets/images/bullet.png"));
            radarEnemySpaceStation = new Image(FileController.getFileStream("assets/images/enemySpaceStation.png"));
            radarSpacecraft = new Image(FileController.getFileStream("assets/images/radar_spacecraft.png"));
            radarEnemy = new Image(FileController.getFileStream("assets/images/enemyRadar.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Image> getEnemyStation() {
        return station;
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

    public Image getTier1evolved() {
        return tier1evolved;
    }

    public void setTier1evolved(Image tier1evolved) {
        this.tier1evolved = tier1evolved;
    }
}
