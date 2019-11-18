package model;

public abstract class GameObject {
        private double x;
        private double y;
        private double velocity;
        private double width;
        private double height;
        private int ID;
        private static int nextID = 0;
        public GameObject (double x, double y, double velocity, double width, double height) {
            this.x = x; this.y = y;  this.velocity = velocity; this.width = width;
            this.height = height;   ID = ++nextID;
        }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
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
            x = x + xDir * multiplier;
            y = y + yDir * multiplier;
    }
}

