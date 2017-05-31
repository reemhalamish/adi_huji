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

    private void processSection(String sourcedir, Section section) {
        Filter sectionFilter;
        try {
            sectionFilter = FilterFactory.createFilter(section.getListOfLines().get(1)); }
        catch(FilterException exception){
            printWarning(section.getFirstLineNum() + 1);
            sectionFilter = FilterFactory.createDefaultFilter();
        }

        List<FileInfo> filteredFileInfos = FileInfosBuilder.buildInfosWithFilter(sourcedir, sectionFilter);

        Order sectionOrder;
        try {
            sectionOrder = OrderFactory.createOrder(section.getListOfLines().get(3));
        } catch (OrderException exception){
            printWarning(section.getFirstLineNum() + 3);
            sectionOrder = OrderFactory.createDefaultOrder();
        }



    }
    // as processing section, print warnings and then the files in order. section after section
    // calls filter manager and order manager with appropriate line

    //try to create filter with second line in section. throw warning if not succedded. else create filtermanager
    // with second line and filter files in sourcedir.

    //after calling OrderManager and sorting files print files by order in sorted list
    //or in reversed order if #REVERSE appears in order line.**print file names here**


    public void processAllSections(String sourcedir, List<Section> listOfSections){
        for (Section section: listOfSections) { processSection(sourcedir, section);}
    }

    private static void printWarning(int lineNum){
        System.err.println(WARNING_MSG + lineNum);

    }
}
