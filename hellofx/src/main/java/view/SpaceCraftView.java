package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SpaceCraftView extends ImageView {

    public SpaceCraftView(double x, double y, double width, double height) throws FileNotFoundException {
        Image image = new Image( new FileInputStream("resources/spaceCraft.png"));
        setImage( image);
        setFitWidth( width);
        setFitHeight( height);
       // setImage( image);
       //super (width,height, Color.RED);
        setTranslateX( x);
        setTranslateY( y);
    }

    public void refreshSpaceCraft (double currentX, double currentY) {
        setTranslateX( currentX);
        setTranslateY( currentY);
    }
}
