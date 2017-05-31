package filters.filters;

import filesprocessing.FileInfo;

/**
 * Created by adi on 25/05/17.
 */
public class ContainsFilter implements Filter {

    /* fields */
    String stringContained;

    public ContainsFilter(String stringContained) {
        this.stringContained = stringContained;
    }

    @Override
    public boolean filter(FileInfo toFilter) {
        return toFilter.name.contains(stringContained);
    }
}
