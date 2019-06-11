package filesprocessing;

import filesprocessing.Exceptions.NoFilterException;
import filesprocessing.Exceptions.NoOrderException;
import filesprocessing.Filters.Filter;

import java.util.ArrayList;
import java.io.*;
import java.io.File;
import java.text.*;
import java.util.Arrays;
import java.util.function.*;
import java.lang.*;


/**
 * this class represents a parser for our particular mission, it parses the commands file given
 * and makes distinct sections out of it
 */
public class Parser {

    // constants

    public static final String FILTER_STRING = "FILTER";
    public static final String ABS_COMPARATOR = "abs";

    // data members
    java.io.BufferedReader bufferedReader;
    java.io.FileReader fileReader;
    ArrayList<Section> sections;


    /**
     * the constructor, takes in a command file and makes a parser for that file
     *
     * @param file the file given to parse
     * @throws NoFilterException if there is a type 1 error with the filter subsection
     * @throws NoOrderException  if there is a type 1 error with the order subsection
     */
    public Parser(File file) throws FileNotFoundException, NoFilterException, NoOrderException {
        this.fileReader = new FileReader(file);
        this.bufferedReader = new BufferedReader(fileReader);
        this.sections = new ArrayList<>();
    }

    /**
     * the main method of the parser, makes distinct sections out of the command file given
     *
     * @throws NoFilterException if there is a type 1 error with the filter subsection
     * @throws IOException       if something is wrong with reading the file
     * @throws NoOrderException  if there is a type 1 error with the order subsection
     */
    public void makeSections() throws NoOrderException, NoFilterException, IOException {

        // first we will declare our variables
        Section section;
        int index = 1;
        String currentLine;
        String[] lineValues = new String[4];
        int[] lineIndices = new int[4];

        // and read the first line
        currentLine = bufferedReader.readLine();

        // as long as we have more text to read
        while (currentLine != null) {

            // our first three lines have to be there and to be valid
            for (int i = 0; i < 3; i++) {

                // and if they are we can save their values
                if (currentLineIsValid(currentLine, i)) {

                    lineValues[i] = currentLine;
                    lineIndices[i] = index;
                    currentLine = bufferedReader.readLine();

                    // otherwise we must throw an exception
                } else {
                    throwRelevantException(i);
                }
                index++;

            }
            lineValues[3] = currentLine;
            lineIndices[3] = index;

            // the order subsection can be empty, we need to handle that and make fitting orderer
            if (currentLine == null || currentLine.equals(FILTER_STRING)) {
                section = new Section(lineValues[1], lineIndices[1], ABS_COMPARATOR, lineIndices[3]);
                sections.add(section);

                // but if its not, we need to use the values we already have and keep reading
            } else {
                section = new Section(lineValues[1], lineIndices[1], lineValues[3], lineIndices[3]);
                sections.add(section);
                currentLine = bufferedReader.readLine();
                index++;


            }
        }
    }

    /**
     * @return the arraylist of sections the parser made
     */
    public ArrayList<Section> getSections() {

        return sections;
    }

    /**
     * this method checks if a line given is valid based on the line given and its index
     * @param currentLine the line we are checking
     * @param i the index of that line
     * @return true if its valid, false if not
     */
    private boolean currentLineIsValid(String currentLine, int i) {
        switch (i) {
            case 0:
                return currentLine != null && currentLine.equals("FILTER");
            case 1:
                return currentLine != null;
            case 2:
                return currentLine != null && currentLine.equals("ORDER");
            default:
                return currentLine != null;
        }

    }

    /**
     * this method throws the relevant exception matching the index given
     * @param i the index indicating the purpose of the line that had the problem
     * @throws NoFilterException if there is a type 1 error with the filter subsection
     * @throws NoOrderException if there is a type 1 error with the order subsection
     */
    private void throwRelevantException(int i) throws NoFilterException, NoOrderException {
        switch (i) {
            case 0:
                throw new NoFilterException();
            case 1:
                throw new NoFilterException();
            default:
                throw new NoOrderException();
        }
    }
}




