package orders;

import filesprocessing.FileInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


 class AbsOrder extends Order {

    /**
     * Sorts the given list of FileInfos by the files absolute name going from 'a' to 'z'.
     * @param fileInfos list of FileInfos
     * @return the sorted list of FIleInfos
     */
    @Override
    public List<FileInfo> sort(List<FileInfo> fileInfos) {
        List<FileInfo> sorted = new ArrayList<>(fileInfos);
        Collections.sort(sorted, new Comparator<FileInfo>() {
            /**
             * Compares its two arguments for order. Returns a negative integer,
             * zero, or a positive integer as the first argument is less than, equal
             * to, or greater than the second.
             * @param o1 first file info
             * @param o2 second file info
             * @return -1, 0 or 1
             */
            @Override
            public int compare(FileInfo o1, FileInfo o2) {
                return o1.absolutePath.compareTo(o2.absolutePath);
            }
        });

        return sorted;
    }
}
