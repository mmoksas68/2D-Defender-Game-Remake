package org.openjfx.assetManager;

import javafx.scene.image.Image;
import org.openjfx.fileManager.FileController;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MenuAssets {

    private Image gameLogo, restart, credits, howtoplay, howtoplay2;
    private ArrayList<Image> colorOptions = new ArrayList<>();
    private ArrayList<Image> backgrounds = new ArrayList<>();

    MenuAssets(){
        try {
            gameLogo = new Image(FileController.getFileStream("assets/menuAssets/logo.png"));
            colorOptions.add(new Image(FileController.getFileStream("assets/menuAssets/theme1.jpg")));
            colorOptions.add(new Image(FileController.getFileStream("assets/menuAssets/theme2.jpg")));
            colorOptions.add(new Image(FileController.getFileStream("assets/menuAssets/theme3.jpg")));
            backgrounds.add(new Image(FileController.getFileStream("assets/menuAssets/background.png")));
            backgrounds.add(new Image(FileController.getFileStream("assets/menuAssets/background2.png")));
            backgrounds.add(new Image(FileController.getFileStream("assets/menuAssets/background3.png")));
            restart = new Image(FileController.getFileStream("assets/menuAssets/restart.png"));
            credits = new Image(FileController.getFileStream("assets/menuAssets/credits.png"));
            howtoplay = new Image(FileController.getFileStream("assets/menuAssets/Howtoplay.png"));
            howtoplay2 = new Image(FileController.getFileStream("assets/menuAssets/howtoplay2.png"));


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

    public Image getRestart(){
        return restart;
    }

    public Image getCredits(){return credits;}

    public Image getHowtoPlay() {
        return howtoplay;
    }


    public Image getHowtoPlay2() {
        return howtoplay2;
    }
}
