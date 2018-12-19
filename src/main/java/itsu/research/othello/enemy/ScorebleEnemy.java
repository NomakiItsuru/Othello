package itsu.research.othello.enemy;

import itsu.research.othello.Main;
import itsu.research.othello.core.GameProvider;
import itsu.research.othello.core.OthelloField;
import itsu.research.othello.core.SettableResult;
import itsu.research.othello.core.Stone;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * # Enemy Part2
 * @author Nomaki Itsuru
 */

public class ScorebleEnemy implements IEnemy {

    private int type;
    private static Map<String, Integer> scoreMap;

    public ScorebleEnemy(int type) {
        this.type = type;
        scoreMap = new HashMap<>();
        initScoreMap();
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
            Map<Integer, SettableResult> scores = new TreeMap<>();
            settableResults.forEach(result -> scores.put(getScore(result.getStone().getPosition().getX(), result.getStone().getPosition().getY()), result));

            field.change(settableResults.get(scores.size() - 1).getStone().getPosition().getX(), settableResults.get(scores.size() - 1).getStone().getPosition().getY(), type, settableResults.get(scores.size() - 1).getSettableStones());

            GameProvider.onSet(this, false);
        }
    }

    private int getScore(int x, int y) {
        return scoreMap.get(x + "," + y);
    }

    private static void initScoreMap() {
        scoreMap.put("0,0", 45);
        scoreMap.put("0,1", -11);
        scoreMap.put("0,2", 4);
        scoreMap.put("0,3", -1);
        scoreMap.put("1,0", -11);
        scoreMap.put("1,1", -16);
        scoreMap.put("1,2", -1);
        scoreMap.put("1,3", -3);
        scoreMap.put("2,0", 4);
        scoreMap.put("2,1", -1);
        scoreMap.put("2,2", 2);
        scoreMap.put("2,3", -1);
        scoreMap.put("3,0", -1);
        scoreMap.put("3,1", -3);
        scoreMap.put("3,2", -1);
        scoreMap.put("3,3", 0);

        final int realFieldSize = Main.FIELD_SIZE - 1;

        for (int x = realFieldSize; x >= 4; x--) {
            for (int y = realFieldSize; y >= 4; y--) {
                if (scoreMap.containsKey(x + "," + y)) continue;
                scoreMap.put(x + "," + y, scoreMap.get((realFieldSize - x) + "," + (realFieldSize - y)));
            }
        }

        for (int x = realFieldSize - Main.FIELD_SIZE / 2; x >= 0; x--) {
            for (int y = realFieldSize; y >= 0; y--) {
                if (scoreMap.containsKey(x + "," + y)) continue;
                scoreMap.put(x + "," + y, scoreMap.get(x + "," + (realFieldSize - y)));
            }
        }

        for (int x = realFieldSize; x >= realFieldSize - Main.FIELD_SIZE / 2 + 1; x--) {
            for (int y = realFieldSize; y >= 0; y--) {
                if (scoreMap.containsKey(x + "," + y)) continue;
                scoreMap.put(x + "," + y, scoreMap.get(x + "," + (realFieldSize - y)));
            }
        }

        /* TODO: FOR TEST, DELETE SOON
        JFrame frame = new JFrame("TEST");
        frame.setBounds(0, 50, 800, 800);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                g.setColor(Color.BLACK);

                for (String key : scoreMap.keySet()) {
                    String[] data = key.split(",");
                    int x = Integer.parseInt(data[0]);
                    int y = Integer.parseInt(data[1]);

                    g.drawString(String.valueOf(scoreMap.get(key)), x * 70 + 20, y * 70 + 20);
                }
            }
        };

        panel.setLayout(null);
        panel.setBounds(0, 0, 800, 800);

        frame.getContentPane().add(panel);
        frame.setVisible(true);*/
    }

    @Override
    public int getType() {
        return type;
    }

}
