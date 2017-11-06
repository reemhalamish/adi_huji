package filesprocessing;

import exceptions.GeneralException;

import java.util.*;
import java.lang.*;
import java.io.*;
import java.text.*;

/**
 * Created by adi on 25/05/17.
 */
public class DirectoryProcessor {

    public static void main(String sourcedir, String commandFile) {
        try {

            // create commandFileReader to break commandfile to sections
            CommandFileReader commandFileReader = new CommandFileReader();
            List<Section> listOfSections = commandFileReader.breakToSections(commandFile);

            // process all sections from commandfile with SectionManager
            SectionManager sectionManager = new SectionManager();
            sectionManager.processAllSections(sourcedir, listOfSections);

        } catch (GeneralException ignored) {
            return;
        }
    }

}
