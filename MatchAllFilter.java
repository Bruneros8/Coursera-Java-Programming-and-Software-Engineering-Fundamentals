import java.util.ArrayList;

public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filters;
    
    public MatchAllFilter() {
        filters = new ArrayList<Filter>();
    }

    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    public boolean satisfies(QuakeEntry qe) {
        for (Filter filter : filters) {
            if (!filter.satisfies(qe)) {
                return false;
            }
        }
        return true;
    }

    public String getName() {
        StringBuilder names = new StringBuilder();
        for (Filter filter : filters) {
            names.append(filter.getName()).append(" ");
        }
        return names.toString().trim();
    }
}