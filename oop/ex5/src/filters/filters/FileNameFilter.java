package filters.filters;

import filesprocessing.FileInfo;


class FileNameFilter implements Filter {

    /* fields */
    private String fileName;

    /**
     * Creates a filter that filters through only files that their name match the given name.
     * @param fileName string of the file name by which to filter files.
     */
    FileNameFilter(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Filters a file through only if it's name matches the given string in the constructor
     * @param toFilter FileInfo to filter
     * @return true if name matches, false otherwise.
     */
    @Override
    public boolean filter(FileInfo toFilter) {
        return toFilter.name.equals(fileName);
    }
}
