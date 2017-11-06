package filters.filters;

import exceptions.FilterException;
import filesprocessing.FileInfo;


class GreaterThanFilter implements Filter {

    /* fields */
    private double lowerBound;

    /**
     * Filters through only files that their size is >= to the lower bound given here.
     * @param lowerBound double representing the lower bound for the size of the files.
     * @throws FilterException throws exception if the lower bound is not legal (i.e. the string does not
     * contain a number, string is null, or the number is smaller than 0)
     */
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


    /**
     * filters through the given fileInfo if it's size is larger or equal to the lower bound.
     * @param toFilter FileInfo to filter
     * @return true if size is larger or equal to lower bound, false otherwise.
     */
    @Override
    public boolean filter(FileInfo toFilter) {
        return toFilter.size >= lowerBound;
    }
}
