package org.openjfx.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.openjfx.model.menuEntities.Settings;

import java.io.File;

public class SoundController {

    public static void fireBullet(){
        String musicFile = "assets/sounds/laser1.wav";     // For example
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(Settings.getInstance().getVolume());
        mediaPlayer.play();
    }

    public static void explosion(){
        String musicFile = "assets/sounds/explosion.wav";     // For example
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(Settings.getInstance().getVolume());
        mediaPlayer.play();
    }

    public static void buttonClick(){
        String buttonClick;
        buttonClick = "assets/sounds/button_sound2.wav";
        //buttonClick = "assets/sounds/button_sound3.wav";
        Media sound = new Media(new File(buttonClick).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(Settings.getInstance().getVolume());
        mediaPlayer.play();

    }

    public static void buttonEntered(){
        String buttonClick;
        int theme = Settings.getInstance().getTheme();
        buttonClick = "assets/sounds/button_sound1.wav";
        Media sound = new Media(new File(buttonClick).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(Settings.getInstance().getVolume());
        mediaPlayer.play();

    }



}
