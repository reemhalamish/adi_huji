package filters.filters;

import exceptions.FilterException;
import filesprocessing.FileInfo;

class BetweenFilter implements Filter {

    /* fields */
    private double lowerSize;
    private double upperSize;

    /**
     * Creates a filter that filters through files that are between the two sizes given. (measured in k-bytes)
     * @param lowerSize the lower bound for the size
     * @param upperSize the upper bound for the size
     * @throws FilterException throws an exception in case the conversion from the string to double did not
     * work properly. (string was null or did not hold a number only)
     */
    BetweenFilter(String lowerSize, String upperSize) throws FilterException {
        try {
            this.lowerSize = Double.valueOf(lowerSize);
            this.upperSize = Double.valueOf(upperSize);
            if (this.lowerSize >=0 && this.upperSize >= this.lowerSize) {
                return;
            } else {
                // the two sizes were not bigger or equal to 0 and/or the lower limit was not smaller or
                // equal to upper limit. i.e. they were not in a proper format.
                throw new FilterException();
            }
        } catch (NumberFormatException | NullPointerException exception){
            throw new FilterException();
        }
    }

    /**
     * filters through files that their size is between two set sizes.
     * @param toFilter FileInfo to filter
     * @return true if file size is between the two sizes, false otherwise.
     */
    @Override
    public boolean filter(FileInfo toFilter) {
        return lowerSize <= toFilter.size && toFilter.size<= upperSize;
    }
}
