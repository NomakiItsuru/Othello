package itsu.research.othello.core;

import itsu.research.othello.enemy.Enemy;

import java.util.Random;

public class GameProvider {

    private static OthelloField othello;
    private static Enemy enemy1;
    private static Enemy enemy2;

    private static int whiteWinCount;
    private static int blackWinCount;

    public static void init(OthelloField field, Enemy enemy1, Enemy enemy2) {
        GameProvider.othello = field;
        GameProvider.enemy1 = enemy1;
        GameProvider.enemy2 = enemy2;
    }

    public static void start() {
        int random = new Random().nextInt(1);

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

    public static void onSet(Enemy enemy, boolean isEnd) {
        if (isEnd) {
            //System.out.println("Program ended.");
            //othello.debug();

            int[] result = othello.getResult();
            System.out.println("Result WHITE:" + result[0] + "-" + result[1] + ":BLACK");

            if (result[0] > result[1]) whiteWinCount++;
            else blackWinCount++;

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
        System.out.println("Finally result WHITE:" + whiteWinCount + "-" + blackWinCount + ":BLACK");
    }
}
