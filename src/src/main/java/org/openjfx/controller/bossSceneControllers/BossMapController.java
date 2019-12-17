package org.openjfx.controller.bossSceneControllers;

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
    private AbilityBehaviourAlgorithm abilityBehaviourAlgorithm;
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
        }

    }

    public BossMap getBossMap() {
        return bossMap;
    }
}
