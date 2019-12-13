package org.openjfx.view.entities.Buff;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BuffView extends ImageView {
    private String buff;
    public BuffView (String key, double x, double y, double width,double height) throws FileNotFoundException {
        processKey( key);
        Image image = new Image(  new FileInputStream(buff));
        setImage( image);
        setFitWidth( width);
        setFitHeight( height);
        setTranslateX( x);
        setTranslateY(y);
    }
    private void processKey(String key) {

        switch ( key) {
            case "increaseHealth":
                buff ="resources/heartBonus.png";
                break;
            case "increasePower":
                buff = "resources/damageBonus.png";
                break;
            default:
        }
    }

}
