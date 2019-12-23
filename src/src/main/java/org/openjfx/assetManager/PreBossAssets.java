package org.openjfx.assetManager;

import javafx.scene.image.Image;
import org.openjfx.fileManager.FileController;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PreBossAssets {

    private List<Image> station = new ArrayList<>();
    private List<Image> spacecraft = new ArrayList<>();
    private Image bullet;
    private List<Image> smartBombCount = new ArrayList<>();
    private Image radarEnemy;
    private Image radarEnemySpaceStation;
    private Image radarSpacecraft;
    private Image meteor;
    private List<Image> gameInfoIcons = new ArrayList<>();
    private Image tier1unevolved;
    private Image tier1evolved;
    private Image tier2unevolved;
    private Image tier2evolved;
    private Image tier3unevolved;
    private Image tier3evolved;


    PreBossAssets(){
        try {
            station.add(new Image(FileController.getFileStream("assets/preBossAssets/buildings/enemybuilding1t1.png")));
            station.add(new Image(FileController.getFileStream("assets/preBossAssets/buildings/enemybuilding2t3.png")));
            tier1unevolved = new Image(FileController.getFileStream("assets/preBossAssets/enemies/Ship1.png"));
            tier1evolved = new Image(FileController.getFileStream("assets/preBossAssets/enemies/Ship2.png"));
            tier2unevolved = new Image(FileController.getFileStream("assets/preBossAssets/enemies/Ship3.png"));
            tier2evolved = new Image(FileController.getFileStream("assets/preBossAssets/enemies/Ship4.png"));
            tier3unevolved = new Image(FileController.getFileStream("assets/preBossAssets/enemies/Ship5.png"));
            tier3evolved = new Image(FileController.getFileStream("assets/preBossAssets/enemies/Ship6.png"));
            spacecraft.add(new Image(FileController.getFileStream("assets/preBossAssets/Spaceship_01_BLUE.png")));
            spacecraft.add(new Image(FileController.getFileStream("assets/preBossAssets/Spaceship_01_GREEN.png")));
            spacecraft.add(new Image(FileController.getFileStream("assets/preBossAssets/Spaceship_01_PURPLE.png")));
            bullet = new Image(FileController.getFileStream("assets/preBossAssets/laser.png"));
            radarEnemySpaceStation = new Image(FileController.getFileStream("assets/preBossAssets/enemySpaceStation.png"));
            radarSpacecraft = new Image(FileController.getFileStream("assets/preBossAssets/radar_spacecraft.png"));
            radarEnemy = new Image(FileController.getFileStream("assets/preBossAssets/enemyRadar.png"));

            meteor = new Image(FileController.getFileStream("assets/preBossAssets/meteor.png"));

            smartBombCount.add(null);
            smartBombCount.add(new Image(FileController.getFileStream("assets/preBossAssets/bombs/singleBomb.png")));
            smartBombCount.add(new Image(FileController.getFileStream("assets/preBossAssets/bombs/doubleBomb.png")));
            smartBombCount.add(new Image(FileController.getFileStream("assets/preBossAssets/bombs/tripleBomb.png")));

            gameInfoIcons.add(new Image(FileController.getFileStream("assets/preBossAssets/gameInfoIcons/scoreIcon.png")));
            gameInfoIcons.add(new Image(FileController.getFileStream("assets/preBossAssets/gameInfoIcons/monster.png")));
            gameInfoIcons.add(new Image(FileController.getFileStream("assets/preBossAssets/gameInfoIcons/space-station.png")));


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

    public List<Image> getSmartBombImg(){
        return smartBombCount;
    }

    public List<Image> getGameInfoIcons(){
        return  gameInfoIcons;
    }

    public Image getTier2unevolved() {
        return tier2unevolved;
    }

    public List<Image> getStation() {
        return station;
    }

    public List<Image> getSmartBombCount() {
        return smartBombCount;
    }

    public Image getMeteor() {
        return meteor;
    }

    public Image getTier3unevolved() {
        return tier3unevolved;
    }

    public Image getTier2evolved() {
        return tier2evolved;
    }

    public Image getTier3evolved() {
        return tier3evolved;
    }
}
