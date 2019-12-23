package org.openjfx.controller.preBossSceneControllers;

import javafx.geometry.Pos;
import org.openjfx.model.bossEntities.BossMap;
import org.openjfx.model.commonEntities.Buff.BuffTypes;
import org.openjfx.model.commonEntities.FiringBehavior.SpacecraftGun;
import org.openjfx.model.commonEntities.Location;
import org.openjfx.model.commonEntities.Spacecraft.Spacecraft;
import org.openjfx.model.preBossEntities.PreBossMap;
import org.openjfx.utilization.PositionHelper;
import org.openjfx.view.gameSceneView.bossSceneView.BossMapView;
import org.openjfx.view.gameSceneView.preBossSceneView.PreBossMapView;

public class SpacecraftController {
    private BossMapView bossMapView;
    private BossMap bossMap;
    private PreBossMapView preBossMapView;
    private PreBossMap preBossMap;
    private Spacecraft spacecraft;
    private boolean upKeyPressed = false;
    private boolean downKeyPressed = false;
    private boolean leftKeyPressed = false;
    private boolean rightKeyPressed = false;
    private boolean fireKeyPressed = false;
    private boolean smartBombKeyPressed = false;
    private boolean hyperJumpKeyPressed = false;

    public SpacecraftController(Spacecraft spacecraft, PreBossMapView preBossMapView, PreBossMap preBossMap) {
        this.spacecraft = spacecraft;
        this.preBossMapView = preBossMapView;
        this.preBossMap = preBossMap;
        this.preBossMapView.setSliderLeft(spacecraft.getLocation().getPositionX()-940);
        bossMap = null;
    }

    public SpacecraftController (Spacecraft spacecraft, BossMapView bossMapView, BossMap bossMap) {
        this.spacecraft = spacecraft;
        this.bossMapView = bossMapView;
        this.bossMap = bossMap;
        preBossMap = null;
    }

    public void getInputs() {
        double [] boundArray = { rightKeyPressed ? 1 : 0, leftKeyPressed ? -1 : 0, upKeyPressed ? 1 : 0, downKeyPressed ? -1 : 0};
        checkBoundaryBossMap( boundArray);
        double xDirection = boundArray[0] + boundArray[1];
        double yDirection = boundArray[2] + boundArray [3];
        double multiplier = 1 / Math.sqrt( Math.pow( xDirection,2) + Math.pow( yDirection, 2));
        if ( xDirection != 0 || yDirection != 0) {
            spacecraft.setMoving(true);
            spacecraft.moveToDirection(bossMap.getSpacecraft1().getVelocity(),xDirection * multiplier, yDirection * multiplier);
        } else {
            spacecraft.setMoving(false);
        }

        ((SpacecraftGun)spacecraft.getSpacecraftGun()).setFiring(fireKeyPressed);

        fireBullet();


    }
    public void checkInputs(){

            spacecraft.setMoving(false);

            if (upKeyPressed && leftKeyPressed) {
                move( -0.04 , (spacecraft.getVelocity() / Math.sqrt(2)), -1, 1);
            } else if (upKeyPressed && rightKeyPressed) {
                move(0.04, (spacecraft.getVelocity()  / Math.sqrt(2)), 1, 1);
            } else if (downKeyPressed && leftKeyPressed) {
                move(-0.04, (spacecraft.getVelocity()  / Math.sqrt(2)), -1, -1);
            } else if (downKeyPressed && rightKeyPressed) {
                move(0.04, (spacecraft.getVelocity()  / Math.sqrt(2)), 1, -1);
            } else if (upKeyPressed) {
                move(0.0, spacecraft.getVelocity() , 0, 1);
            } else if (downKeyPressed) {
                move(0.0, spacecraft.getVelocity() , 0, -1);
            } else if (leftKeyPressed) {
                move(-0.04, spacecraft.getVelocity() , -1, 0);
            } else if (rightKeyPressed) {
                move(0.04, spacecraft.getVelocity() , 1, 0);
            }


                if (!leftKeyPressed && !rightKeyPressed) {
                    preBossMapView.setSliderAccelerationSpeed(0);
                    if (spacecraft.getLocation().getPositionX() - preBossMapView.getSliderLeft() > 941) {
                        if (spacecraft.getLocation().getPositionX() - preBossMapView.getSliderLeft() > 944) {
                            preBossMapView.setSliderLeft(preBossMapView.getSliderLeft() + 5);
                        } else
                            preBossMapView.setSliderLeft(spacecraft.getLocation().getPositionX() - 940);
                    }

                    if (spacecraft.getLocation().getPositionX() - preBossMapView.getSliderLeft() < 939) {
                        if (spacecraft.getLocation().getPositionX() - preBossMapView.getSliderLeft() < 936) {
                            preBossMapView.setSliderLeft(preBossMapView.getSliderLeft() - 5);
                        } else
                            preBossMapView.setSliderLeft(spacecraft.getLocation().getPositionX() - 940);
                    }
                }

                if (leftKeyPressed) {
                    spacecraft.setDirectionLeft(true);
                    if (spacecraft.getLocation().getPositionX() < preBossMapView.getSliderLeft() + 170.0) {
                        spacecraft.getLocation().setPositionX(preBossMapView.getSliderLeft() + 170.0);
                        preBossMapView.setSliderLeft(preBossMapView.getSliderLeft() - 4);
                        preBossMapView.setSliderAccelerationSpeed(-(spacecraft.getVelocity() / Math.sqrt(2)));
                    }
                }

                if (rightKeyPressed) {
                    spacecraft.setDirectionLeft(false);
                    if (spacecraft.getLocation().getPositionX() > preBossMapView.getSliderLeft() + 1750.0 - spacecraft.getHitBoxWidth()) {
                        spacecraft.getLocation().setPositionX(preBossMapView.getSliderLeft() + 1750.0 - spacecraft.getHitBoxWidth());
                        preBossMapView.setSliderLeft(preBossMapView.getSliderLeft() + 4);
                        preBossMapView.setSliderAccelerationSpeed((spacecraft.getVelocity() / Math.sqrt(2)));
                    }
                }


            ((SpacecraftGun)spacecraft.getSpacecraftGun()).setFiring(fireKeyPressed);

            fireBullet();

            preBossMapView.refreshExplodeAnimations();

    }
    private void fireBullet(){
        if ( bossMap == null)
            spacecraft.getSpacecraftGun().gunTimer(preBossMap);
        else
            spacecraft.getSpacecraftGun().gunTimer(bossMap);
    }

