package filters.filters;

import exceptions.FilterException;
import filesprocessing.FileInfo;


class SmallerThanFilter implements Filter {

    /* fields */
    private double upperBound;

    /**
     * Filters through only files that their size is <= to the lower bound given here.
     * @param upperBound double representing the upper bound for the size of the files.
     * @throws FilterException throws exception if the upper bound is not legal (i.e. the string does not
     * contain a number, string is null, or the number is smaller than 0)
     */
    SmallerThanFilter(String upperBound) throws FilterException {
       try {
           this.upperBound = Double.valueOf(upperBound);
           if ( this.upperBound < 0) { throw new FilterException(); }
       }
       catch (NumberFormatException | NullPointerException exception){
           throw new FilterException(); }
        }


    /**
     * filters through the given fileInfo if it's size is smaller or equal to the upper bound.
     * @param toFilter FileInfo to filter
     * @return true if size is smaller or equal to upper bound, false otherwise.
     */
    @Override
    public boolean filter(FileInfo toFilter) {
        return toFilter.size <= upperBound;
    }
}