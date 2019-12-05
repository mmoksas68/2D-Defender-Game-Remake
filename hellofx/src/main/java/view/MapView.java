package view;




import javafx.scene.layout.AnchorPane;
import model.LittleBoss;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MapView extends AnchorPane {


    private SpaceCraftView spaceCraftView;
    private ArrayList <BulletView> bulletViews;
    private BossOneView bossOneView;
    private ArrayList <LaserView> laserViews;
    private ArrayList <LittleBossView> littleBossViews;
    private ArrayList <BuffView> buffViews;
    public MapView (){
        bulletViews = new ArrayList<>();
        laserViews = new ArrayList<>();
        littleBossViews = new ArrayList<>();
        buffViews = new ArrayList<>();
    }

    public ArrayList <BulletView> getBulletViews () {
        return bulletViews;
    }

    public ArrayList<LittleBossView> getLittleBossViews(){
        return littleBossViews;
    }

    public ArrayList<BuffView> getBuffViews() {
        return buffViews;
    }

    public void setBuffViews(ArrayList<BuffView> buffViews) {
        this.buffViews = buffViews;
    }

    public void refreshSpaceCraftView(double currentX, double currentY) {
        spaceCraftView.refreshSpaceCraft(currentX, currentY);
    }
    public void refreshBullet(BulletView b, double currentX, double currentY) {
        b.refreshBullet( currentX, currentY);
    }
    public void refreshBoss ( double currentX, double currentY) {
        bossOneView.refreshBossOne( currentX, currentY);
    }

    public void refreshLittleBoss( LittleBossView littleBossView, double currentX, double currentY){
        littleBossView.refreshLittleBoss( currentX, currentY);
    }

    ////////////////////////////////////////////////////////////////////////////
    public void addSpaceCraftView( double x, double y, double width, double height) throws FileNotFoundException {
        spaceCraftView = new SpaceCraftView( x,y, width,height);
        getChildren().add( spaceCraftView);
    }
    public SpaceCraftView  getSpaceCraftView() {
        return spaceCraftView;
    }

    public void addBossOneView(double x, double y, double width, double height) throws FileNotFoundException {
        bossOneView = new BossOneView(x,y,width,height);
        getChildren().add( bossOneView);
    }
    public BossOneView getBossOneView () {
        return bossOneView;
    }
    public void addBulletView (BulletView bulletView){
        bulletViews.add( bulletView);
        getChildren().add( bulletView);
    }
    public void removeBulletView (BulletView bulletView) {
        getChildren().remove( bulletView);
        bulletViews.remove( bulletView);
    }

    public void addLittleBossView(LittleBossView littleBossView) {
        littleBossViews.add( littleBossView);
        getChildren().add( littleBossView);
    }

    public void removeLittleBossView( LittleBossView littleBossView){
        getChildren().remove( littleBossView);
        littleBossViews.remove( littleBossView);
    }

    public void addLaserView (LaserView laserView) {
        laserViews.add( laserView);
        getChildren().add( laserView);
    }
    public void removeLaserView (LaserView laserView) {
        getChildren().remove( laserView);
        laserViews.remove(laserView);
    }
   /* public LaserView getLaserView () {
        return laserView;
    }*/
   public void addBuffView (BuffView buffView) {
       buffViews.add( buffView);
       getChildren().add( buffView);
   }
   public void refreshBuffView(BuffView bw, double currentX, double currentY) {
       bw.setTranslateX( currentX);
       bw.setTranslateY( currentY);
   }

   public void addMarkerView (MarkerView mw) throws FileNotFoundException {
       getChildren().add( mw);
   }
   public void deleteMarkerView (MarkerView mw) {
       getChildren().remove( mw);
   }
}
