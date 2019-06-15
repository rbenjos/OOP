package processing.textStructure;

import processing.parsingRules.IparsingRule;
import utils.MD5;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class represents a body of works - anywhere between one and thousands of documents sharing the same structure and that can be parsed by the same parsing rule.
 */
public class Corpus implements Iterable<Entry>, Serializable {

    // serialize ID

    private static final long serialVersionUID = 1L ;


    // data members

    private String path;
    private IparsingRule parsingRule;
    private ArrayList<Entry> entries; // TODO needs implementation


    public Corpus(String path, IparsingRule parserName) throws IOException {


        this.path = path;
        this.parsingRule = parsingRule;
        /*
        check if the path is a folder or file.
        if file - single entry corpus.
        otherwise, recursively scan the directory for all subdirectories and files.
        each entry in a corpus should hold the folder from which the file came.
         */

        // build corpus

    }


    /**
     * Return the parsing rule used for this corpus
     *
     * @return
     */

    public String getPath() {
        return path;
    }


    public IparsingRule getParsingRule() {
        return parsingRule;
    }

    /**
     * Iterate over Entry objects in the Corpus
     *
     * @return An Entry iterator
     */
    @Override
    public Iterator<Entry> iterator() {
       return entries.iterator();
    }

    /**
     * Return the checksum of the entire corpus.
     * Can be calculated by getting the checksum of each file, then concating them to one string and
     * returning the checksum of that string.
     *
     * @return
     */
    public String getChecksum() {
        String entriesMD5CheckSum = "";
        for (Entry entry : entries) {

            entriesMD5CheckSum += entry.getChecksum();  //TODO make more efficient
        }
        return MD5.getMd5(entriesMD5CheckSum);
    }
}

