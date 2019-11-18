package org.openjfx.view.entities.buildingView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

class BuildingView extends ImageView {
    private static Image image;

    BuildingView(int buildingType) {
        String url = "assets/images/buildings/allybuilding.png";
        switch (buildingType){
            case 1:
                url = "assets/images/buildings/allybuilding.png";
                break;
            case 2:
                url = "assets/images/buildings/enemybuilding1t1.png";
                break;
            case 3:
                url = "assets/images/buildings/enemybuilding1t2.png";
                break;
            case 4:
                url = "assets/images/buildings/enemybuilding1t3.png";
                break;
            case 5:
                url = "assets/images/buildings/enemybuilding2t1.png";
                break;
            case 6:
                url = "assets/images/buildings/enemybuilding2t2.png";
                break;
            case 7:
                url = "assets/images/buildings/enemybuilding2t3.png";
                break;
        }

        try {
            image = new Image(new FileInputStream(url));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        setImage(image);
    }
}
