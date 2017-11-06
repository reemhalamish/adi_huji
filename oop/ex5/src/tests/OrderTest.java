package tests;

import exceptions.OrderException;
import filesprocessing.FileInfo;
import orders.OrderFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Re'em on 6/7/2017.
 */
public class OrderTest {
    @Test
    public void sort() throws Exception {
    }

    @Test
    public void order() {
        try {
            FileInfo a = new FileInfo(20, "f.txt", false, false, false, "D:/f.txt");
            FileInfo b = new FileInfo(14, "g.txt", false, false, false, "D:/g.txt");
            FileInfo c = new FileInfo(17.5, "f.png", false, false, false, "D:/f.png");
            FileInfo d = new FileInfo(17.5, "h.png", false, false, false, "D:/h.png");

            List<FileInfo> fileInfos = new ArrayList<>();
            fileInfos.add(a);
            fileInfos.add(b);
            fileInfos.add(c);
            fileInfos.add(d);

            List<FileInfo> orderedByAbs = OrderFactory.createOrder("abs").sort(fileInfos);
            List<FileInfo> orderedByAbsRev = OrderFactory.createOrder("abs#REVERSE").sort(fileInfos);
            List<FileInfo> orderedByType = OrderFactory.createOrder("type").sort(fileInfos);
            List<FileInfo> orderedByTypeRev = OrderFactory.createOrder("type#REVERSE").sort(fileInfos);
            List<FileInfo> orderedBySize = OrderFactory.createOrder("size").sort(fileInfos);
            List<FileInfo> orderedBySizeRev = OrderFactory.createOrder("size#REVERSE").sort(fileInfos);

            assertEquals(orderedByAbs.get(0), c);
            assertEquals(orderedByAbs.get(1), a);
            assertEquals(orderedByAbs.get(2), b);
            assertEquals(orderedByAbs.get(3), d);

            assertEquals(orderedByAbsRev.get(3), c);
            assertEquals(orderedByAbsRev.get(2), a);
            assertEquals(orderedByAbsRev.get(1), b);
            assertEquals(orderedByAbsRev.get(0), d);

            assertEquals(orderedByType.get(0), c);
            assertEquals(orderedByType.get(1), d);
            assertEquals(orderedByType.get(2), a);
            assertEquals(orderedByType.get(3), b);

            assertEquals(orderedByTypeRev.get(3), c);
            assertEquals(orderedByTypeRev.get(2), d);
            assertEquals(orderedByTypeRev.get(1), a);
            assertEquals(orderedByTypeRev.get(0), b);

            assertEquals(orderedBySize.get(0), b);
            assertEquals(orderedBySize.get(1), c);
            assertEquals(orderedBySize.get(2), d);
            assertEquals(orderedBySize.get(3), a);

            assertEquals(orderedBySizeRev.get(3), b);
            assertEquals(orderedBySizeRev.get(2), c);
            assertEquals(orderedBySizeRev.get(1), d);
            assertEquals(orderedBySizeRev.get(0), a);


        }catch(OrderException exception) {
            assertTrue(false);
        }

    }


}