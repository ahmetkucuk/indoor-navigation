package core.model;

import java.util.*;

/**
 * Created by ahmet on 4/7/17.
 */
public class LightIntensityResponse {

    private int numberOfHops;
    private int lostPercentage;
    private List<LightIntensityMeasurement> readings;

    public LightIntensityResponse(List<LightIntensityMeasurement> readings) {
        this.readings = readings;
        computeNumberOfHops();
        computeLostPercentage();
    }

    private void computeNumberOfHops() {

        if(readings.size() == 0) {
            numberOfHops = 0;
            return;
        }


        //Count Number of parent to find number of hops
        //NOTE if topology changes, this may give wrong result
        //However there is a option to reset values which can be done from web interface
        Set<Integer> numberOfUniqueParents = new HashSet<>();

        for(LightIntensityMeasurement l: readings) {
            numberOfUniqueParents.add(l.getParent());
        }

        numberOfHops = numberOfUniqueParents.size();
    }

    private void computeLostPercentage() {
        if(readings.size() == 0) {
            lostPercentage = 0;
            return;
        }
        //Find Packet with max count
        int maxCount = 0;
        int minCount = Integer.MAX_VALUE;

        Set<Integer> allUniquePackets = new HashSet<>();
        for(LightIntensityMeasurement l: readings) {
            if(l.getId() == 0) {

                if(maxCount < l.getCount()) {
                    maxCount = l.getCount();
                }

                if(minCount > l.getCount()) {
                    minCount = l.getCount();
                }

            }
            allUniquePackets.add(l.getCount());
        }
        if(maxCount == minCount) {
            lostPercentage = 0;
            return;
        }

        int lostCount = 0;
        for(int i = minCount; i < maxCount; i++) {
            if(!allUniquePackets.contains(i)) {
                lostCount++;
            }
        }


        //Divide number of arrived packet with number of packets at hand
        lostPercentage = (int)(((double)(lostCount)/(double)readings.size()) * 100);
    }


    public int getNumberOfHops() {
        return numberOfHops;
    }

    public void setNumberOfHops(int numberOfHops) {
        this.numberOfHops = numberOfHops;
    }

    public int getLostPercentage() {
        return lostPercentage;
    }

    public void setLostPercentage(int lostPercentage) {
        this.lostPercentage = lostPercentage;
    }

    public List<LightIntensityMeasurement> getReadings() {
        return readings;
    }

    public void setReadings(List<LightIntensityMeasurement> readings) {
        this.readings = readings;
    }
}
