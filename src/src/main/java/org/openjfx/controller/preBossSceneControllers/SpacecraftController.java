package org.openjfx.controller.preBossSceneControllers;

import org.openjfx.model.commonEntities.FiringBehavior.SpacecraftGun;
import org.openjfx.model.commonEntities.Spacecraft.Spacecraft;
import org.openjfx.model.preBossEntities.PreBossMap;
import org.openjfx.view.gameSceneView.preBossSceneView.PreBossMapView;

public class SpacecraftController {
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
                }
            }

            if (rightKeyPressed) {
                spacecraft.setDirectionLeft(false);
                if (spacecraft.getLocation().getPositionX() > preBossMapView.getSliderLeft() + 1750.0 - spacecraft.getHitBoxWidth()) {
                    spacecraft.getLocation().setPositionX(preBossMapView.getSliderLeft() + 1750.0 - spacecraft.getHitBoxWidth());
                    preBossMapView.setSliderLeft(preBossMapView.getSliderLeft() + 4);
                }
            }

            ((SpacecraftGun)spacecraft.getSpacecraftGun()).setFiring(fireKeyPressed);

            fireBullet();

            preBossMapView.refreshExplodeAnimations();

    }

    private void fireBullet(){
        spacecraft.getSpacecraftGun().gunTimer(preBossMap);
    }

    private void move(double accelerationSpeedChange, double constraint, double directionX, double directionY){

            spacecraft.setMoving(true);

            this.getPreBossMapView().setSliderAccelerationSpeed(this.getPreBossMapView().getSliderAccelerationSpeed() + accelerationSpeedChange); ;

            if (this.getPreBossMapView().getSliderAccelerationSpeed() < -constraint)
                this.getPreBossMapView().setSliderAccelerationSpeed( -constraint);
            if (this.getPreBossMapView().getSliderAccelerationSpeed() > constraint)
                this.getPreBossMapView().setSliderAccelerationSpeed(constraint);

            this.getPreBossMapView().setSliderLeft(this.getPreBossMapView().getSliderLeft() + this.getPreBossMapView().getSliderAccelerationSpeed());
            spacecraft.moveToDirection(spacecraft.getVelocity(), directionX, directionY);
    }

    private void activateSmartBomb(){

    }

    private void doHyperJump(){

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
