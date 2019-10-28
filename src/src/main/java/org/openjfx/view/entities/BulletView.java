package org.openjfx.view.entities;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class BulletView extends Rectangle {
    public BulletView(double x, double y, double w, double h, Paint paint) {
        super(w, h, paint);
        setTranslateX(x);
        setTranslateY(y);
    }
}
