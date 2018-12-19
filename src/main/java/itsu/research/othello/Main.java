package itsu.research.othello;

import itsu.research.othello.core.GameProvider;
import itsu.research.othello.core.OthelloField;
import itsu.research.othello.enemy.*;

import java.util.concurrent.TimeUnit;

/**
 * @author Nomaki Itsuru
 */

public class Main {

    public static final int GAME_COUNT = 10000;
    public static final int FIELD_SIZE = 8;
    public static final boolean DEBUG_MODE = true;

    private static long startTime;

    public static void main(String args[]) {
        if (!(args.length > 0)) {
            System.out.println("You must specify arguments for --part1, --part2, --part3, --part4");
            System.exit(1);
        }

        System.out.println("Process started!: " + args[0].replaceFirst("--", ""));

        startTime = System.currentTimeMillis();

        IEnemy targetEnemy = null;

        switch (args[0].replaceFirst("--", "")) {
            case "part1": {
                targetEnemy = new RandomEnemy(OthelloField.WHITE);
                break;
            }

            case "part2": {
                targetEnemy = new MostCountEnemy(OthelloField.WHITE);
                break;
            }

            case "part3": {
                targetEnemy = new MostCountCornerEnemy(OthelloField.WHITE);
                break;
            }

            case "part4": {
                targetEnemy = new ScorebleEnemy(OthelloField.WHITE);
                break;
            }
        }

        for (int i = 0; i < GAME_COUNT; i++) {
            GameProvider.init(new OthelloField(FIELD_SIZE), new RandomEnemy(OthelloField.BLACK), targetEnemy);
            GameProvider.start();
        }

        GameProvider.printResult();

        long endTime = System.currentTimeMillis();
        System.out.println("Process successfully finished: " + (endTime - startTime) + "ms (" + TimeUnit.MILLISECONDS.toSeconds(endTime - startTime) + "s)");
    }

}
