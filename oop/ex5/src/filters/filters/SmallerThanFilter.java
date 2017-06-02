package filters.filters;

import exceptions.FilterException;
import filesprocessing.FileInfo;

/**
 * Created by adi on 25/05/17.
 */
public class SmallerThanFilter implements Filter {

    /* fields */
    private double upperBound;

    public SmallerThanFilter(String upperBound) throws FilterException {
       try {
           this.upperBound = Double.valueOf(upperBound);
           if ( this.upperBound < 0) { throw new FilterException(); }
       }
       catch (NumberFormatException | NullPointerException exception){
           throw new FilterException(); }
        }


    @Override
    public boolean filter(FileInfo toFilter) {
        return toFilter.size <= upperBound;
    }



}
