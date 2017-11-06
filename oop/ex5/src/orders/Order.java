package orders;

import filesprocessing.FileInfo;
import java.util.List;

public abstract class Order {

    /**
     * Sorts a list of FileInfos by a certain order
     * @param fileInfos list of FileInfos
     * @return the sorted list.
     */
    public abstract List<FileInfo> sort (List<FileInfo> fileInfos);
}