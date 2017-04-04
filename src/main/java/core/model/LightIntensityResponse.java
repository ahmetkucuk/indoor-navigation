package core.model;

import java.util.Arrays;

/**
 * Created by ahmet on 3/31/17.
 */
public class LightIntensityResponse {

    private int version;
    private int interval;
    private int id;
    private int child;
    private int count;
    private int[] readings;
    private long createdAt;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int[] getReadings() {
        return readings;
    }

    public void setReadings(int[] readings) {
        this.readings = readings;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "LightIntensityResponse{" +
                "version=" + version +
                ", interval=" + interval +
                ", id=" + id +
                ", count=" + count +
                ", readings=" + Arrays.toString(readings) +
                '}';
    }
}
