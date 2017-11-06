package orders;

import filesprocessing.FileInfo;

import java.util.Collections;
import java.util.List;


 class ReverseOrder extends Order{

    /* fields */
    private Order innerOrder;

    /* constructor */

    /**
     * Constructs a reversed order object. Gets an Order object as an argument to reverse it's order.
     * @param order the order that needs to be reversed
     */
    public ReverseOrder(Order order) {
        this.innerOrder = order;
    }

    /**
     * Sorts the list of FileInfos by given the inner order given as an argument in the constructor,
     * then reverses the order of the file and return the reversed list
     * @param fileInfos list of FileInfos
     * @return the reversed list of FileInfos ordered by the inner order given in the constructor.
     */
    @Override
    public List<FileInfo> sort(List<FileInfo> fileInfos) {
        List<FileInfo> orderedInfos = innerOrder.sort(fileInfos);
        Collections.reverse(orderedInfos); // reverses the order of file infos in orderedInfos
        return orderedInfos;
    }
}
