package model;

import java.util.List;

/**
 * Created by ahmet on 4/12/17.
 */
public class NotificationResponse {


    private List<NavigationNotification> notifications;

    public NotificationResponse(List<NavigationNotification> navigationNotification) {
        this.notifications = navigationNotification;
    }

    public List<NavigationNotification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NavigationNotification> notifications) {
        this.notifications = notifications;
    }
}
