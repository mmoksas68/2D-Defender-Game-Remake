package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LaserView extends ImageView {
    public  LaserView (double x, double y, double width, double height, Color color) throws FileNotFoundException {
        Image image = new Image( new FileInputStream("resources/bossLaser.png"));

        setImage( image);
        setFitWidth( width);
        setFitHeight( height);
        setTranslateX( x);
        setTranslateY(y);
    }
    public void refreshLaser (double currentX, double currentY) {
        setTranslateX( currentX);
        setTranslateY( currentY);
    }
}
