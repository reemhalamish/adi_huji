package filesprocessing;

import exceptions.FilterException;
import exceptions.OrderException;
import filters.filter_processing.FileInfosBuilder;
import filters.filters.Filter;
import filters.filters.FilterFactory;
import orders.Order;
import orders.OrderFactory;

import java.util.List;

/**
 * Created by adi on 25/05/17.
 */
public class SectionManager {

    private static final String WARNING_MSG = "Warning in line ";
    private static final int FILTER_LINE = 1;
    private static final int ORDER_LINE = 3;

    private static void printWarning(int lineNum){
        System.err.println(WARNING_MSG + lineNum);

    }

    private void processSection(String sourcedir, Section section) {
        Filter sectionFilter;
        try {
            sectionFilter = FilterFactory.createFilter(section.getListOfLines().get(FILTER_LINE)); }
        catch(FilterException exception){
            printWarning(section.getFirstLineNum() + FILTER_LINE);
            sectionFilter = FilterFactory.createDefaultFilter();
        }

        List<FileInfo> filteredFileInfos = FileInfosBuilder.buildInfosWithFilter(sourcedir, sectionFilter);

        Order sectionOrder;
        try {
            if (section.getListOfLines().size() == 3)
                sectionOrder = OrderFactory.createDefaultOrder();
            else
                sectionOrder = OrderFactory.createOrder(section.getListOfLines().get(ORDER_LINE));
        } catch (OrderException exception){
            printWarning(section.getFirstLineNum() + ORDER_LINE);
            sectionOrder = OrderFactory.createDefaultOrder();
        }

        List<FileInfo> orderedFileInfos = sectionOrder.sort(filteredFileInfos);

        for(FileInfo info: orderedFileInfos) {
            System.out.println(info.name);
        }

    }


    public void processAllSections(String sourcedir, List<Section> listOfSections){
        for (Section section: listOfSections) { processSection(sourcedir, section);}
    }

}
