package itsu.research.othello.core;

import itsu.research.othello.enemy.Enemy;

import java.util.Random;

public class GameProvider {

    private static Othello othello;
    private static Enemy enemy1;
    private static Enemy enemy2;

    public static void init(Othello othello, Enemy enemy1, Enemy enemy2) {
        GameProvider.othello = othello;
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

    public static void onSet(Enemy enemy) {
        if (othello.getSettableCounts() == 0) {
            System.out.println("Program ended.");
            othello.debug();
        }

        if (enemy.equals(enemy1)) {
            enemy2.set(othello);
        } else {
            enemy1.set(othello);
        }
    }
}
