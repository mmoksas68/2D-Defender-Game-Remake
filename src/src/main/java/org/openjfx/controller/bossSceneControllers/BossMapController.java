package org.openjfx.controller.bossSceneControllers;

import org.openjfx.controller.SoundController;
import org.openjfx.model.bossEntities.Boss.Boss;
import org.openjfx.model.bossEntities.BossAbility.*;
import org.openjfx.model.bossEntities.BossMap;
import org.openjfx.model.commonEntities.Buff.Buff;
import org.openjfx.model.commonEntities.Bullet.Bullet;
import org.openjfx.model.commonEntities.LocatableObject;
import org.openjfx.model.commonEntities.Spacecraft.Spacecraft;
import org.openjfx.utilization.PositionHelper;


public class BossMapController {
    private BossMap bossMap;

    public BossMapController(BossMap bossMap) {
        this.bossMap = bossMap;
    }
    public void checkMapSituation() {

        for (var bullet : bossMap.getBullets().values()) {

            bullet.moveToDirection(bullet.getVelocity(), bullet.getDirectionX(), bullet.getDirectionY());
            bullet.setDistanceTravelled( bullet.getDistanceTravelled() + bullet.getVelocity());
            if(bullet.getDistanceTravelled() > BossMap.MAP_WIDTH)
            {
                bullet.setDead(true);
            }
        }

        for(var specialAbility: bossMap.getSpecialAbilities().values()){
            specialAbility.getAbilityBehaviourAlgorithm().moveSpecialAbility();
        }


        // Check collisions between game entities
        checkCollision( bossMap.getSpacecraft1(), bossMap.getBullets());
        checkCollision( bossMap.getBoss(), bossMap.getBullets());
        checkCollision( bossMap.getSpacecraft1(), bossMap.getSpecialAbilities());
        if(!bossMap.isSinglePlayer()){
            checkCollision(bossMap.getSpacecraft2(), bossMap.getBullets());
            checkCollision( bossMap.getSpacecraft2(), bossMap.getSpecialAbilities());
        }

    }

    private <T extends LocatableObject> void checkCollision(LocatableObject obj, java.util.Map<Long, T> list) {
        PositionHelper objHelper = new PositionHelper(obj);

        for (var iterator : list.values()) {
            PositionHelper iteratorHelper = new PositionHelper(iterator);

            if (PositionHelper.isThereACollision(objHelper, iteratorHelper)) {

                if( obj instanceof Spacecraft){
                    if( iterator instanceof Bullet && ((Bullet) iterator).getDirectionX() < 0){
                        obj.setHealthPoint(obj.getHealthPoint() - ((Bullet) iterator).getDamage());
                        iterator.setDead(true);
                    }
                    else if( iterator instanceof SpecialAbility){
                        obj.setHealthPoint(obj.getHealthPoint() - ((SpecialAbility) iterator).getDamage());
                        if( iterator instanceof LittleBoss)
                            iterator.setDead(true);
                    }
/* TO BE IMPLEMENTED
                    else if( iterator instanceof Buff){
                    }*/
                }
                else if( obj instanceof Boss){
                    if( iterator instanceof Bullet && ((Bullet) iterator).getDirectionX() > 0){
                        obj.setHealthPoint(obj.getHealthPoint() - ((Bullet) iterator).getDamage());
                        iterator.setDead(true);
                    }
                }

                if (obj.getHealthPoint() <= 0)
                    obj.setDead(true);
            }
        }
    }

    public BossMap getBossMap() {
        return bossMap;
    }


}
