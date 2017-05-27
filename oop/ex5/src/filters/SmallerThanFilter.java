package filters;

import filesprocessing.FileInfo;

/**
 * Created by adi on 25/05/17.
 */
public class SmallerThanFilter implements Filter {

    /* fields */
    double lowerBound;

    public SmallerThanFilter(double lowerBound) {
        this.lowerBound = lowerBound;
    }

    @Override
    public boolean filter(FileInfo toFilter) {
        return false;
    }



}
