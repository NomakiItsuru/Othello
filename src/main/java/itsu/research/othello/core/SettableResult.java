package itsu.research.othello.core;

import java.util.List;

/**
 * @author Nomaki Itsuru
 */

public final class SettableResult {

    private boolean isSettable;
    private Stone stone;
    private int mostAmount;
    private List<Stone> settableStones;

    protected SettableResult(boolean isSettable, Stone stone, int mostAmount, List<Stone> settableStones) {
        this.isSettable = isSettable;
        this.stone = stone;
        this.mostAmount = mostAmount;
        this.settableStones = settableStones;
    }

    public Stone getStone() {
        return stone;
    }

    public int getMostAmount() {
        return mostAmount;
    }

    public boolean isSettable() {
        return isSettable;
    }

    public List<Stone> getSettableStones() {
        return settableStones;
    }

    @Override
    public String toString() {
        return "SettableResult{" +
                "isSettable=" + isSettable +
                ", stone=" + stone +
                ", mostAmount=" + mostAmount +
                ", settableStones=" + settableStones +
                '}';
    }
}
