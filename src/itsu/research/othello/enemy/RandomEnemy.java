package itsu.research.othello.enemy;

import itsu.research.othello.core.GameProvider;
import itsu.research.othello.core.Othello;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomEnemy implements Enemy {

    private int type;

    public RandomEnemy(int type) {
        this.type = type;
    }

    @Override
    public void set(Othello othello) {
        List<Map<String, Object>> boardData = othello.getCanSetBoards(type);

        if (boardData.size() == 0) {
            GameProvider.onSet(this);
        }

        int index = new Random().nextInt(boardData.size() - 1);
        int x = Integer.parseInt(String.valueOf(boardData.get(index).get("x")));
        int y = Integer.parseInt(String.valueOf(boardData.get(index).get("y")));

        System.out.println(othello.set(x, y, type));
        othello.debug();

        GameProvider.onSet(this);
    }

    @Override
    public int getType() {
        return type;
    }

}
