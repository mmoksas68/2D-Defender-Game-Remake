package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Map {
    private final double MAX_WIDTH = 800.0;
    private final double MAX_HEIGHT = 600.0;
    private final double MOVE_OFFSET = 10.0;

    private final double BUFF_PROB = 0.01;
    private SpaceCraft spaceCraft;
    private ArrayList <Bullet> bullets;
    private Boss boss;
    private ArrayList <Laser> lasers;
    private ArrayList <LittleBoss> littleBosses;
    private HashMap<Buff, Boolean> buffs;
    private ArrayList <Marker> markers;
    public Map (int level) {
        spaceCraft = new SpaceCraft();
        bullets = new ArrayList<>();
        lasers = new ArrayList<>();
        littleBosses = new ArrayList<>();
        buffs = new HashMap<>();

        switch ( level) {
            case 1:
                boss = new BossOne();
                break;
            case 2:
                boss = new BossTwo();
                break;
            case 3:
                boss = new BossThree();
                break;
            default:
                break;
        }

    }

    public SpaceCraft getSpaceCraft() {
        return spaceCraft;
    }
    public ArrayList <Bullet> getBullets() {
        return  bullets;
    }

    public ArrayList<Laser> getLasers() {
        return lasers;
    }
    public ArrayList<LittleBoss> getLittleBosses() { return littleBosses; }

    public void checkCollisions (GameObject object) {
        if ( object instanceof Bullet) {
            if (( ((Bullet) object).getSourceID() == spaceCraft.getID())) {
                if (object.getLocation().getPositionX() >= boss.getLocation().getPositionX() && object.getLocation().getPositionY() >= boss.getLocation().getPositionY() && object.getLocation().getPositionY() <= boss.getLocation().getPositionY() + boss.getHeight()) {
                    ((Bullet) object).setDead(true);
                    boss.setHealthPoint(boss.getHealthPoint() - spaceCraft.getGunPower());
                    if ( Math.random() < BUFF_PROB) {
                        double randomY = boss.getLocation().getPositionY() + (Math.random() * boss.getHeight());
                        buffs.put(new Buff( new Location( boss.getLocation().getPositionX(), randomY)), false);
                    }

                }
            }
            else if (((Bullet) object).getSourceID() == boss.getID()) {
                if (object.getLocation().getPositionX() <= spaceCraft.getLocation().getPositionX() + spaceCraft.getWidth() && object.getLocation().getPositionX() >= spaceCraft.getHeight() && object.getLocation().getPositionY() >= spaceCraft.getLocation().getPositionY() && object.getLocation().getPositionY() <= spaceCraft.getLocation().getPositionY() + spaceCraft.getWidth()) {
                    ((Bullet) object).setDead(true);
                    spaceCraft.setHealthPoint((int) (spaceCraft.getHealthPoint() - boss.getGunPower()));



                }
            }
        }
        else if ( object instanceof Laser) {
            double topSpacecraft = spaceCraft.getLocation().getPositionY();
            double bottomSpacecraft = topSpacecraft + spaceCraft.getHeight();
            double topLaser = object.getLocation().getPositionY();
            double bottomLaser = topLaser + object.getHeight();
            if ( (bottomSpacecraft > topLaser && bottomSpacecraft < bottomLaser) ||
                    (topSpacecraft < bottomLaser && topSpacecraft > topLaser)    )    {
                spaceCraft.setHealthPoint((int) (spaceCraft.getHealthPoint() - ((Laser) object).getDamage()));
            }
           /* for ( double y = spaceCraft.getLocation().getPositionY(); y <= spaceCraft.getLocation().getPositionY()+spaceCraft.getHeight() && ((Laser) object).getDamage() != 0; y++) {
                if ( object.getLocation().getPositionY() <= y && y <= object.getLocation().getPositionY()+object.getHeight()) {
                    spaceCraft.setHealthPoint( spaceCraft.getHealthPoint()-((Laser) object).getDamage());
                }
            }*/

        }
        else if ( object instanceof Marker ) {
            double topSpacecraft = spaceCraft.getLocation().getPositionY();
            double bottomSpacecraft = topSpacecraft + spaceCraft.getHeight();
            double topMarker = object.getLocation().getPositionY();
            double bottomMarker = topMarker + object.getHeight();
            double leftSpacecraft = spaceCraft.getLocation().getPositionX();
            double rightSpacecraft = leftSpacecraft + spaceCraft.getWidth();
            double leftMarker = object.getLocation().getPositionX();
            double rightMarker = leftMarker + object.getWidth();

            if ( !(bottomSpacecraft < topMarker || topSpacecraft > bottomMarker ||
                 rightSpacecraft < leftMarker || leftSpacecraft > rightMarker )   ) {
                spaceCraft.setHealthPoint((int) (spaceCraft.getHealthPoint() - ((Marker) object).getDamage()));
            }


        }
        else if( object instanceof LittleBoss) {
            if (object.getLocation().getPositionX() <= spaceCraft.getLocation().getPositionX() + spaceCraft.getWidth() && object.getLocation().getPositionX() >= spaceCraft.getHeight() && object.getLocation().getPositionY() >= spaceCraft.getLocation().getPositionY() && object.getLocation().getPositionY() <= spaceCraft.getLocation().getPositionY() + spaceCraft.getWidth()) {
                spaceCraft.setHealthPoint((int) (spaceCraft.getHealthPoint() - ((LittleBoss) object).getDamage()));
            }
        }


    }

    public void checkBoundry ( double [] array, GameObject gameObject) {
        if ( gameObject instanceof SpaceCraft) {
            if (spaceCraft.getLocation().getPositionX() >= MAX_WIDTH / 2 - 5 * MOVE_OFFSET) array[0] = 0;
            if (spaceCraft.getLocation().getPositionX() <= 2 * MOVE_OFFSET) array[1] = 0;
            if (spaceCraft.getLocation().getPositionY() <= 2 * MOVE_OFFSET) array[2] = 0;
            if (spaceCraft.getLocation().getPositionY() >= MAX_HEIGHT - spaceCraft.getHeight() - 2 * MOVE_OFFSET)
                array[3] = 0;
        }

    }

    public HashMap<Buff,Boolean> getBuffs() {
        return buffs;
    }

    public Boss getBoss() {
        return boss;
    }

    public boolean useSpecialAbility (Boss boss) {
        if ( boss instanceof BossTwo) {
            if ( Math.random() < ((BossTwo) boss).getROCKET_FREQ()) {
                markers = ((BossTwo) boss).sendRockets(500,550);
                return true;
            }
            return false;
        }
        return false;
    }

    public ArrayList<Marker> getMarkers() {
        return markers;
    }

    public double getMAX_WIDTH() {
        return MAX_WIDTH;
    }

    public double getMAX_HEIGHT() {
        return MAX_HEIGHT;
    }

    public double getMOVE_OFFSET() {
        return MOVE_OFFSET;
    }
}
