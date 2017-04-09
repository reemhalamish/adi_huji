import java.awt.*;

import oop.ex2.*;

/**
 * The API spaceships need to implement for the SpaceWars game. 
 * It is your decision whether SpaceShip.java will be an interface, an abstract class,
 *  a base class for the other spaceships or any other option you will choose.
 *  
 * @author oop
 */
public abstract class SpaceShip{

    /* constants */

    private static final int START_HEALTH = 25;
    private static final int START_MAX_ENERGY_LEVEL = 250;
    private static final int START_CUR_ENERGY_LEVEL = 200;
    private static final int MIN_HEALTH = 0;
    private static final int ENERGY_TO_FIRE_SHOT = 15;
    private static final int ENERGY_HIT_BY_SHOT = 2;
    private static final int MAX_ENERGY_LOSS_BY_SHOT = 5;
    private static final int CUR_ENERGY_LOSS_BY_SHOT = 10;
    private static final int RESET_ROUNDS_BETWEEN_SHOTS = 7;
    protected static final int MOVE_RIGHT = -1;
    protected static final int MOVE_LEFT = 1;
    protected static final int MOVE_FORWARD = 0;
    private static final int ENERGY_TO_TELEPORT = 100;
    private static final int ENERGY_TO_ACTIVATE_SHIELD = 3;



    protected SpaceShipPhysics physics;
    protected int maxEnergyLevel, curEnergyLevel, health;
    private boolean shieldOn;
    private int counterBetweenShots;

    protected SpaceShip(){
        physics = new SpaceShipPhysics();
        health = START_HEALTH;
        maxEnergyLevel = START_MAX_ENERGY_LEVEL;
        curEnergyLevel = START_CUR_ENERGY_LEVEL;
    }
   
    /**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * 
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game){
            shieldOn = false;
            counterBetweenShots -= 1;
            doSpecificAction(game);
    }

    protected abstract void doSpecificAction(SpaceWars game);

    /**
     * This method is called every time a collision with this ship occurs 
     */
    public void collidedWithAnotherShip(){

    }

    /** 
     * This method is called whenever a ship has died. It resets the ship's 
     * attributes, and starts it at a new random position.
     */
    public void reset(){

    }

    /**
     * Checks if this ship is dead.
     * 
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        return health <= MIN_HEALTH;
        // smaller or equal to zero in case a ship with 1 health left is shot and bashed at the same time
    }

    /**
     * Gets the physics object that controls this ship.
     * 
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return physics;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets shot at (with or without a shield).
     */
    public void gotShot() {
        if(shieldOn && curEnergyLevel >= ENERGY_HIT_BY_SHOT) {
            curEnergyLevel -= ENERGY_HIT_BY_SHOT;
        }
        else{
            maxEnergyLevel -= MAX_ENERGY_LOSS_BY_SHOT;
            curEnergyLevel -= CUR_ENERGY_LOSS_BY_SHOT;
            health -= 1;
        }


    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     * 
     * @return the image of this ship.
     */
    public Image getImage(){
        // todo if is dead retun another image. if shield on return another...
        return GameGUI.SPACESHIP_IMAGE_SHIELD;
    }

    /**
     * Attempts to fire a shot.
     * 
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
       if (curEnergyLevel >= ENERGY_TO_FIRE_SHOT && counterBetweenShots <= 0){
           game.addShot(physics);
           curEnergyLevel -= ENERGY_TO_FIRE_SHOT;
           counterBetweenShots = RESET_ROUNDS_BETWEEN_SHOTS;
       }
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
        if (curEnergyLevel >= ENERGY_TO_ACTIVATE_SHIELD) {
            shieldOn = true;
            curEnergyLevel -= ENERGY_TO_ACTIVATE_SHIELD;
        }
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
        if (curEnergyLevel >= ENERGY_TO_TELEPORT){
            physics =  new SpaceShipPhysics();
            curEnergyLevel -= ENERGY_TO_TELEPORT;
        }
    }

    /**
     * checks which direction the ship needs to move in order to face the given enemy ship
     * @param other physics object of the other ship
     * @return direction to move - MOVE_RIGHT, MOVE_LEFT or MOVE_FORWARD
     */
    protected int moveDirectionToFaceEnemy(SpaceShipPhysics other){
        double angleToFaceEnemy = physics.angleTo(other);
        if (angleToFaceEnemy == 0) { return MOVE_FORWARD;}
        if (angleToFaceEnemy < 0) { return MOVE_RIGHT;}
        else /*(angleToFaceEnemy > 0)*/ { return MOVE_LEFT;}
    }

    /**
     * flips the direction
     * @param direction given direction
     * @return returns the opposite
     */
    protected int moveOppositeDirection(int direction){
        return -1*direction;
    }
}
