package filters;

import filesprocessing.FileInfo;

/**
 * Created by adi on 25/05/17.
 */
public class GreaterThanFilter implements Filter {

    /* fields */
    double lowerBound;

    public GreaterThanFilter(double lowerBound) {
        this.lowerBound = lowerBound;
    }

    @Override
    public boolean filter(FileInfo toFilter) {
        return false;
    }
}
