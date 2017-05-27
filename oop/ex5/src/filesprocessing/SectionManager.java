package filesprocessing;

import java.util.List;

/**
 * Created by adi on 25/05/17.
 */
public class SectionManager {

    private void processSection(Section section){}
    // as processing section, print warnings and then the files in order. section after section


    public void processAllSections(List<Section> listOfSections){
        for (Section section: listOfSections) { processSection(section);}
    }
}
