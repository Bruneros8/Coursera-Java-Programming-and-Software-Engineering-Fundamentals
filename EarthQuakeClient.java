import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (qe.getLocation().distanceTo(from) < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            double depth = qe.getDepth();
            if (depth > minDepth && depth < maxDepth) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            String title = qe.getInfo();
            if ((where.equals("start") && title.startsWith(phrase)) || (where.equals("end") && title.endsWith(phrase)) || (where.equals("any") && title.contains(phrase))) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n", qe.getLocation().getLatitude(),qe.getLocation().getLongitude(),qe.getMagnitude(), qe.getInfo());
        }
    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "E:/Coursera/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        double magMin = 5.0;
        ArrayList<QuakeEntry> bigQuakes = filterByMagnitude(list, magMin);
        System.out.println("Quakes larger than " + magMin + ":");
        for (QuakeEntry qe : bigQuakes) {
            System.out.println(qe);
        }
        System.out.println("Found " + bigQuakes.size() + " quakes that match that criteria");
    }

    public void closeToMe() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        // This location is Durham, NC
        Location city = new Location(35.988, -78.907);
        double distMax = 1000.0;
        ArrayList<QuakeEntry> closeQuakes = filterByDistanceFrom(list, distMax, city);
        System.out.println("Quakes within " + distMax + " kilometers of Durham, NC:");
        for (QuakeEntry qe : closeQuakes) {
            System.out.println("Distance: " + qe.getLocation().distanceTo(city) + " km, " + qe.getInfo());
        }
    }

    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "E:/Coursera/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        double minDepth = -4000.0;
        double maxDepth = -2000.0;
        ArrayList<QuakeEntry> depthQuakes = filterByDepth(list, minDepth, maxDepth);
        System.out.println("Find quakes with depth between " + minDepth + " and " + maxDepth);
        for (QuakeEntry qe : depthQuakes) {
            System.out.println(qe);
        }
        System.out.println("Found " + depthQuakes.size() + " quakes that match that criteria");
    }

    public void quakesByPhrase(String phrase, String where) {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "E:/Coursera/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        ArrayList<QuakeEntry> phraseQuakes = filterByPhrase(list, where, phrase);
        System.out.println("Find quakes with phrase '" + phrase + "' at " + where);
        for (QuakeEntry qe : phraseQuakes) {
            System.out.println(qe);
        }
        System.out.println("Found " + phraseQuakes.size() + " quakes that match '" + phrase + "' at " + where);
    }

    public void quakesByPhraseQuestion3() {
        quakesByPhrase("Quarry Blast", "start");
    }

    public void quakesByPhraseQuestion4() {
        quakesByPhrase("Alaska", "end");
    }

    public void quakesByPhraseQuestion5() {
        quakesByPhrase("Can", "any");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "E:/Coursera/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
}