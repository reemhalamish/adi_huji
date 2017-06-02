package filters.filters;

import exceptions.FilterException;
import filesprocessing.FileInfo;

/**
 * Created by adi on 25/05/17.
 */
public class ExecutableFilter implements Filter {

    private boolean isExecutable;

    public ExecutableFilter(String isExecutable) throws FilterException {
        if (isExecutable.equals("NOT")){
            this.isExecutable = false;
        }
        if (isExecutable.equals("YES")){
            this.isExecutable = true;
        }
        else {throw new FilterException(); }
    }

    @Override
    public boolean filter(FileInfo toFilter) {
        return toFilter.executable == isExecutable;
    }
}
