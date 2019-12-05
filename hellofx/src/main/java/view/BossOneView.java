package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BossOneView extends ImageView {
    public  BossOneView (double x, double y, double width, double height) throws FileNotFoundException {
        Image image = new Image( new FileInputStream("resources/boss2.png"));
        setImage ( image);
        setFitWidth( width);
        setFitHeight( height);
         //   super ( width, height, Color.BLUE);
            setTranslateX( x);
            setTranslateY(y);
    }
    public void refreshBossOne (double currentX, double currentY) {
            setTranslateX( currentX);
            setTranslateY( currentY);
    }
}

