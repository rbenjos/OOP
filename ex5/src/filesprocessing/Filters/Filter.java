package filesprocessing.Filters;


import java.util.ArrayList;
import java.io.*;
import java.io.File;
import java.text.*;
import java.util.Arrays;
import java.util.function.*;
import java.lang.*;


/**
 * this abstract class represents an abstract filter
 * all filters has a filterFile method which takes in a file
 * and returns true if that file has matched a certain criteria and false if not
 */
public abstract class Filter {

    /**
     * @param file the file we are checking
     * @return true if that file has matched a certain criteria and false if not
     */
    public abstract boolean filterFile(File file);
}
