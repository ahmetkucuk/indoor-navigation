package model;

import java.util.Arrays;

/**
 * Created by ahmet on 3/31/17.
 */
public class LightIntensityMeasurement {

    private int version;
    private int interval;
    private int id;
    private int parent;
    private int count;
    private int[] readings;
    private long createdAt;
    private long secret;

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

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public long getSecret() {
        return secret;
    }

    public void setSecret(long secret) {
        this.secret = secret;
    }

    @Override
    public String toString() {
        return "LightIntensityMeasurement{" +
                "version=" + version +
                ", interval=" + interval +
                ", id=" + id +
                ", parent=" + parent +
                ", count=" + count +
                ", readings=" + Arrays.toString(readings) +
                ", createdAt=" + createdAt +
                ", secret=" + secret +
                '}';
    }
}
