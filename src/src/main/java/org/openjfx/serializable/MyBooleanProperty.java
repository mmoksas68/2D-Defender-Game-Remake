package org.openjfx.serializable;

import javafx.beans.property.SimpleBooleanProperty;

import java.io.Serializable;

public class MyBooleanProperty extends SimpleBooleanProperty implements Serializable {

    public MyBooleanProperty(boolean value){
        super(value);
    }
}
