package filters;

import filesprocessing.FileInfo;

/**
 * Created by adi on 25/05/17.
 */
public class BetweenFilter implements Filter {

    /* fields */
    double lowerSize;
    double upperSize;

    public BetweenFilter(double lowerSize, double upperSize) {
        this.lowerSize = lowerSize;
        this.upperSize = upperSize;
    }

    @Override
    public boolean filter(FileInfo toFilter) {
        return false;
    }
}
