package model;

public class Laser extends GameObject {
    private int damage;
    public Laser( double x, double y, double width,double height, int damage) {
        super( x, y, 1000, width, height);
        this.damage = damage;
    }
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
