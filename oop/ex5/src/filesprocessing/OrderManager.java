package filesprocessing;

import exceptions.OrderException;
import orders.Order;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adi on 25/05/17.
 */
public class OrderManager {

//    /* fields */
//    List<FileInfo> listOfFilteredFiles;
//
//    public OrderManager(List<FileInfo> listOfFilteredFiles) {
//        this.listOfFilteredFiles = listOfFilteredFiles;
//    }

    private Order stringToOrder(String lineToCreateOrder) throws OrderException{
        return null;
    }

    public List<FileInfo> sortFilesByOrder(List<FileInfo> listfilteredFileInfos,
                                           String orderLine) throws OrderException{
        Order sectionOreder = stringToOrder(orderLine);
        List<FileInfo> sortedFiles = new ArrayList<>();
        //sort listfilteredFileInfos by sectionOrder.
        //if two files are equal by size o rtype, sort by abs path

        return sortedFiles;
    }

}
