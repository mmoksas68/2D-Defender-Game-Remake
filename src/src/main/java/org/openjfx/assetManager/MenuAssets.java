package org.openjfx.assetManager;

import javafx.scene.image.Image;
import org.openjfx.fileManager.FileController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MenuAssets {

    private Image gameLogo;
    private ArrayList<Image> colorOptions = new ArrayList<>();
    private ArrayList<Image> backgrounds = new ArrayList<>();

    MenuAssets(){
        try {
            gameLogo = new Image(FileController.getFileStream("assets/menuAssets/logo.png"));
            colorOptions.add(new Image(FileController.getFileStream("assets/menuAssets/color1.jpg")));
            colorOptions.add(new Image(FileController.getFileStream("assets/menuAssets/color2.png")));
            colorOptions.add(new Image(FileController.getFileStream("assets/menuAssets/color3.jpg")));
            backgrounds.add(new Image(FileController.getFileStream("assets/menuAssets/background.png")));
            backgrounds.add(new Image(FileController.getFileStream("assets/menuAssets/background2.png")));
            backgrounds.add(new Image(FileController.getFileStream("assets/menuAssets/background3.jpg")));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Image getGameLogo(){
        return gameLogo;
    }

    public ArrayList<Image> getColorOptions(){
        return colorOptions;
    }

    public ArrayList<Image> getBackgrounds(){
        return backgrounds;
    }

}
