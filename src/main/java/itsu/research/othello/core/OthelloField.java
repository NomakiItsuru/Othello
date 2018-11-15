package itsu.research.othello.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Nomaki Itsuru
 */

public class OthelloField {

    public static final int EMPTY = 0;
    public static final int BLACK = 1;
    public static final int WHITE = 2;

    private int size;
    private List<Stone> field;

    public OthelloField(int size) {
        if (size % 2 == 1) {
            throw new IllegalArgumentException("size must be even number.");
        }

        this.size = size;

        field = new ArrayList<>((int) Math.pow(size, 2));
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                field.add(new Stone(x, y, EMPTY));
            }
        }
        setStone(size / 2 - 1, size / 2 - 1, WHITE);
        setStone(size / 2, size / 2 - 1, BLACK);
        setStone(size / 2 - 1, size / 2, BLACK);
        setStone(size / 2, size / 2, WHITE);
    }

    public Stone getStone(int x, int y) {
        for (Stone stone : field) {
            Stone.Position position = stone.getPosition();
            if (position.getX() == x && position.getY() == y) {
                return stone;
            }
        }
        return null;
    }

    public void setStone(int x, int y, int type) {
        getStone(x, y).setType(type);
    }

    public void change(int x, int y, int type, List<Stone> stones) {
        setStone(x, y, type);

        for (Stone stone : stones) {
            setStone(stone.getPosition().getX(), stone.getPosition().getY(), type);
        }
    }

    public SettableResult isSettable(int x, int y, int type) {
        int opposite = (type == BLACK ? WHITE : BLACK);
        int mostAmount = 0;
        boolean isSettable = false;
        Stone base = getStone(x, y);
        List<Stone> settableStones = new ArrayList<>();

        if (base.getType() != EMPTY) return new SettableResult(false, base, 0, Collections.EMPTY_LIST);

        //Left
        if (getStone(x - 1, y) != null && getStone(x - 1, y).getType() == opposite) {
            int amount = 0;
            boolean isAvailable = false;

            for (int i = x - 2; i >= 0; i--) {
                if (getStone(i, y).getType() == type) {
                    isAvailable = true;
                    break;
                }
            }

            if (isAvailable) {
                for (int i = x - 1; i >= 0; i--) {
                    Stone s = getStone(i, y);
                    if (s.getType() == opposite) {
                        amount++;
                        settableStones.add(s);
                    }

                    else break;
                }

                mostAmount = amount;
                isSettable = true;
            }
        }

        //Right
        if (getStone(x + 1, y) != null && getStone(x + 1, y).getType() == opposite) {
            int amount = 0;
            boolean isAvailable = false;

            for (int i = x + 2; i <= size - 1; i++) {
                if (getStone(i, y).getType() == type) {
                    isAvailable = true;
                    break;
                }
            }

            if (isAvailable) {
                for (int i = x + 1; i <= size - 1; i++) {
                    Stone s = getStone(i, y);
                    if (s.getType() == opposite) {
                        amount++;
                        settableStones.add(s);
                    }

                    else break;
                }

                mostAmount = amount;
                isSettable = true;
            }
        }
        
        //UP
        if (getStone(x, y - 1) != null && getStone(x, y - 1).getType() == opposite) {
            int amount = 0;
            boolean isAvailable = false;

            for (int i = y - 2; i >= 0; i--) {
                if (getStone(x, i).getType() == type) {
                    isAvailable = true;
                    break;
                }
            }

            if (isAvailable) {
                for (int i = y - 1; i >= 0; i--) {
                    Stone s = getStone(x, i);
                    if (s.getType() == opposite) {
                        amount++;
                        settableStones.add(s);
                    }

                    else break;
                }

                mostAmount = amount;
                isSettable = true;
            }
        }
        
        //DOWN
        if (getStone(x, y + 1) != null && getStone(x, y + 1).getType() == opposite) {
            int amount = 0;
            boolean isAvailable = false;

            for (int i = y + 2; i <= size - 1; i++) {
                if (getStone(x, i).getType() == type) {
                    isAvailable = true;
                    break;
                }
            }

            if (isAvailable) {
                for (int i = y + 1; i <= size - 1; i++) {
                    Stone s = getStone(x, i);
                    if (s.getType() == opposite) {
                        amount++;
                        settableStones.add(s);
                    }

                    else break;
                }

                mostAmount = amount;
                isSettable = true;
            }
        }

        //Left-UP
        if (getStone(x - 1, y - 1) != null && getStone(x - 1, y - 1).getType() == opposite) {
            int amount = 0;
            boolean isAvailable = false;

            for (int i = 2; x - i >= 0 && y - i >= 0; i++) {
                if (getStone(x - i, y - i).getType() == type) {
                    isAvailable = true;
                    break;
                }
            }

            if (isAvailable) {
                for (int i = 1; x - i >= 0 && y - i >= 0; i++) {
                    Stone s = getStone(x - i, y - i);
                    if (s.getType() == opposite) {
                        amount++;
                        settableStones.add(s);
                    }

                    else break;
                }

                mostAmount = amount;
                isSettable = true;
            }
        }

        //Right-UP
        if (getStone(x + 1, y - 1) != null && getStone(x + 1, y - 1).getType() == opposite) {
            int amount = 0;
            boolean isAvailable = false;

            for (int i = 2; x + i <= size - 1 && y - i >= 0; i++) {
                if (getStone(x + i, y - i).getType() == type) {
                    isAvailable = true;
                    break;
                }
            }

            if (isAvailable) {
                for (int i = 1; x + i <= size - 1 && y - i >= 0; i++) {
                    Stone s = getStone(x + i, y - i);
                    if (s.getType() == opposite) {
                        amount++;
                        settableStones.add(s);
                    }

                    else break;
                }

                mostAmount = amount;
                isSettable = true;
            }
        }

        //Left-Down
        if (getStone(x - 1, y + 1) != null && getStone(x - 1, y + 1).getType() == opposite) {
            int amount = 0;
            boolean isAvailable = false;

            for (int i = 2; x - i >= 0 && y + i <= size - 1; i++) {
                if (getStone(x - i, y + i).getType() == type) {
                    isAvailable = true;
                    break;
                }
            }

            if (isAvailable) {
                for (int i = 1; x - i >= 0 && y + i <= size - 1; i++) {
                    Stone s = getStone(x - i, y + i);
                    if (s.getType() == opposite) {
                        amount++;
                        settableStones.add(s);
                    }

                    else break;
                }

                mostAmount = amount;
                isSettable = true;
            }
        }

        //Right-Down
        if (getStone(x + 1, y + 1) != null && getStone(x + 1, y + 1).getType() == opposite) {
            int amount = 0;
            boolean isAvailable = false;

            for (int i = 2; x + i <= size - 1 && y + i <= size - 1; i++) {
                if (getStone(x + i, y + i).getType() == type) {
                    isAvailable = true;
                    break;
                }
            }

            if (isAvailable) {
                for (int i = 1; x + i <= size - 1 && y + i <= size - 1; i++) {
                    Stone s = getStone(x + i, y + i);
                    if (s.getType() == opposite) {
                        amount++;
                        settableStones.add(s);
                    }

                    else break;
                }

                mostAmount = amount;
                isSettable = true;
            }
        }

        return new SettableResult(isSettable, base, mostAmount, settableStones);
    }

    public List<SettableResult> getSettablePlaces(int type) {
        List<SettableResult> list = new ArrayList<>();

        for (Stone stone : field) {
            SettableResult result = isSettable(stone.getPosition().getX(), stone.getPosition().getY(), type);
            if (result.isSettable()) {
                list.add(result);
            }
        }

        return list;
    }

    public int[] getResult() {
        int[] result = new int[2];

        result[0] = (int) field.stream().filter(stone -> stone.getType() == BLACK).count();
        result[1] = (int) field.stream().filter(stone -> stone.getType() == WHITE).count();

        return result;
    }

    public void debug() {
        System.out.print(" ");
        for (int i = 0; i < size; i++) {
            System.out.print("  " + i);
        }

        System.out.print("\n--");

        for (int i = 0; i < size; i++) {
            System.out.print("---");
        }

        int[][] temp = new int[size][size];
        for (Stone stone : field) {
            temp[stone.getPosition().getX()][stone.getPosition().getY()] = stone.getType();
        }

        for (int y = 0; y < size; y++) {
            System.out.print("\n" + y + "| ");

            for (int x = 0; x < size; x++) {
                String name = "";
                switch(temp[x][y]) {
                    case EMPTY:
                        name = " ";
                        break;

                    case WHITE:
                        name = "O";
                        break;

                    case BLACK:
                        name = "#";
                        break;
                }

                System.out.print(name + "  ");
            }
        }

        System.out.println("");
    }

}
