package filters;

import filesprocessing.FileInfo;

/**
 * Created by adi on 25/05/17.
 */
public class PrefixFilter implements Filter {

    /* fields */
    String prefix;

    public PrefixFilter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public boolean filter(FileInfo toFilter) {
        return toFilter.name.startsWith(prefix);
    }
}
