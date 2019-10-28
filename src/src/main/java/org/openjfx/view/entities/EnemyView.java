package org.openjfx.view.entities;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class EnemyView extends Rectangle{

    public EnemyView(double x, double y, double w, double h, Paint paint) {
        super(w, h, paint);
        setTranslateX(x);
        setTranslateY(y);
    }

}
