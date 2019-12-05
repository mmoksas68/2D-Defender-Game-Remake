package model;

public class Marker extends GameObject{
    private double damage;
    public Marker(Location location, double radius, double damage) {
        super( location, 0, radius,radius);
        this.damage = damage;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
}
