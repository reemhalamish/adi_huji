package orders;

import filesprocessing.FileInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


 class TypeOrder extends Order {

    /**
     * Sorts the given list of FileInfos by the files type going from 'a' to 'z'. If two files are of the same
     * type, they are sorted by the absolute path order.
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
             *
             * @param first first file info
             * @param second second file info
             * @return -1, 0 or 1
             */
            @Override
            public int compare(FileInfo first, FileInfo second) {
                String type1 = first.getType();
                String type2 = second.getType();
                int comparisonresult = type1.compareTo(type2);

                if (comparisonresult != 0) return comparisonresult;
                else {  // type equals. order by absolute value!
                    String absNoType1 = first.absolutePath;
                    absNoType1 = absNoType1.substring(0, absNoType1.length() - type1.length());
                    String absNoType2 = second.absolutePath;
                    absNoType2 = absNoType2.substring(0, absNoType2.length() - type2.length());

                    return absNoType1.compareTo(absNoType2);
                }
            }
        });

        return infos;
    }
}
