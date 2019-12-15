package org.openjfx.assetManager;

import javafx.scene.image.Image;
import org.openjfx.fileManager.FileController;

import java.io.FileNotFoundException;

public class PreBossAssets {

    private Image enemyStation;
    private Image tier1unevolved;
    private Image spacecraft;
    private Image bullet;

    PreBossAssets(){
        try {
            enemyStation = new Image(FileController.getFileStream("assets/images/buildings/allybuilding.png"));
            tier1unevolved = new Image(FileController.getFileStream("assets/images/enemy.png"));
            spacecraft = new Image(FileController.getFileStream("assets/images/Spaceship_01_BLUE.png"));
            bullet = new Image(FileController.getFileStream("assets/images/bullet.png"));
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

    public Image getSpacecraft() {
        return spacecraft;
    }

    public Image getBullet() {
        return bullet;
    }

}
