package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MarkerView extends ImageView {
    public MarkerView(double x, double y, double width, double height) throws FileNotFoundException {
        Image image = new Image( new FileInputStream("resources/dead_head.png"));
        setImage( image);
        setFitWidth( width);
        setFitHeight( height);
        setTranslateX( x);
        setTranslateY(y);
    }
}
