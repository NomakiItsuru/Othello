package itsu.research.othello.core;

import itsu.research.othello.Main;
import itsu.research.othello.enemy.IEnemy;

import java.util.Random;

/**
 * @author Nomaki Itsuru
 */

public class GameProvider {

    private static OthelloField othello;
    private static IEnemy enemy1;
    private static IEnemy enemy2;

    private static int whiteWinCount;
    private static int blackWinCount;

    public static void init(OthelloField field, IEnemy enemy1, IEnemy enemy2) {
        GameProvider.othello = field;
        GameProvider.enemy1 = enemy1;
        GameProvider.enemy2 = enemy2;
    }

    public static void start() {
        int random = new Random().nextInt(2);

        if (random == 0) {
            enemy1.set(othello);
        } else {
            enemy2.set(othello);
        }
    }

    public static void reset() {
        othello = null;
        enemy1 = null;
        enemy2 = null;
    }

    public static void onSet(IEnemy enemy, boolean isEnd) {
        if (isEnd) {
            int[] result = othello.getResult();
            if (Main.DEBUG_MODE) System.out.println("Result BLACK:" + result[0] + "-" + result[1] + ":WHITE");

            if (result[0] > result[1]) blackWinCount++;
            else whiteWinCount++;

            GameProvider.reset();

        } else {
            if (enemy.equals(enemy1)) {
                enemy2.set(othello);

            } else {
                enemy1.set(othello);
            }
        }

    }

    public static void printResult() {
        System.out.println("Finally result BLACK:" + blackWinCount + "-" + whiteWinCount + ":WHITE");
    }
}
