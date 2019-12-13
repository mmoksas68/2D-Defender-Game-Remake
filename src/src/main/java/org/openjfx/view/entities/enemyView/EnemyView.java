package org.openjfx.view.entities.enemyView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class EnemyView extends ImageView {

    private static Image image;

    EnemyView(int enemyType) {
        String url = "assets/images/enemy.png";
       /* switch (enemyType){
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

        }  */

        try {
            image = new Image(new FileInputStream(url));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        setImage(image);
    }
}
