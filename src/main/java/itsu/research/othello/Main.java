package itsu.research.othello;

import itsu.research.othello.core.GameProvider;
import itsu.research.othello.core.OthelloField;
import itsu.research.othello.enemy.RandomEnemy;

/**
 * @author Nomaki Itsuru
 */

public class Main {

    public static void main(String args[]) {
        if (!(args.length > 0)) {
            System.out.println("You must specify arguments for --part1, --part2, --part3, --part4");
            System.exit(1);
        }

        switch (args[0].replaceFirst("--", "")) {
            case "part1": {
                for (int i = 0; i < 1000; i++) {
                    GameProvider.init(new OthelloField(8), new RandomEnemy(OthelloField.BLACK), new RandomEnemy(OthelloField.WHITE));
                    GameProvider.start();
                }
                GameProvider.printResult();
                break;
            }

            case "part2": {
                break;
            }

            case "part3": {
                break;
            }

            case "part4": {
                break;
            }
        }

        System.out.println("Process ended.");
    }
}
