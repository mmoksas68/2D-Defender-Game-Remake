package org.openjfx.controller.bossSceneControllers;

import org.openjfx.controller.SoundController;
import org.openjfx.controller.bossSceneControllers.abilityBehaviourManager.AbilityBehaviourAlgorithm;
import org.openjfx.controller.bossSceneControllers.abilityBehaviourManager.DefaultAbilityAlgorithm;
import org.openjfx.controller.bossSceneControllers.abilityBehaviourManager.LittleBossAbilityAlgorithm;
import org.openjfx.model.bossEntities.BossAbility.SpecialAbility;
import org.openjfx.model.bossEntities.BossMap;
import org.openjfx.model.commonEntities.Bullet.Bullet;
import org.openjfx.model.preBossEntities.PreBossMap;

import java.util.Collections;

public class BossMapController {
    private BossMap bossMap;

    public BossMapController(BossMap bossMap) {
        this.bossMap = bossMap;
    }
    public void checkMapSituation() {
        for (var bullet : bossMap.getBullets().values()) {
         //   checkCollision(bullet, preBossMap.getEnemies());
         //   checkCollision(bullet, preBossMap.getStations());
         //   checkCollision(bullet, Collections.singletonMap(preBossMap.getSpacecraft1().getID(), preBossMap.getSpacecraft1()));

         //   if(!isSinglePlayer)
         //       checkCollision(bullet, Collections.singletonMap(preBossMap.getSpacecraft2().getID(), preBossMap.getSpacecraft2()));

            bullet.moveToDirection(bullet.getVelocity(), bullet.getDirectionX(), bullet.getDirectionY());
            bullet.setDistanceTravelled( bullet.getDistanceTravelled() + bullet.getVelocity());
            if(bullet.getDistanceTravelled() > BossMap.MAP_WIDTH)
            {
                bullet.setDead(true);
            }
        }

        for (SpecialAbility specialAbility : bossMap.getSpecialAbilities().values()) {
            specialAbility.getAbilityBehaviourAlgorithm().moveSpecialAbility( );
            double topSpacecraft = getBossMap().getSpacecraft1().getLocation().getPositionY();
            double bottomSpacecraft = topSpacecraft + getBossMap().getSpacecraft1().getHitBoxHeight();
            double topMarker = specialAbility.getLocation().getPositionY();
            double bottomMarker = topMarker + specialAbility.getHitBoxHeight();
            double leftSpacecraft = getBossMap().getSpacecraft1().getLocation().getPositionX();
            double rightSpacecraft = leftSpacecraft + getBossMap().getSpacecraft1().getHitBoxWidth();
            double leftMarker = specialAbility.getLocation().getPositionX();
            double rightMarker = leftMarker + specialAbility.getHitBoxWidth();

            if ( !(bottomSpacecraft < topMarker || topSpacecraft > bottomMarker ||
                    rightSpacecraft < leftMarker || leftSpacecraft > rightMarker)   ) {
                bossMap.getSpacecraft1().setHealthPoint(getBossMap().getSpacecraft1().getHealthPoint() - specialAbility.getDamage());
                System.out.println( bossMap.getSpacecraft1().getHealthPoint());
                if ( specialAbility.getDamage() != 0)
                SoundController.explosion();
            }


        }

    }

    public BossMap getBossMap() {
        return bossMap;
    }
}
