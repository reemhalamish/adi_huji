package filters.filters;

import exceptions.FilterException;
import filesprocessing.FileInfo;

/**
 * Created by adi on 25/05/17.
 */
public class WritableFilter implements Filter {

    private boolean isWritable;

    public WritableFilter(String isWritable) throws FilterException {
        if (isWritable.equals("NOT")){
            this.isWritable = false;
        }
        if (isWritable.equals("YES")){
            this.isWritable = true;
        }
        else {throw new FilterException(); }
    }

    @Override
    public boolean filter(FileInfo toFilter) {
        return toFilter.writable == isWritable;
    }
}
