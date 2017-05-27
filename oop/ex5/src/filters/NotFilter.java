package filters;

import filesprocessing.FileInfo;

/**
 * Created by Re'em on 5/27/2017.
 */
public class NotFilter implements Filter{

    /* fields */
    Filter innerFilter;

    public NotFilter(Filter filter) {
        this.innerFilter = filter;
    }

    @Override
    public boolean filter(FileInfo toFilter) {
        return !innerFilter.filter(toFilter);
    }
}
