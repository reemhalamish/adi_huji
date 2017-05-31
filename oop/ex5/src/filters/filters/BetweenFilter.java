package filters.filters;

import exceptions.FilterException;
import filesprocessing.FileInfo;

/**
 * Created by adi on 25/05/17.
 */
public class BetweenFilter implements Filter {

    /* fields */
    double lowerSize;
    double upperSize;

    public BetweenFilter(String lowerSize, String upperSize) throws FilterException {
        try {
            this.lowerSize = Double.valueOf(lowerSize);
            this.upperSize = Double.valueOf(upperSize);
            if (this.lowerSize >=0 && this.upperSize >= this.lowerSize) {
                return;
            } else { throw new FilterException(); }
        } catch (NumberFormatException | NullPointerException exception){ throw new FilterException();}
    }

    @Override
    public boolean filter(FileInfo toFilter) {
        return false;
    }
}
