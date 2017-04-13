package core;

import model.NavigationNotification;
import model.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ahmet on 4/13/17.
 */
public class NavigationManager {

    private static final NavigationManager manager = new NavigationManager();

    private final Map<Integer, List<NavigationNotification>> notificationsById = new HashMap<>();
    private final Map<Integer, Position> latestDirection = new HashMap<>();

    private NavigationManager(){}

    public static NavigationManager getManager() {
        return manager;
    }

    public void addNotification(NavigationNotification notification) {
        notificationsById.putIfAbsent(notification.getMobileId(), new ArrayList<>());
        notificationsById.get(notification.getMobileId()).add(notification);
    }

    public void updatePosition(NavigationNotification notification) {

        Position newPosition = Position.moteIdByPosition.getOrDefault(notification.getId(), new Position(notification.getId(), notification.getMobileId(), 390, 390, -1));
        newPosition.setMobileId(notification.getMobileId());
        latestDirection.put(notification.getMobileId(), newPosition);
    }

    public List<Position> getLatestPositions() {
        return new ArrayList<>(latestDirection.values());
    }

    public void resetPostions() {
        latestDirection.clear();
        notificationsById.clear();
    }


}
