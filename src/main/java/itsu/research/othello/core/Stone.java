package itsu.research.othello.core;

/**
 * @author Nomaki Itsuru
 */

public final class Stone {

    private Position position;
    private int type;

    protected Stone(int x, int y, int type) {
        this.position = new Position(x, y);
        this.type = type;
    }

    public Position getPosition() {
        return position;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Stone{" +
                "position=" + position +
                ", type=" + type +
                '}';
    }

    public final class Position {

        private int x;
        private int y;

        protected Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

}