    private void move(double accelerationSpeedChange, double constraint, double directionX, double directionY){

        PositionHelper positionHelper = new PositionHelper(spacecraft);
        boolean outTop = positionHelper.getTop()-1 < 0;
        boolean outBottom = positionHelper.getBottom()+1 > PreBossMap.MAP_HEIGHT;
        boolean outRight = positionHelper.getRight()+1 > PreBossMap.MAP_WIDTH;
        boolean outLeft = positionHelper.getLeft()-1 < 0;
        if (outTop){
            if (directionY == 1){
                directionY = 0;
            }
        }
        if (outBottom){
            if (directionY == -1){
                directionY = 0;
            }
        }
        if (outLeft){
            if (directionX == -1){
                directionX = 0;
                leftKeyPressed = false;
            }
        }
        if (outRight){
            if (directionX == 1){
                directionX = 0;
                rightKeyPressed = false;
            }
        }

        spacecraft.setMoving(true);

        this.getPreBossMapView().setSliderAccelerationSpeed(this.getPreBossMapView().getSliderAccelerationSpeed() + accelerationSpeedChange); ;

        if (this.getPreBossMapView().getSliderAccelerationSpeed() < -constraint)
            this.getPreBossMapView().setSliderAccelerationSpeed( -constraint);
        if (this.getPreBossMapView().getSliderAccelerationSpeed() > constraint)
            this.getPreBossMapView().setSliderAccelerationSpeed(constraint);

        this.getPreBossMapView().setSliderLeft(this.getPreBossMapView().getSliderLeft() + this.getPreBossMapView().getSliderAccelerationSpeed());
        spacecraft.moveToDirection(spacecraft.getVelocity(), directionX, directionY);
    }

    public void activateSmartBomb(){
        if( bossMap == null){
            if(spacecraft.getSmartBombStock() > 0){
                spacecraft.setSmartBombStock(spacecraft.getSmartBombStock()-1);
                PositionHelper spacecraftHelper = new PositionHelper(spacecraft);
                for(var enemy : preBossMap.getEnemies().values()){
                    PositionHelper enemyHelper = new PositionHelper(enemy);
                    if(PositionHelper.isInRadar(enemyHelper, spacecraftHelper, Spacecraft.SMARTBOMB_RADIUS)){
                        enemy.setHealthPoint(enemy.getHealthPoint()-Spacecraft.SMARTBOMB_DAMAGE);
                        if(enemy.getHealthPoint() <= 0){
                            enemy.setDead(true);
                        }
                    }
                }
                for(var station : preBossMap.getStations().values()){
                    PositionHelper stationHelper = new PositionHelper(station);
                    if(PositionHelper.isInRadar(stationHelper, spacecraftHelper, Spacecraft.SMARTBOMB_RADIUS)){
                        station.setHealthPoint(station.getHealthPoint()-Spacecraft.SMARTBOMB_DAMAGE);
                        if(station.getHealthPoint() <= 0){
                            station.setDead(true);
                        }
                    }
                }
            }
        }
        else {
            if(spacecraft.getSmartBombStock() > 0){
                spacecraft.setSmartBombStock(spacecraft.getSmartBombStock()-1);
                bossMap.getBoss().setHealthPoint( bossMap.getBoss().getHealthPoint() - Spacecraft.SMARTBOMB_DAMAGE);
                if( bossMap.getBoss().getHealthPoint() <= 0){
                    bossMap.getBoss().setDead(true);
                }
            }
        }
    }

