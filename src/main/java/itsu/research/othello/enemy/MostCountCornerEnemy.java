package itsu.research.othello.enemy;

import itsu.research.othello.Main;
import itsu.research.othello.core.GameProvider;
import itsu.research.othello.core.OthelloField;
import itsu.research.othello.core.SettableResult;
import itsu.research.othello.core.Stone;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * # Enemy Part2
 * @author Nomaki Itsuru
 */

public class MostCountCornerEnemy implements IEnemy {

    private int type;

    public MostCountCornerEnemy(int type) {
        this.type = type;
    }

    @Override
    public void set(OthelloField field) {
        List<SettableResult> settableResults = field.getSettablePlaces(type)
                .stream()
                .filter(result -> result.isSettable())
                .collect(Collectors.toList());

        if (settableResults.size() - 1 <= 0) {
            GameProvider.onSet(this, true);

        } else {
            Collections.sort(settableResults, (stone1, stone2) -> stone2.getMostAmount() - stone1.getMostAmount());
            List<SettableResult> data = settableResults.stream().filter(result -> result.getMostAmount() == settableResults.get(0).getMostAmount()).collect(Collectors.toList());

            int prioritlyIndex = -1;
            boolean hasPriority = false;

            for (SettableResult datum : data) {
                prioritlyIndex++;
                Stone stone = datum.getStone();
                if (isCorner(stone.getPosition().getX(), stone.getPosition().getY())) {
                    hasPriority = true;
                    break;
                }
            }

            if (hasPriority) {
                field.change(settableResults.get(prioritlyIndex).getStone().getPosition().getX(), settableResults.get(prioritlyIndex).getStone().getPosition().getY(), type, settableResults.get(prioritlyIndex).getSettableStones());
            } else {
                int index = new Random().nextInt(data.size());
                field.change(settableResults.get(index).getStone().getPosition().getX(), settableResults.get(index).getStone().getPosition().getY(), type, settableResults.get(index).getSettableStones());
            }

            GameProvider.onSet(this, false);
        }
    }

    private boolean isCorner(int x, int y) {
        return (x == 0 && y == 0)
                || (x == 0 && y == Main.FIELD_SIZE - 1)
                || (x == Main.FIELD_SIZE - 1 && y == 0)
                || (x == Main.FIELD_SIZE - 1 && y == Main.FIELD_SIZE - 1);
    }

    @Override
    public int getType() {
        return type;
    }

}
