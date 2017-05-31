package filters.filters;

import filesprocessing.FileInfo;

/**
 * Created by adi on 25/05/17.
 */
public interface Filter {

    public boolean filter(FileInfo toFilter);
}
