package org.openjfx.assetManager;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.openjfx.fileManager.FileController;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PreBossAssets {

    private List<Image> station = new ArrayList<>();
    private Image tier1unevolved;
    private Image tier1evolved;
    private List<Image> spacecraft = new ArrayList<>();
    private Image bullet;
    private List<Image> smartBombCount = new ArrayList<>();
    private Image radarEnemy;
    private Image radarEnemySpaceStation;
    private Image radarSpacecraft;
    private Image meteor;
    private List<Image> gameInfoIcons = new ArrayList<>();
    private Image tier2unevolved;
    private Image tier2evolved;
    private Image tier3unevolved;
    private Image tier3evolved;
    private List<Image> backgrounds = new ArrayList<>();

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

            meteor = new Image(FileController.getFileStream("assets/images/meteor.png"));
            backgrounds.add(new Image(FileController.getFileStream("assets/images/backgrounds/background.png")));
            backgrounds.add(new Image(FileController.getFileStream("assets/images/backgrounds/background2.png")));
            backgrounds.add(new Image(FileController.getFileStream("assets/images/backgrounds/background3.png")));

            smartBombCount.add(null);
            smartBombCount.add(new Image(FileController.getFileStream("assets/images/bombs/singleBomb.png")));
            smartBombCount.add(new Image(FileController.getFileStream("assets/images/bombs/doubleBomb.png")));
            smartBombCount.add(new Image(FileController.getFileStream("assets/images/bombs/tripleBomb.png")));

            gameInfoIcons.add(new Image(FileController.getFileStream("assets/images/gameInfoIcons/scoreIcon.png")));
            gameInfoIcons.add(new Image(FileController.getFileStream("assets/images/gameInfoIcons/monster.png")));
            gameInfoIcons.add(new Image(FileController.getFileStream("assets/images/gameInfoIcons/space-station.png")));

            tier2unevolved = new Image(FileController.getFileStream("assets/images/spacecrafts/bomber.png"));
            tier2evolved = new Image(FileController.getFileStream("assets/images/spacecrafts/bomber2.png"));

            tier3unevolved = new Image(FileController.getFileStream("assets/images/spacecrafts/bomber.png"));
            tier3evolved = new Image(FileController.getFileStream("assets/images/spacecrafts/bomber2.png"));
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
    public List<Image> getBackgrounds() {return backgrounds;}

    public Image getTier2evolved() {
        return tier2evolved;
    }

    public void setTier2evolved(Image tier2evolved) {
        this.tier2evolved = tier2evolved;
    }

    public Image getTier3unevolved() {
        return tier3unevolved;
    }

    public void setTier3unevolved(Image tier3unevolved) {
        this.tier3unevolved = tier3unevolved;
    }

    public Image getTier3evolved() {
        return tier3evolved;
    }

    public void setTier3evolved(Image tier3evolved) {
        this.tier3evolved = tier3evolved;
    }
}
