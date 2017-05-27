package orders;

import filesprocessing.FileInfo;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by adi on 25/05/17.
 */
public abstract class Order {
    public abstract List<FileInfo> sort (List<FileInfo> fileInfos);
}