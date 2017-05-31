package filters.filters;

import exceptions.FilterException;
import filesprocessing.FileInfo;

/**
 * Created by adi on 25/05/17.
 */
public class GreaterThanFilter implements Filter {

    /* fields */
    double lowerBound;

    GreaterThanFilter(String lowerBound) throws FilterException {
        try {
            this.lowerBound = Double.valueOf(lowerBound);
            if (this.lowerBound < 0) {
                throw new FilterException();
            }

        } catch (NumberFormatException | NullPointerException exception) {
            throw new FilterException();
        }
    }


    @Override
    public boolean filter(FileInfo toFilter) {
        return false;
    }
}
