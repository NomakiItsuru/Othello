package itsu.research.othello.core;

import java.util.HashMap;
import java.util.Map;

public class Othello {

    /*
    0: none
    1: black
    2: white

    8x8
     */

    public static final int NONE = 0;
    public static final int BLACK = 1;
    public static final int WHITE = 2;

    private Map<Integer, int[]> board;

    public Othello() {
        board = new HashMap<>();
        for (int i = 0; i < 8; i++) {
            board.put(i, new int[]{NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE});
        }

        setFirst(4, 4, WHITE);
        setFirst(5, 4, BLACK);
        setFirst(4, 5, BLACK);
        setFirst(5, 5, WHITE);

        set(6, 3, WHITE);
        set(3, 6, WHITE);
        set(3, 3, BLACK);
        set(6, 6, BLACK);

        getCanSetBoards(WHITE);
        debug();

        set(6, 5, WHITE);
    }

    public String setFirst(int x,int y, int type) {
        board.get(y - 1)[x - 1] = type;
        return "X: " + x + " Y: " + y + " TYPE: " + type;
    }

    public String set(int x, int y, int type) {
        if (x > 8 || y > 8 || x < 1 || y < 1) {
            System.out.println("You must specify the value from 1 to 8");
            return null;
        }

        if (board.get(y - 1)[x - 1] != 0) {
            System.out.println("Specified board is already used");
            return null;
        }

        board.get(y - 1)[x - 1] = type;

        // horizontal
        int x1 = x;
        while (true) {
            x1--;

            if (x1 < 1) break;
            if (board.get(y - 1)[x1 - 1] == type || board.get(y - 1)[x1 - 1] == NONE) break;

            board.get(y - 1)[x1 - 1] = type;
        }

        int x2 = x;
        while (true) {
            x2++;

            if (x1 > 9) break;
            if (board.get(y - 1)[x2 - 1] == type || board.get(y - 1)[x2 - 1] == NONE) break;

            board.get(y - 1)[x2 - 1] = type;
        }

        // vertical
        int y1 = y;
        while (true) {
            y1--;

            if (y1 < 1) break;
            if (board.get(y1 - 1)[x - 1] == type || board.get(y1 - 1)[x - 1] == NONE) break;

            board.get(y1 - 1)[x - 1] = type;
        }

        int y2 = y;
        while (true) {
            y2++;

            if (y2 > 9) break;
            if (board.get(y2 - 1)[x - 1] == type || board.get(y2 - 1)[x - 1] == NONE) break;

            board.get(y2 - 1)[x - 1] = type;
        }

        // diagonal
        // left up
        int x3 = x;
        int y3 = y;
        while (true) {
            x3--;
            y3++;

            if (x3 < 1 || y3 > 9) break;
            if (board.get(y3 - 1)[x3 - 1] == type || board.get(y3 - 1)[x3 - 1] == NONE) break;

            board.get(y3 - 1)[x3 - 1] = type;
        }
        
        // right up
        int x4= x;
        int y4 = y;
        while (true) {
            x4++;
            y4--;

            if (x4 > 9 || y4 < 1) break;
            if (board.get(y4 - 1)[x4 - 1] == type || board.get(y4 - 1)[x4 - 1] == NONE) break;

            board.get(y4 - 1)[x4 - 1] = type;
        }
        
        // right down
        int x5= x;
        int y5 = y;
        while (true) {
            x5++;
            y5++;

            if (x5 > 9 || y5 > 9) break;
            if (board.get(y5 - 1)[x5 - 1] == type || board.get(y5 - 1)[x5 - 1] == NONE) break;

            board.get(y5 - 1)[x5 - 1] = type;
        }
        
        // left down
        int x6= x;
        int y6 = y;
        while (true) {
            x6--;
            y6--;

            if (x6 < 1 || y6 < 1) break;
            if (board.get(y6 - 1)[x6 - 1] == type || board.get(y6 - 1)[x6 - 1] == NONE) break;

            board.get(y6 - 1)[x6 - 1] = type;
        }
        
        return "X: " + x + " Y: " + y + " TYPE: " + type;
    }

    /*
    int [0] = x
    int [1] = y
    int [2] = direction

    direction
    0: left
    1: up
    2: right
    3: down
    4: cannot place
     */
    public Map<Integer, Map<String, Object>> getCanSetBoards(int type) {
        Map<Integer, Map<String, Object>> result = new HashMap<>();
        int index = 0;

        for (int x = 1; x < 9; x++) {
            for (int y = 1; y < 9; y++) {
                Map<String, Object> data = isCanSetBoard(x, y, type);
                if (data != null) {
                    result.put(index, data);
                    index++;
                }
            }
        }

        return result;
    }

