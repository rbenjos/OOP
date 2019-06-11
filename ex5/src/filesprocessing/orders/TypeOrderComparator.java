package filesprocessing.orders;

import java.io.File;

/**
 * this class represents a comparator that compares files based on their type
 */
public class TypeOrderComparator extends OrderComparator {

    /**
     * this method compares 2 files based on the lexical value of their absolute path
     *
     * @param file1 the first file being compared
     * @param file2 the second file being compared
     * @return 1 if the first file should be listed before the second
     * -1 if the second file should be listed before the first
     * 0 if their pathes have the same lexical value
     */
    public int compare(File file1, File file2) {
        String file1Type = getType(file1);
        String file2Type = getType(file2);

        if (file1Type.compareTo(file2Type) == 0) {
            AbsOrderComparator absOrderComparator = new AbsOrderComparator();
            return absOrderComparator.compare(file1, file2);
        } else
            return file1Type.compareTo(file2Type);

    }


    /**
     * this method extracts the type of the files, basicaly the text that comes after the last delimeter
     *
     * @param file the file of which we are extracting the suffix (the type)
     * @return the type of the file
     */
    public String getType(File file) {

        // first we will turn our files path into a list of chars
        char[] filePathList = file.getAbsolutePath().toCharArray();
        String type = "";

        // than we will go over every char
        for (char currentChar : filePathList) {

            /* if that char happens to be a delimeter, it means the string we made so far can be forgotten
               as it was not the last delimeter*/
            if (currentChar == '.') {
                type = "";
            }

            // otherwise we will add the current char to the string representing the type
            else {
                type += currentChar;
            }

        }
        return type;
    }
}
