package itsu.research.othello.core;

import itsu.research.othello.enemy.RandomEnemy;

/**
 * 2018/8/13
 * H30 栃木高校　1年　野牧　樹
 * SSH 課題研究用
 *
 * @author Nomaki Itsuru
 */

public class Main {

    public static void main(String args[]) {
        Othello o = new Othello();

        if (!(args.length > 0)) {
            System.out.println("You must specify arguments for --part1, --part2, --part3, --part4");
            System.exit(1);
        }

        switch (args[0].replaceFirst("--", "")) {
            case "part1": {
                GameProvider.init(new Othello(), new RandomEnemy(Othello.BLACK), new RandomEnemy(Othello.WHITE));
                GameProvider.start();
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