    public void doHyperJump(){
        if(spacecraft.getHyperJumpBattery() > 25){
            if(spacecraft.isDirectionLeft() && (spacecraft.getLocation().getPositionX()- 20 * spacecraft.getHyperJumpBattery() > 0)){
                spacecraft.setLocation(new Location(spacecraft.getLocation().getPositionX() - 20 * spacecraft.getHyperJumpBattery(), spacecraft.getLocation().getPositionY()));
                preBossMapView.setSliderLeft(preBossMapView.getSliderLeft() - 20 * spacecraft.getHyperJumpBattery());
                spacecraft.setHyperJumpBattery(0);
            }
            else if (!spacecraft.isDirectionLeft() && spacecraft.getLocation().getPositionX() + 20 * spacecraft.getHyperJumpBattery() < PreBossMap.MAP_WIDTH){
                spacecraft.setLocation(new Location(spacecraft.getLocation().getPositionX() + 20 * spacecraft.getHyperJumpBattery(), spacecraft.getLocation().getPositionY()));
                preBossMapView.setSliderLeft(preBossMapView.getSliderLeft() + 20 * spacecraft.getHyperJumpBattery());
                spacecraft.setHyperJumpBattery(0);
            }
        }
    }

    public void checkBoundaryBossMap ( double [] array) {
            if (spacecraft.getLocation().getPositionX() + spacecraft.getHitBoxWidth() >= (3*BossMap.MAP_WIDTH) /4) array[0] = 0;
            if (spacecraft.getLocation().getPositionX() <= 0) array[1] = 0;
            if (spacecraft.getLocation().getPositionY() <= 0) array[2] = 0;
            if (spacecraft.getLocation().getPositionY() + spacecraft.getHitBoxHeight()>= BossMap.MAP_HEIGHT) array[3] = 0;
    }

    public void applyBuff(BuffTypes type){
        switch (type){
            case EMPTY:
                break;
            case SPEED:
                spacecraft.setVelocity(spacecraft.getVelocity() + Spacecraft.INIT_VELOCITY/4);
                 break;
            case SMART_BOMB:
                spacecraft.setSmartBombStock(spacecraft.getSmartBombStock() + 1);
                break;
            case HEALTH:
                spacecraft.setHealthPoint(spacecraft.getHealthPoint() + Spacecraft.HEALTH_INCREASE);
                break;
            case HYPER_JUMP:
                spacecraft.setHyperJumpBattery(spacecraft.getHyperJumpBattery() + Spacecraft.HYPERJUMP_ENERGY_BUFF);
                break;
            case FIRE_RATE:
                ((SpacecraftGun)spacecraft.getSpacecraftGun()).increaseFrequency();
                break;
            case GUN_POWER:
                ((SpacecraftGun)spacecraft.getSpacecraftGun()).increaseDamage();
                break;
            case GUN_TYPE:
                spacecraft.developGun();
                break;
            default:
                break;
        }
    }

    public BossMapView getBossMapView() {
        return bossMapView;
    }

    public BossMap getBossMap() {
        return bossMap;
    }

    public PreBossMapView getPreBossMapView() {
        return preBossMapView;
    }

    public void setPreBossMapView(PreBossMapView preBossMapView) {
        this.preBossMapView = preBossMapView;
    }

    public PreBossMap getPreBossMap() {
        return preBossMap;
    }

    public void setPreBossMap(PreBossMap preBossMap) {
        this.preBossMap = preBossMap;
    }

    public Spacecraft getSpacecraft() {
        return spacecraft;
    }

    public void setSpacecraft(Spacecraft spacecraft) {
        this.spacecraft = spacecraft;
    }

    public boolean isUpKeyPressed() {
        return upKeyPressed;
    }

    public void setUpKeyPressed(boolean upKeyPressed) {
        this.upKeyPressed = upKeyPressed;
    }

    public boolean isDownKeyPressed() {
        return downKeyPressed;
    }

    public void setDownKeyPressed(boolean downKeyPressed) {
        this.downKeyPressed = downKeyPressed;
    }

    public boolean isLeftKeyPressed() {
        return leftKeyPressed;
    }

    public void setLeftKeyPressed(boolean leftKeyPressed) {
        this.leftKeyPressed = leftKeyPressed;
    }

    public boolean isRightKeyPressed() {
        return rightKeyPressed;
    }

    public void setRightKeyPressed(boolean rightKeyPressed) {
        this.rightKeyPressed = rightKeyPressed;
    }

    public boolean isFireKeyPressed() {
        return fireKeyPressed;
    }

    public void setFireKeyPressed(boolean fireKeyPressed) {
        this.fireKeyPressed = fireKeyPressed;
    }

    public boolean isSmartBombKeyPressed() {
        return smartBombKeyPressed;
    }

    public void setSmartBombKeyPressed(boolean smartBombKeyPressed) {
        this.smartBombKeyPressed = smartBombKeyPressed;
    }

    public boolean isHyperJumpKeyPressed() {
        return hyperJumpKeyPressed;
    }

    public void setHyperJumpKeyPressed(boolean hyperJumpKeyPressed) {
        this.hyperJumpKeyPressed = hyperJumpKeyPressed;
    }
}
