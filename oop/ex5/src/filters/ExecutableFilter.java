package filters;

import filesprocessing.FileInfo;

/**
 * Created by adi on 25/05/17.
 */
public class ExecutableFilter implements Filter {


    @Override
    public boolean filter(FileInfo toFilter) {
        return toFilter.executable;
    }
}
