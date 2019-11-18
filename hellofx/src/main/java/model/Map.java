package model;

import java.util.ArrayList;

public class Map {
    private final double MAX_WIDTH = 800.0;
    private final double MAX_HEIGHT = 600.0;
    private SpaceCraft spaceCraft;
    private ArrayList <Bullet> bullets;
    private BossOne bossOne;
    private ArrayList <Laser> lasers;
    public Map () {
        spaceCraft = new SpaceCraft();
        bullets = new ArrayList<>();
        bossOne = new BossOne();
        lasers = new ArrayList<>();
    }

    public SpaceCraft getSpaceCraft() {
        return spaceCraft;
    }
    public ArrayList <Bullet> getBullets() {
        return  bullets;
    }
    public BossOne getBossOne () {return bossOne;}

    public ArrayList<Laser> getLasers() {
        return lasers;
    }

    public void checkCollisions (GameObject object) {
        if ( object instanceof Bullet) {
            if (( ((Bullet) object).getSourceID() == spaceCraft.getID())) {
                if (object.getX() >= bossOne.getX() && object.getY() >= bossOne.getY() && object.getY() <= bossOne.getY() + bossOne.getHeight()) {
                    ((Bullet) object).setDead(true);
                    bossOne.setHealthPoint(bossOne.getHealthPoint() - 30);
                }
            }
            else if (((Bullet) object).getSourceID() == bossOne.getID()) {
                if (object.getX() <= spaceCraft.getX() + spaceCraft.getWidth() && object.getX() >= spaceCraft.getHeight() && object.getY() >= spaceCraft.getY() && object.getY() <= spaceCraft.getY() + spaceCraft.getWidth()) {
                    ((Bullet) object).setDead(true);
                    spaceCraft.setHealthPoint(spaceCraft.getHealthPoint() - 30);
                }
            }
        }
        else if ( object instanceof Laser) {
            for ( double y = spaceCraft.getY(); y <= spaceCraft.getY()+spaceCraft.getHeight() && ((Laser) object).getDamage() != 0; y++) {
                if ( object.getY() <= y && y <= object.getY()+object.getHeight()) {
                    spaceCraft.setHealthPoint( spaceCraft.getHealthPoint()-((Laser) object).getDamage());
                }
            }

        }



    }

    public void checkBoundry (GameObject gameObject) {
        if ( gameObject.getID() == spaceCraft.getID()) {

        }
    }
}
