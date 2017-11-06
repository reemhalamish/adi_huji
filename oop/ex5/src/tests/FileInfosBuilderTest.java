//package tests;
//
//import filesprocessing.FileInfo;
//import filters.filter_processing.FileInfosBuilder;
//import filters.filters.FileNameFilter;
//import filters.filters.Filter;
//import org.junit.Test;
//
//import java.util.List;
//
//import static org.junit.Assert.*;
//
///**
// * Created by abrock on 6/5/17.
// */
//public class FileInfosBuilderTest {
//
//    @Test
//    public void buildInfosFromFolderTest(){
//        Filter fileNameFilter = new FileNameFilter("png");
//        List<FileInfo> infos = FileInfosBuilder.buildInfosWithFilter("/cs/usr/abrock/Documents", fileNameFilter);
//        for (FileInfo info: infos){
//            assertEquals("LOGO.png", info.name);
//        }
//        String[] a = "a#b#c".split("#");
//        assertTrue(a[2].equals("c"));
//        for (String item: a) {System.out.println(item); }
//
//    }
//
//
//}