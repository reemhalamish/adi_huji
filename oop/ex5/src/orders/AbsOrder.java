package orders;

import filesprocessing.FileInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by adi on 25/05/17.
 */
public class AbsOrder extends Order {

    @Override
    public List<FileInfo> sort(List<FileInfo> fileInfos) {
        List<FileInfo> sorted = new ArrayList<>(fileInfos);
        Collections.sort(sorted, new Comparator<FileInfo>() {
            /**
             * Compares its two arguments for order.  Returns a negative integer,
             * zero, or a positive integer as the first argument is less than, equal
             * to, or greater than the second.
             * @param o1 first
             * @param o2 second
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
