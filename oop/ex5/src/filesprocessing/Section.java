package filesprocessing;

import java.util.List;

/**
 * Created by Re'em on 5/27/2017.
 */
public class Section {

    /* fields */
    private int firstLineNum;
    private List<String > listOfLines;

    public Section(int firstLineNum, List<String> listOfLines) {
        this.firstLineNum = firstLineNum;
        this.listOfLines = listOfLines;
    }

    public int getFirstLineNum() {
        return firstLineNum;
    }

    public List<String> getListOfLines() {
        return listOfLines;
    }
}
