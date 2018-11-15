package itsu.research.othello.enemy;

import itsu.research.othello.core.Othello;
import itsu.research.othello.core.OthelloField;

public interface Enemy {

    public void set(OthelloField field);

    public int getType();

}
