package filters.filters;

import filesprocessing.FileInfo;

/**
 * Created by adi on 25/05/17.
 */
public class AllFilter implements Filter {

    @Override
    public boolean filter(FileInfo toFilter) {
        return true;
    }
}
