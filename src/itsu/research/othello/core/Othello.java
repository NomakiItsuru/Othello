package itsu.research.othello.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Othello {

    public static final int NONE = 0;
    public static final int BLACK = 1;
    public static final int WHITE = 2;

    private Map<Integer, int[]> board;

    private boolean debug = false;

    public Othello() {
        board = new HashMap<>();
        for (int i = 0; i < 8; i++) {
            board.put(i, new int[]{NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE});
        }

        // Set first stone position
        setFirst(3, 3, WHITE);
        setFirst(4, 3, BLACK);
        setFirst(3, 4, BLACK);
        setFirst(4, 4, WHITE);
    }

    private String setFirst(int x, int y, int type) {
        board.get(y)[x] = type;
        return "X: " + x + " Y: " + y + " TYPE: " + type;
    }


    public String set(int x, int y, int type) {
        if (x > 7 || y > 7 || x < 0 || y < 0) {
            throw new IllegalArgumentException("You must specify the value from 1 to 8");
        }

        if (board.get(y)[x] != 0) {
            throw new IllegalArgumentException("Specified board is already used");
        }

        Map<String, Object> setData = isCanSetBoard(x, y, type);

        if (setData == null) return "Cannot place the stone.";

        board.get(y)[x] = type;
        int oppositeType = type == WHITE ? BLACK : WHITE;

        for (Map.Entry entry : setData.entrySet()) {
            if (((String) entry.getKey()).contains("Count")) continue;
            if (entry.getKey().equals("x") || entry.getKey().equals("y")) continue;
            if (!(boolean) entry.getValue()) continue;

            switch (String.valueOf(entry.getKey())) {
                case "left": {
                    int x1 = x;
                    while (true) {
                        x1--;

                        if (x1 < 0 || board.get(y)[x1] != oppositeType) break;
                        else board.get(y)[x1] = type;
                    }
                }

                case "right": {
                    int x1 = x;
                    while (true) {
                        x1++;

                        if (x1 > 7 || board.get(y)[x1] != oppositeType) break;
                        else board.get(y)[x1] = type;
                    }
                }

                case "up": {
                    int y1 = y;
                    while (true) {
                        y1--;

                        if (y1 < 0 || board.get(y1)[x] != oppositeType) break;
                        else board.get(y1)[x] = type;
                    }
                }

                case "down": {
                    int y1 = y;
                    while (true) {
                        y1++;

                        if (y1 < 0 || board.get(y1)[x] != oppositeType) break;
                        else board.get(y1)[x] = type;
                    }
                }

                case "leftUp": {
                    int x1 = x;
                    int y1 = y;
                    while (true) {
                        x1--;
                        y1--;

                        if (x1 < 0 || y1 < 0 || board.get(y1)[x1] != oppositeType) break;
                        else board.get(y1)[x1] = type;
                    }
                }

                case "rightUp": {
                    int x1 = x;
                    int y1 = y;
                    while (true) {
                        x1++;
                        y1--;

                        if (x1 > 7 || y1 < 0 || board.get(y1)[x1] != oppositeType) break;
                        else board.get(y1)[x1] = type;
                    }
                }

                case "leftDown": {
                    int x1 = x;
                    int y1 = y;
                    while (true) {
                        x1--;
                        y1++;

                        if (x1 < 0 || y1 > 7 || board.get(y1)[x1] != oppositeType) break;
                        else board.get(y1)[x1] = type;
                    }
                }

                case "rightDown": {
                    int x1 = x;
                    int y1 = y;
                    while (true) {
                        x1++;
                        y1++;

                        if (x1 > 7 || y1 > 7 || board.get(y1)[x1] != oppositeType) break;
                        else board.get(y1)[x1] = type;
                    }
                }
            }
        }
        
        return "X: " + x + " Y: " + y + " TYPE: " + type;
    }

    public List<Map<String, Object>> getCanSetBoards(int type) {
        List<Map<String, Object>> result = new ArrayList<>();

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Map<String, Object> data = isCanSetBoard(x, y, type);
                if (data != null) {
                    if (debug) System.out.println(x + ", " + y + "  " + data.toString());
                    result.add(data);
                }
            }
        }

        return result;
    }

    private Map<String, Object> isCanSetBoard(int x, int y, int type) {
        if (board.get(y)[x] != NONE) return null;

        Map<String, Object> data = new HashMap<>();
        Map<String, Object> copy = new HashMap<>();

        int oppositeType = type == BLACK ? WHITE : BLACK;

        if (x - 2 < 0 || board.get(y)[x - 1] != oppositeType) {
            data.put("left", false);
            data.put("leftCount", 0);
        }
        else data.put("left", true);

        if (x + 2 > 7 || board.get(y)[x + 1] != oppositeType) {
            data.put("right", false);
            data.put("rightCount", 0);
        }
        else data.put("right", true);

        if (y - 2 < 0 || board.get(y - 1)[x] != oppositeType) {
            data.put("up", false);
            data.put("upCount", 0);
        }
        else data.put("up", true);

        if (y + 2 > 7 || board.get(y + 1)[x] != oppositeType) {
            data.put("down", false);
            data.put("downCount", 0);
        }
        else data.put("down", true);

        if (x - 2 < 0 || y - 2 < 0 || board.get(y - 1)[x - 1] != oppositeType) {
            data.put("leftUp", false);
            data.put("leftUpCount", 0);
        }
        else data.put("leftUp", true);

        if (x + 2 > 7 || y - 2 < 0 || board.get(y - 1)[x + 1] != oppositeType) {
            data.put("rightUp", false);
            data.put("rightUpCount", 0);
        }
        else data.put("rightUp", true);

        if (x - 2 < 0 || y + 2 > 7 || board.get(y + 1)[x - 1] != oppositeType) {
            data.put("leftDown", false);
            data.put("leftDownCount", 0);
        }
        else data.put("leftDown", true);

        if (x + 2 > 7 || y + 2 > 7 || board.get(y + 1)[x + 1] != oppositeType) {
            data.put("rightDown", false);
            data.put("rightDownCount", 0);
        }
        else data.put("rightDown", true);

        if (!(boolean) data.get("left") && !(boolean) data.get("right") && !(boolean) data.get("up") && !(boolean) data.get("down")
                && !(boolean) data.get("leftUp") && !(boolean) data.get("rightUp") && !(boolean) data.get("leftDown") && !(boolean) data.get("rightDown")) {
            return null;
        }

        copy.putAll(data);

        for (Map.Entry entry : copy.entrySet()) {
            if (((String) entry.getKey()).contains("Count")) continue;
            if (!(boolean) entry.getValue()) continue;

            boolean isAvailable = false;
            int count = 0;

            switch ((String) entry.getKey()) {
                case "left": {
                    int x1 = x - 1;
                    int x2 = x1 - 1;

                    while(x2 > -1) {
                        if (board.get(y)[x2] == type) isAvailable = true;
                        x2--;
                    }

                    if (isAvailable) {
                        while (true) {
                            if (board.get(y)[x1] == oppositeType) count++;
                            else if (board.get(y)[x1] == type) break;
                            x1--;
                        }
                    }

                    break;
                }

                case "right": {
                    int x1 = x + 1;
                    int x2 = x1 + 1;

                    while(x2 < 8) {
                        if (board.get(y)[x2] == type) isAvailable = true;
                        x2++;
                    }

                    if (isAvailable) {
                        while (true) {
                            if (board.get(y)[x1] == oppositeType) count++;
                            else if (board.get(y)[x1] == type) break;
                            x1++;
                        }
                    }

                    break;
                }

                case "up": {
                    int y1 = y - 1;
                    int y2 = y1 - 1;

                    while(y2 > -1) {
                        if (board.get(y2)[x] == type) isAvailable = true;
                        y2--;
                    }

                    if (isAvailable) {
                        while (true) {
                            if (board.get(y1)[x] == oppositeType) count++;
                            else if (board.get(y1)[x] == type) break;
                            y1--;
                        }
                    }

                    break;
                }

                case "down": {
                    int y1 = y + 1;
                    int y2 = y1 + 1;

                    while(y2 < 8) {
                        if (board.get(y2)[x] == type) isAvailable = true;
                        y2++;
                    }

                    if (isAvailable) {
                        while (true) {
                            if (board.get(y1)[x] == oppositeType) count++;
                            else if (board.get(y1)[x] == type) break;
                            y1++;
                        }
                    }

                    break;
                }

                case "leftUp": {
                    int x1 = x - 1;
                    int x2 = x1 - 1;
                    int y1 = y - 1;
                    int y2 = y1 - 1;

                    while(true) {
                        if (x2 < 0 || y2 < 0) break;

                        if (board.get(y2)[x2] == type) isAvailable = true;
                        x2--;
                        y2--;
                    }

                    if (isAvailable) {
                        while (true) {
                            if (board.get(y1)[x1] == oppositeType) count++;
                            else if (board.get(y1)[x1] == type) break;
                            x1--;
                            y1--;
                        }
                    }

                    break;
                }

                case "rightUp": {
                    int x1 = x + 1;
                    int x2 = x1 + 1;
                    int y1 = y - 1;
                    int y2 = y1 - 1;

                    while(true) {
                        if (x2 > 7 || y2 < 0) break;

                        if (board.get(y2)[x2] == type) isAvailable = true;
                        x2++;
                        y2--;
                    }

                    if (isAvailable) {
                        while (true) {
                            if (board.get(y1)[x1] == oppositeType) count++;
                            else if (board.get(y1)[x1] == type) break;
                            x1++;
                            y1--;
                        }
                    }

                    break;
                }

                case "leftDown": {
                    int x1 = x - 1;
                    int x2 = x1 - 1;
                    int y1 = y + 1;
                    int y2 = y1 + 1;

                    while(true) {
                        if (x2 < 0 || y2 > 7) break;

                        if (board.get(y2)[x2] == type) isAvailable = true;
                        x2--;
                        y2++;
                    }

                    if (isAvailable) {
                        while (true) {
                            if (board.get(y1)[x1] == oppositeType) count++;
                            else if (board.get(y1)[x1] == type) break;
                            x1--;
                            y1++;
                        }
                    }

                    break;
                }

                case "rightDown": {
                    int x1 = x + 1;
                    int x2 = x1 + 1;
                    int y1 = y + 1;
                    int y2 = y1 + 1;

                    while(true) {
                        if (x2 > 7 || y2 > 7) break;

                        if (board.get(y2)[x2] == type) isAvailable = true;
                        x2++;
                        y2++;
                    }

                    if (isAvailable) {
                        while (true) {
                            if (board.get(y1)[x1] == oppositeType) count++;
                            else if (board.get(y1)[x1] == type) break;
                            x1++;
                            y1++;
                        }
                    }

                    break;
                }
            }

            data.put((String) entry.getKey(), isAvailable);
            data.put(entry.getKey() + "Count", isAvailable ? count : 0);
            data.put("x", x);
            data.put("y", y);

            if (!(boolean) data.get("left") && !(boolean) data.get("right") && !(boolean) data.get("up") && !(boolean) data.get("down")
                    && !(boolean) data.get("leftUp") && !(boolean) data.get("rightUp") && !(boolean) data.get("leftDown") && !(boolean) data.get("rightDown")) {
                return null;
            }
        }

        return data;
    }

    public int getCount(int x, int y, int type) {
        int count = 0;

        for (Map.Entry entry : isCanSetBoard(x, y, type).entrySet()) {
            if (!String.valueOf(entry.getKey()).contains("Count")) continue;
            count += Integer.parseInt(String.valueOf(entry.getValue()));
        }

        return count;
    }

    public int getSettableCounts() {
        int count = 0;

        for (Integer y : board.keySet()) {
            for (int x : (int[]) board.get(y)) {
                if (x == NONE) count++;
            }
        }

        return count;
    }

    public void debug() {
        System.out.println("   0  1  2  3  4  5  6  7");
        System.out.println("-------------------------");

        int num = 0;
        for (Map.Entry entry : board.entrySet()) {
            int[] y = (int[]) entry.getValue();
            System.out.println(num + "| " + y[0] + ", " + y[1] + ", " + y[2] + ", " + y[3] + ", " + y[4] + ", " + y[5] + ", " + y[6] + ", " + y[7]);
            num++;
        }

        System.out.println();
    }
}
