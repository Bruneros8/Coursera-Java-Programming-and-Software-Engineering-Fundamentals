public class MagnitudeFilter implements Filter {
    private double minMagnitude;
    private double maxMagnitude;

    public MagnitudeFilter(double min, double max) {
        minMagnitude = min;
        maxMagnitude = max;
    }

    public boolean satisfies(QuakeEntry qe) {
        double magnitude = qe.getMagnitude();
        return magnitude >= minMagnitude && magnitude <= maxMagnitude;
    }

    public String getName() {
        return "MagnitudeFilter";
    }
}