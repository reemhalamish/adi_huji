public class RunnerSpaceship extends SpaceShip {

    private static final boolean ALWAYS_ACCELERATE = true;

    @Override
    public void doSpecificAction(SpaceWars game){
        SpaceShip closestShip = game.getClosestShipTo(this);
        double angleToFaceMe = closestShip.physics.angleTo(physics);
        double distanceBetweenShips = physics.distanceFrom(closestShip.physics);
        if (distanceBetweenShips < 0.25 && -0.23 < angleToFaceMe && angleToFaceMe < 0.23){
            teleport();
            SpaceShip newClosestSpaceShip = game.getClosestShipTo(this);
            physics.move(ALWAYS_ACCELERATE,
                    moveOppositeDirection(moveDirectionToFaceEnemy(newClosestSpaceShip.physics)));
            return;
        } else {
            physics.move(ALWAYS_ACCELERATE,
                    moveOppositeDirection(moveDirectionToFaceEnemy(closestShip.physics)));
        }
    }
}
