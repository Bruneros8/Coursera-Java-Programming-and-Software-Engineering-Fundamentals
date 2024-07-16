import java.util.ArrayList;

public class AllFilters implements Filter {
    private ArrayList<Filter> filters;

    public AllFilters() {
        filters = new ArrayList<>();
    }

    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    @Override
    public boolean satisfies(String id) {
        for (Filter filter: filters) {
            if (!filter.satisfies(id)) {
                return false;
            }
        }
        return true;
    }
}