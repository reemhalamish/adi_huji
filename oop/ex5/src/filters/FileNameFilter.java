package filters;

import filesprocessing.FileInfo;

/**
 * Created by adi on 25/05/17.
 */
public class FileNameFilter implements Filter {

    /* fields */
    String fileName;

    public FileNameFilter(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean filter(FileInfo toFilter) {
        return toFilter.name.equals(fileName);
    }
}
