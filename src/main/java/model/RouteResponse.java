package model;

import java.util.List;

/**
 * Created by ahmet on 4/13/17.
 */
public class RouteResponse {

    private final List<Position> positions;

    RouteResponse(List<Position> p) {
        positions = p;
    }
}
