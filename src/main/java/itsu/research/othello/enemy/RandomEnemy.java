package itsu.research.othello.enemy;

import itsu.research.othello.core.*;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomEnemy implements Enemy {

    private int type;

    public RandomEnemy(int type) {
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
            int index = new Random().nextInt(settableResults.size() - 1);

            field.change(settableResults.get(index).getStone().getPosition().getX(), settableResults.get(index).getStone().getPosition().getY(), type, settableResults.get(index).getSettableStones());

            GameProvider.onSet(this, false);
        }
    }

    @Override
    public int getType() {
        return type;
    }

}
