package filters.filters;

import filesprocessing.FileInfo;

class AllFilter implements Filter {

    /**
     * filters all files through.
     * @param toFilter FileInfo to filter
     * @return true
     */
    @Override
    public boolean filter(FileInfo toFilter) {
        return true;
    }
}
