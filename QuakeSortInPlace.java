import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }

    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i = from + 1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }

    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
        for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
    }

    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        int maxIdx = from;
        for (int i = from + 1; i < quakeData.size(); i++) {
            if (quakeData.get(i).getDepth() > quakeData.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        for (int pass = 0; pass < 70; pass++) { // Perform 70 passes
            int maxIdx = getLargestDepth(in, pass);
            QuakeEntry qi = in.get(pass);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(pass, qmax);
            in.set(maxIdx, qi);
        }
    }

    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        for (int i = 0; i < quakeData.size() - numSorted - 1; i++) {
            if (quakeData.get(i).getMagnitude() > quakeData.get(i + 1).getMagnitude()) {
                QuakeEntry temp = quakeData.get(i);
                quakeData.set(i, quakeData.get(i + 1));
                quakeData.set(i + 1, temp);
            }
        }
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        for (int i = 0; i < in.size() - 1; i++) {
            onePassBubbleSort(in, i);
        }
    }

    public int sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        int passes = 0;
        for (int i = 0; i < in.size() - 1; i++) {
            if (!checkInSortedOrder(in, i)) {
                onePassBubbleSort(in, i);
                passes++;
            }
            else {
                passes++;
                break;
            }
        }
        return passes;
    }

    public int sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        int passes = 0;
        for (int i = 0; i < in.size() - 1; i++) {
            if (!checkInSortedOrder(in, i)) {
                onePassBubbleSort(in, i);
                passes++;
            }
            else {
                passes++;
                break;
            }
        }
        return passes;
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes, int from) {
        for (int i = from; i < quakes.size() - 1; i++) {
            if (quakes.get(i).getMagnitude() > quakes.get(i + 1).getMagnitude()) {
                return false;
            }
        }
        return true;
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        String source = "E:/Coursera/earthQuakeDataWeekDec6sample2.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        int passes = sortByMagnitudeWithBubbleSortWithCheck(list);
        System.out.println("Number of passes needed to sort the file: " + passes);
    }

    public static void main(String[] args) {
        QuakeSortInPlace quakeSorter = new QuakeSortInPlace();
        quakeSorter.testSort();
    }
}