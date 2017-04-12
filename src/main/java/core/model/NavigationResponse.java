package core.model;

import java.util.List;

/**
 * Created by ahmet on 4/12/17.
 */
public class NavigationResponse {


    private List<NavigationNotification> notifications;

    public NavigationResponse(List<NavigationNotification> navigationNotification) {
        this.notifications = navigationNotification;
    }

    public List<NavigationNotification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NavigationNotification> notifications) {
        this.notifications = notifications;
    }
}