    private Map<String, Object> isCanSetBoard(int x, int y, int type) {
        if (board.get(y - 1)[x - 1] != 0) return null;

        Map<String, Object> data = new HashMap<>();
        Map<String, Object> copy = new HashMap<>();

        if (x - 2 < 0 || board.get(y - 1)[x - 2] == NONE) {
            data.put("left", false);
            data.put("leftCount", 0);
        }
        else data.put("left", true);

        if (x > 7 || board.get(y - 1)[x] == NONE) {
            data.put("right", false);
            data.put("rightCount", 0);
        }
        else data.put("right", true);

        if (y - 2 < 0 || board.get(y - 2)[x - 1] == NONE) {
            data.put("up", false);
            data.put("upCount", 0);
        }
        else data.put("up", true);

        if (y > 7 || board.get(y)[x - 1] == NONE) {
            data.put("down", false);
            data.put("downCount", 0);
        }
        else data.put("down", true);

        if (!(boolean) data.get("left") && !(boolean) data.get("right") && !(boolean) data.get("up") && !(boolean) data.get("down")) {
            return null;
        }

        copy.putAll(data);

        for (Map.Entry entry : copy.entrySet()) {
            if (((String) entry.getKey()).contains("Count")) continue;
            if (!(boolean) entry.getValue()) continue;

            boolean isAvailable = false;
            int oppositeType = type == BLACK ? WHITE : BLACK;
            int count = 0;

            switch ((String) entry.getKey()) {
                case "left": {
                    int x1 = x;

                    if (x - 2 < 0) break;
                    if (board.get(y - 1)[x - 2] == type) break;

                    while (true) {
                        x1--;
                        if (x1 - 1 < 0) break;

                        count++;

                        if (board.get(y - 1)[x1 - 1] == type) {
                            isAvailable = true;
                            break;
                        }

                        if (board.get(y - 1)[x1 - 1] == NONE) break;
                    }

                    break;
                }

                case "right": {
                    int x1 = x;

                    if (x > 7) break;
                    if (board.get(y - 1)[x] == type) break;

                    while (true) {
                        x1++;
                        if (x1 > 7) break;

                        count++;

                        if (board.get(y - 1)[x1 - 1] == type) {
                            isAvailable = true;
                            break;
                        }

                        if (board.get(y - 1)[x1 - 1] == NONE) break;
                    }

                    break;
                }

                case "up": {
                    int y1 = y;

                    if (y - 2 < 0) break;
                    if (board.get(y - 2)[x - 1] == type) break;

                    while (true) {
                        y1--;
                        if (y1 - 1 < 0) break;

                        count++;

                        if (board.get(y1 - 1)[x - 1] == type) {
                            isAvailable = true;
                            break;
                        }

                        if (board.get(y1 - 1)[x - 1] == NONE) break;
                    }

                    break;
                }

                case "down": {
                    int y1 = y;

                    if (y > 7) break;
                    if (board.get(y)[x - 1] == type) break;

                    while (true) {
                        y1++;
                        if (y1 > 7) break;

                        count++;

                        if (board.get(y1 - 1)[x - 1] == type) {
                            isAvailable = true;
                            break;
                        }

                        if (board.get(y1 - 1)[x - 1] == NONE) break;
                    }

                    break;
                }
            }

            data.put((String) entry.getKey(), isAvailable);
            data.put(entry.getKey() + "Count", isAvailable ? count - 1 : 0);

            if (!(boolean) data.get("left") && !(boolean) data.get("right") && !(boolean) data.get("up") && !(boolean) data.get("down")) {
                return null;
            }
        }

        System.out.println(x + ", " + y + "  " + data.toString());

        return data;
    }

    public int[] getCounts() {
        int black = 0;
        int white = 0;

        for (Map.Entry entry : board.entrySet()) {
            int[] value = (int[]) entry.getValue();
            for (int i : value) {
                switch (i) {
                    case BLACK: {
                        black++;
                        break;
                    }

                    case WHITE: {
                        white++;
                        break;
                    }
                }
            }
        }

        return new int[] {black, white};
    }

    public void debug() {
        System.out.println("  1  2  3  4  5  6  7  8");
        System.out.println("------------------------");

        int num = 1;
        for (Map.Entry entry : board.entrySet()) {
            int[] y = (int[]) entry.getValue();
            System.out.println(num + "|" + y[0] + ", " + y[1] + ", " + y[2] + ", " + y[3] + ", " + y[4] + ", " + y[5] + ", " + y[6] + ", " + y[7]);
            num++;
        }

        System.out.println();
    }
}
