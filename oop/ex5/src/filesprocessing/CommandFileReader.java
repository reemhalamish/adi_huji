package filesprocessing;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adi on 25/05/17.
 */
public class CommandFileReader {

    List<Section> breakToSections(String fileName) {
        List<Section> listOfSections = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return listOfSections;
    }
}
