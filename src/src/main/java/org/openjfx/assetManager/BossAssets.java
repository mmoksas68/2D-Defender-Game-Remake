package org.openjfx.assetManager;

import javafx.scene.image.Image;
import org.openjfx.fileManager.FileController;

import java.io.FileNotFoundException;

public class BossAssets {

    private Image boss;
    private Image laser;
    private Image laserIndicator;
    private Image marker;
    private Image rocket;
    private Image littleBoss;

    BossAssets(){
        try {
            boss = new Image(FileController.getFileStream("resources/boss2.png"));
            laser = new Image(FileController.getFileStream("resources/bossLaser.png"));
            laserIndicator = new Image(FileController.getFileStream("assets/images/bossLaser.png"));
            marker = new Image(FileController.getFileStream("resources/dead_head.png"));
            rocket = new Image(FileController.getFileStream("resources/rocket.png"));
            littleBoss = new Image(FileController.getFileStream("resources/boss1.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Image getBoss() { return boss; }

    public Image getLaser() { return laser; }

    public Image getLaserIndicator() {
        return laserIndicator;
    }

    public Image getMarker() { return marker; }

    public Image getRocket() { return rocket; }

    public Image getLittleBoss() { return littleBoss; }
}
