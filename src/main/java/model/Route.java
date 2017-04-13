package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ahmet on 4/13/17.
 */
public class Route {

    public static final Route route;
    static {
        route = new Route();
        route.insertDirection(1, 2);
        route.insertDirection(4, 6);
    }

    Map<Integer, Integer> moteByDirectionMap = new HashMap<>();

    public int getDirection(int modeId) {
        return moteByDirectionMap.getOrDefault(modeId, -1);
    }

    public void insertDirection(int id, int direction) {
        moteByDirectionMap.put(id, direction);
    }

}
