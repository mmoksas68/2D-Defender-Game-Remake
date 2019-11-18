package model;

public class SpaceCraft extends GameObject {
    private final double MAP_WIDTH = 800;
    private final double MAX_X = (800 /2) - getWidth();
    private double bulletVelocity;
    private int healthPoint;
    public SpaceCraft () {
        super( 50, 280, 10,80,40);
        healthPoint = 1000;
        bulletVelocity = 30;
    }

    public Bullet fire () {
        return new Bullet(getID(),getX() + getWidth(),getY() + getHeight()/2,bulletVelocity);
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }
}
