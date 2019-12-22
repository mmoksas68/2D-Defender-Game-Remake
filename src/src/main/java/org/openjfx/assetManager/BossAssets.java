package org.openjfx.assetManager;

import javafx.scene.image.Image;
import org.openjfx.fileManager.FileController;

import java.io.FileNotFoundException;

public class BossAssets {

    private Image bossOne;
    private Image bossTwo;
    private Image bossThree;
    private Image laser;
    private Image laserIndicator;
    private Image marker;
    private Image rocket;
    private Image littleBoss;

    BossAssets(){
        try {
            bossOne = new Image(FileController.getFileStream("assets/bossAssets/bossOne.png"));
            bossTwo = new Image(FileController.getFileStream("assets/bossAssets/bossTwo.png"));
            bossThree = new Image(FileController.getFileStream("assets/bossAssets/bossThree.png"));
            laser = new Image(FileController.getFileStream("assets/bossAssets/bossLaser.png"));
            laserIndicator = new Image(FileController.getFileStream("assets/bossAssets/bossLaserIndicator.png"));
            marker = new Image(FileController.getFileStream("assets/bossAssets/dead_head.png"));
            rocket = new Image(FileController.getFileStream("assets/bossAssets/rocket.png"));
            littleBoss = new Image(FileController.getFileStream("assets/bossAssets/bossThree.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Image getBossOne() { return bossOne; }

    public Image getBossTwo() { return bossTwo; }

    public Image getBossThree() { return bossThree; }

    public Image getLaser() { return laser; }

    public Image getLaserIndicator() {
        return laserIndicator;
    }

    public Image getMarker() { return marker; }

    public Image getRocket() { return rocket; }

    public Image getLittleBoss() { return littleBoss; }




}
