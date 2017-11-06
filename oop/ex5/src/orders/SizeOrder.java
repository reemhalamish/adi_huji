package orders;

import filesprocessing.FileInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


 class SizeOrder extends Order {

    /**
     * Sorts the given list of FileInfos by the files size going from smallest to largest. If two files are
     * same size, they are sorted by the absolute path order.
     * @param fileInfos list of FileInfos
     * @return the sorted list of FileInfos
     */
    @Override
    public List<FileInfo> sort(List<FileInfo> fileInfos) {
        List<FileInfo> infos = new ArrayList<>(fileInfos);
        Collections.sort(infos, new Comparator<FileInfo>() {
            /**
             * Compares its two arguments for order. Returns a negative integer,
             * zero, or a positive integer as the first argument is less than, equal
             * to, or greater than the second.
             * @param first first file info
             * @param second second file info
             * @return -1, 0 or 1
             */
            @Override
            public int compare(FileInfo first, FileInfo second) {
                if (first.size > second.size) return 1;
                else if (first.size < second.size) return -1;

                // reached here? the file sizes are equal
                return first.absolutePath.compareTo(second.absolutePath);
            }
        });
        return infos;
    }
}
