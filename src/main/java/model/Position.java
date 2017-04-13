package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ahmet on 4/13/17.
 */
public class Position {

    public static final Map<Integer, Position> moteIdByPosition;

    static {
        moteIdByPosition = new HashMap<>();
        //1 is UP, 2 is RIGHT, 3 DOWN, 4 LEFT
        moteIdByPosition.put(1, new Position(1, 100, 180, 570, 1));
        moteIdByPosition.put(2, new Position(2, 100, 180, 220, 2));
        moteIdByPosition.put(3, new Position(3, 100, 620, 220, 3));
        moteIdByPosition.put(4, new Position(4, 100, 610, 570, 3));
    }

    int id;
    int mobileId;
    int x;
    int y;
    int direction;

    public Position(int id, int mobileId, int x, int y, int direction) {
        this.id = id;
        this.mobileId = mobileId;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getMobileId() {
        return mobileId;
    }

    public void setMobileId(int mobileId) {
        this.mobileId = mobileId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
