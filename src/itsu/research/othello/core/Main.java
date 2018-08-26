package itsu.research.othello.core;

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
        o.debug();
        System.out.println(o.getCounts()[0] + ", " + o.getCounts()[1]);

        if (!(args.length > 0)) {
            System.out.println("Arguments count must be one");
            System.exit(1);
        }

        switch (args[0]) {
            case "part1": {
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
