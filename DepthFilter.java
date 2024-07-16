public class DepthFilter implements Filter {
    private double minDepth;
    private double maxDepth;

    public DepthFilter(double min, double max) {
        minDepth = min;
        maxDepth = max;
    }

    public boolean satisfies(QuakeEntry qe) {
        double depth = qe.getDepth();
        return depth >= minDepth && depth <= maxDepth;
    }

    public String getName() {
        return "DepthFilter";
    }
}