import edu.duke.*;

public class PerimeterRunner {

    public double getPerimeter (Shape s) {
        double totalPerim = 0;
        Point prevPt = s.getLastPoint();
        for (Point currPt: s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            totalPerim += currDist;  // Fix: Use += to accumulate the distances
            prevPt = currPt;
        }
        return totalPerim;
    }

    public int getNumPoints(Shape s) {
        int numPoints = 0;
        for (Point p : s.getPoints()) {
            numPoints++;
        }
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        double totalLength = 0;
        int numSides = 0;

        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            totalLength += currDist;
            numSides++;
            prevPt = currPt;
        }

        return totalLength / numSides;
    }

    public double getLargestSide(Shape s) {
        double largestSide = 0;

        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            largestSide = Math.max(largestSide, currDist);
            prevPt = currPt;
        }

        return largestSide;
    }

    public double getLargestX(Shape s) {
        double largestX = Double.NEGATIVE_INFINITY;

        for (Point p : s.getPoints()) {
            double x = p.getX();
            largestX = Math.max(largestX, x);
        }

        return largestX;
    }

    public void testPerimeter() {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);

        int numPoints = getNumPoints(s);
        System.out.println("Number of Points: " + numPoints);

        double avgLength = getAverageLength(s);
        System.out.println("Average Length of Sides: " + avgLength);

        double largestSide = getLargestSide(s);
        System.out.println("Largest Side: " + largestSide);

        double largestX = getLargestX(s);
        System.out.println("Largest X Value: " + largestX);

        double perimeter = getPerimeter(s);  // Add this line to display the perimeter
        System.out.println("Perimeter: " + perimeter);
    }

    public static void main(String[] args) {
        PerimeterRunner pr = new PerimeterRunner();
        pr.testPerimeter();
    }
}