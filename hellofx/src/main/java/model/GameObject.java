package model;

public abstract class GameObject {
        private Location location;
        private double velocity;
        private double width;
        private double height;
        private int ID;
        private static int currentID = 0;
        public GameObject (Location location, double velocity, double width, double height) {
            this.location = location;  this.velocity = velocity; this.width = width;
            this.height = height;   this.ID = currentID++;
        }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
    public int getID (){
            return ID;
    }
    public void move (Double xDir, Double yDir) {
            double distance = Math.sqrt(Math.pow( xDir,2) + Math.pow( yDir, 2));
            double multiplier = velocity / distance;
            location.setPositionX( location.getPositionX()  + xDir * multiplier);
            location.setPositionY( location.getPositionY() + yDir * multiplier);
    }
}

