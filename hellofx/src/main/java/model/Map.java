package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Map {
    private final double MAX_WIDTH = 800.0;
    private final double MAX_HEIGHT = 600.0;

    private final double BUFF_PROB = 0.01;
    private SpaceCraft spaceCraft;
    private ArrayList <Bullet> bullets;
    private BossOne bossOne;
    private ArrayList <Laser> lasers;
    private HashMap<Buff, Boolean> buffs;
    public Map () {
        spaceCraft = new SpaceCraft();
        bullets = new ArrayList<>();
        bossOne = new BossOne();
        lasers = new ArrayList<>();
        buffs = new HashMap<>();
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
                if (object.getLocation().getPositionX() >= bossOne.getLocation().getPositionX() && object.getLocation().getPositionY() >= bossOne.getLocation().getPositionY() && object.getLocation().getPositionY() <= bossOne.getLocation().getPositionY() + bossOne.getHeight()) {
                    ((Bullet) object).setDead(true);
                    bossOne.setHealthPoint(bossOne.getHealthPoint() - spaceCraft.getGunPower());
                    if ( Math.random() < BUFF_PROB) {
                        double randomY = getBossOne().getLocation().getPositionY() + (Math.random() * getBossOne().getHeight());
                        buffs.put(new Buff( new Location( bossOne.getLocation().getPositionX(), randomY)), false);
                    }

                }
            }
            else if (((Bullet) object).getSourceID() == bossOne.getID()) {
                if (object.getLocation().getPositionX() <= spaceCraft.getLocation().getPositionX() + spaceCraft.getWidth() && object.getLocation().getPositionX() >= spaceCraft.getHeight() && object.getLocation().getPositionY() >= spaceCraft.getLocation().getPositionY() && object.getLocation().getPositionY() <= spaceCraft.getLocation().getPositionY() + spaceCraft.getWidth()) {
                    ((Bullet) object).setDead(true);
                    spaceCraft.setHealthPoint((int) (spaceCraft.getHealthPoint() - bossOne.getGunPower()));



                }
            }
        }
        else if ( object instanceof Laser) {
            for ( double y = spaceCraft.getLocation().getPositionY(); y <= spaceCraft.getLocation().getPositionY()+spaceCraft.getHeight() && ((Laser) object).getDamage() != 0; y++) {
                if ( object.getLocation().getPositionY() <= y && y <= object.getLocation().getPositionY()+object.getHeight()) {
                    spaceCraft.setHealthPoint( spaceCraft.getHealthPoint()-((Laser) object).getDamage());
                }
            }

        }



    }

    public void checkBoundry (GameObject gameObject) {
        if ( gameObject.getID() == spaceCraft.getID()) {

        }
    }

    public HashMap<Buff,Boolean> getBuffs() {
        return buffs;
    }

}
