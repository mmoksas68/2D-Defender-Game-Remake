package org.openjfx.view.entities;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class SpacecraftView extends Rectangle {

    public SpacecraftView(double x, double y, double w, double h, Paint paint) {
        super(w, h, paint);
        setTranslateX(x);
        setTranslateY(y);
    }

}
