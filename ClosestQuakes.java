/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        // Calculate distances and create a TreeMap to sort earthquakes by distance
        TreeMap<Double, QuakeEntry> distanceMap = new TreeMap<>();
        for (QuakeEntry qe : quakeData) {
            double distance = current.distanceTo(qe.getLocation());
            distanceMap.put(distance, qe);
        }
        // Add the closest earthquakes to the result list
        int count = 0;
        for (double distance : distanceMap.keySet()) {
            ret.add(distanceMap.get(distance));
            count++;
            if (count == howMany) {
                break;
            }
        }
        return ret;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());
        Location jakarta  = new Location(-6.211,106.845);
        ArrayList<QuakeEntry> close = getClosest(list,jakarta,10);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }

    public static void main(String[] args) {
        ClosestQuakes closestQuakes = new ClosestQuakes();
        closestQuakes.findClosestQuakes();
    }
}