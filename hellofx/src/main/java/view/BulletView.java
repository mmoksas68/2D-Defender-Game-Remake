package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BulletView extends ImageView {
    public  BulletView (double x, double y, double width, double height) throws FileNotFoundException {
        Image image = new Image( new FileInputStream("resources/bullet.png"));
        setImage( image);
        setFitWidth( width);
        setFitHeight( height);
        setTranslateX( x);
        setTranslateY(y);
    }
    public void refreshBullet (double currentX, double currentY) {
        setTranslateX( currentX);
        setTranslateY( currentY);
    }
}
