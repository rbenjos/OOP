package filesprocessing;

import filesprocessing.Exceptions.FilterErrorException;
import filesprocessing.Exceptions.NoFilterException;
import filesprocessing.Exceptions.NoOrderException;
import filesprocessing.Filters.FileFilterer;
import filesprocessing.orders.AbsOrderComparator;
import filesprocessing.orders.FileSorter;
import filesprocessing.orders.OrderComparator;

import java.util.ArrayList;
import java.io.*;
import java.io.File;
import java.text.*;
import java.util.Arrays;
import java.util.function.*;
import java.lang.*;


/**
 * this class is the manager class, runs all the code, printing filtered
 * and ordered lists of files with commands given from the arguments
 */
public class DirectoryProcessor {

    /**
     * this method will take in an ordered collection of the names of the files
     * that were filtered and will output them into a text file
     *
     */

    private static String NO_FILTER_ERROR = "the filter subsection is missing";
    private static String NO_ORDER_ERROR = "the order subsection is missing";
    private static String A_FILE_IS_MISSING_ERROR = "either the command file or the source direcotry missing";

    public static void main(String args[]) {

        try {

            // first we will parse the command file
            String commandFilePath = args[1];

            File commandFile = new File(commandFilePath);
            Parser parser = new Parser(commandFile);
            parser.makeSections();
            ArrayList<Section> sections = parser.getSections();

            // and make our filters and orderers for each of them
            for (Section section : sections) {
                section.makeFilter();
                section.makeOrderComparator();
            }

            // then we will extract our list of files from the source directory
            String directoryPath = args[0];
            File sourceDirectory = new File(directoryPath);
            File[] filesInDirectory = sourceDirectory.listFiles();


            // then for each of our sections (if our file is not empty)
            if (filesInDirectory!= null) {
                for (Section section : sections) {

                    // we will filter the files using the filter
                    File[] filteredFilesArray = FileFilterer.filterFiles(filesInDirectory, section.getFilter());

                    // and sort them with our comparator
                    OrderComparator absOrderComparator = new AbsOrderComparator();
                    FileSorter.sortFiles(filteredFilesArray, section.getOrderComparator());

                    // and finally we will print our filtered and sorted list of files
                    printFiles(filteredFilesArray);

                }
            }


            // lastly we will handle our exceptions as detailed in the exercise description
        } catch (NoFilterException e) {
            System.err.println("ERROR: " + NO_FILTER_ERROR + "\n");
        } catch (NoOrderException e) {
            System.err.println("ERROR: " + NO_ORDER_ERROR + "\n");
        } catch (FileNotFoundException | IndexOutOfBoundsException e) {
            System.err.println("ERROR: " + A_FILE_IS_MISSING_ERROR + "\n");
        } catch (IOException e) {
            System.err.println("ERROR: " + "IO exception apperantly" + "\n");
        }
    }


    /**
     * this method prints all the files in a given array
     * @param files the array of files needed to be printed
     */
    private static void printFiles(File[] files) {
        for (File file : files) {
            System.out.println(file.getName());
        }
    }
}
