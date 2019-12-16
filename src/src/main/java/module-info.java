module org.openjfx {
    requires javafx.controls;
    requires javafx.media;
    exports org.openjfx;
    exports org.openjfx.controller;
    opens org.openjfx.model.menuEntities to javafx.base;
}