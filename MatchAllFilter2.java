import java.util.ArrayList;

public class MatchAllFilter2 implements Filter {
    private ArrayList<Filter> filters;

    public MatchAllFilter2() {
        filters = new ArrayList<>();
    }

    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        for (Filter filter : filters) {
            if (!filter.satisfies(qe)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getName() {
        StringBuilder names = new StringBuilder();
        for (Filter filter : filters) {
            if (names.length() > 0) {
                names.append(", ");
            }
            names.append(filter.getName());
        }
        return names.toString();
    }
}