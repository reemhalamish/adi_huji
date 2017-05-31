package filters.filters;

import filesprocessing.FileInfo;

/**
 * Created by adi on 25/05/17.
 */
public class HiddenFilter implements Filter{

    @Override
    public boolean filter(FileInfo toFilter) {
        return toFilter.hidden;
    }
}
