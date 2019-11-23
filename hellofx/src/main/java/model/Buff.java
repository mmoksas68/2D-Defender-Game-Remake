package model;

public class Buff extends GameObject{

    private String name;
    private String [] buffs = { "increaseHealth", "increasePower"};

   public Buff (Location location) {
       super(location, 5,20,20);
       name = null;
       randomBuff();
    }
    public void randomBuff () {
            int selectedBuffIndex = (int) (Math.random()*buffs.length);
            name = buffs[ selectedBuffIndex];
    }

    public String getName() {
        return name;
    }
}
