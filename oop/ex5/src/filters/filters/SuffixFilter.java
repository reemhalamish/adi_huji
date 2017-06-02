package filters.filters;

import filesprocessing.FileInfo;

/**
 * Created by adi on 25/05/17.
 */
public class SuffixFilter implements Filter {

    /* fields */
    private String suffix;

    public SuffixFilter(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public boolean filter(FileInfo toFilter) {
        return toFilter.name.endsWith(suffix);
    }
}
