package model;

/**
 * Created by ahmet on 4/11/17.
 */
public class NavigationNotification {

    private int id;
    private int parent;
    private int count;
    private int mobileId;
    private long createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMobileId() {
        return mobileId;
    }

    public void setMobileId(int mobileId) {
        this.mobileId = mobileId;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "NavigationNotification{" +
                "id=" + id +
                ", parent=" + parent +
                ", count=" + count +
                ", mobileId=" + mobileId +
                '}';
    }
}
