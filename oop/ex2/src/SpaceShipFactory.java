public class SpaceShipFactory {
    public static SpaceShip[] createSpaceShips(String[] args) {

        SpaceShip[] spaceShips = new SpaceShip[args.length];
        for (int i = 0; i < args.length; i++){
            switch (args[i]){
                case "r":
                    spaceShips[i] = new RunnerSpaceship();
                    break;
//                case "b":
//                    spaceShips[i] = new BasherSpaceship();
//                    break;
//                case "a":
//                    spaceShips[i] = new AggressiveSpaceship();
//                    break;
//                case "d":
//                    spaceShips[i] = new DrunkardSpaceship();
//                    break;
//                case "s":
//                    spaceShips[i] = new SpecialSpaceship();
//                    break;
//                case "h":
//                    spaceShips[i] = new HumanSpaceship();
//                    break;
            }
        }

        return spaceShips;
    }
}
