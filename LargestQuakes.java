import java.util.*;

public class LargestQuakes {
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "E:/Coursera/nov20quakedata.atom"; // Updated file path
        ArrayList<QuakeEntry> list = parser.read(source);
        // Print the three earthquakes with the largest magnitude
        ArrayList<QuakeEntry> largestQuakes = getLargest(list, 50);
        for (QuakeEntry qe : largestQuakes) {
            System.out.println(qe);
        }
        // Answer Question 7: What country had the 50th largest earthquake?
        if (largestQuakes.size() >= 50) {
            QuakeEntry fiftiethLargest = largestQuakes.get(49);
            System.out.printf("Magnitude of the 50th largest earthquake: %.2f\n", fiftiethLargest.getMagnitude());
        }
        else {
            System.out.println("Not enough earthquakes to answer the question.");
        }
    }

    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        int index = 0;
        double largestMag = 0.0;
        for (int i = 0; i < data.size(); i++) {
            double currentMag = data.get(i).getMagnitude();
            if (currentMag > largestMag) {
                largestMag = currentMag;
                index = i;
            }
        }
        return index;
    }

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        for (int i = 0; i < Math.min(howMany, quakeData.size()); i++) {
            int index = indexOfLargest(quakeData);
            ret.add(quakeData.remove(index));
        }
        return ret;
    }

    public static void main(String[] args) {
        LargestQuakes largestQuakes = new LargestQuakes();
        largestQuakes.findLargestQuakes();
    }
}