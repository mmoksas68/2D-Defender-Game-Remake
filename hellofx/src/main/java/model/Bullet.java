package model;

public class Bullet extends GameObject {
    int sourceID;
    boolean isDead;
    public Bullet ( int sourceID, Location location, double velocity ) {
        super( location, velocity,10,5);
        this.sourceID = sourceID;
        isDead = false;
    }

    public int getSourceID() {
        return sourceID;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}
