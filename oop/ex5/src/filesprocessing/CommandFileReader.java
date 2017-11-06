package filesprocessing;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;
import exceptions.GeneralException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by adi on 25/05/17.
 */
public class CommandFileReader {

    private static final String ERR_ACCESS_COMMAND_FILE = "could not access the command file";
    private static final String FILTER_STR = "FILTER";
    private static final String ORDER_STR = "ORDER";
    private static final String NO_ORDER_SUBSECTION = "no order subsection";
    private static final String NO_FILTER_SUBSECTION = "no filter subsection";

    private static void printError (String errorMessage){
        System.err.println("Error" + errorMessage);
    }

    List<Section> breakToSections(String fileName) throws GeneralException {
        List<Section> listOfSections = new ArrayList<>();
        List<String> listLines = new ArrayList<>();

        // read the file and create a list of the lines in the file
        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(fileName));
            String lineText;
            while ((lineText = lineReader.readLine()) != null) {
                listLines.add(lineText.replace("\n", ""));
            }
            lineReader.close();
        } catch (IOException e ){
            printError(ERR_ACCESS_COMMAND_FILE);
            throw new GeneralException();
        }

        // break into sections using the list of lines
//        ListIterator<String> lineIterator = listLines.listIterator();
//        while (lineIterator.hasNext()){

        int nextToJump = 4; // if found a 3-lines section, jump 3 lines ahead.
                            // else - jump 4 lines
        for (int lineIdx = 0; lineIdx < listLines.size(); lineIdx+= nextToJump){
            int lineNumInFile = lineIdx + 1;
            if (listLines.get(lineIdx).equals(FILTER_STR)) {
                // if got here section has a proper FILTER subsection
                if (listLines.get(lineIdx + 2).equals(ORDER_STR)){
                    // if got here section has a proper ORDER subsection
                    if (listLines.get(lineIdx + 3).equals(FILTER_STR)){
                        // if got here, this section's ORDER line is empty. This section has 3 lines.
                        nextToJump = 3;
                        Section curSection = new Section(lineNumInFile, listLines.subList(lineIdx,
                                lineIdx + nextToJump));
                        listOfSections.add(curSection);

                    }
                    // if got here than the section has an order line
                    else {
                        // This section's ORDER line is not empty. This section has 4 lines.
                        nextToJump = 4;
                        Section curSection = new Section(lineNumInFile, listLines.subList(lineIdx,
                                lineIdx + nextToJump));
                        listOfSections.add(curSection);
                    }
                }
                else {
                    // if got here, the section has a proper FILTER line but not a proper ORDER line
                    printError(NO_ORDER_SUBSECTION);
                    throw new GeneralException();
                }
            }
            else {
                // if got here, than no proper FILTER line found
                printError(NO_FILTER_SUBSECTION);
                throw new GeneralException();
            }
        }
        return listOfSections;
    }
}
