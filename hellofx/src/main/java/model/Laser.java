package model;

public class Laser extends GameObject {
    private int damage;
    public Laser( Location location, double width,double height, int damage) {
        super( location, 0, width, height);
        this.damage = damage;
    }
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
