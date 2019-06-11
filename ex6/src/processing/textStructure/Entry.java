package processing.textStructure;

import processing.parsingRules.IparsingRule;
import utils.MD5;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.*;

/**
 * This class represents a single file within a Corpus
 */
public class Entry implements Iterable<Block>, Serializable {



    private static final long serialVersionUID = 1L ;


    // data members

    private String filePath;
    private String entryText;
    private List<Block> blocks;
    private IparsingRule parseRule;
    private HashMap<String,String> metaData;
    private RandomAccessFile entryRAF;

    /**
	 * Main constructor
	 * @param filePath  The path to the file this entry represents
	 * @param parseRule The parsing rule to be used for this entry
	 */
    public Entry(String filePath, IparsingRule parseRule) {


        this.filePath = filePath;
        this.parseRule = parseRule;
        try {
            this.entryRAF = new RandomAccessFile(filePath, "r");
        } catch (FileNotFoundException e ){}

        // create list of blocks by parsing the entry file
        blocks = parseRule.parseFile(entryRAF);

        // parse all blocks iteratively
        for (Block block : blocks ){

            long startPos = block.getStartIndex();
            long endPos =  block.getEndIndex();
            parseRule.parseRawBlock(entryRAF,startPos,endPos);
        }

    }

    /**
     * Iterate over Block objects in the Entry
     * @return  A Block object iterator
     */
    @Override
    public Iterator<Block> iterator() {
        return blocks.iterator();
    }

    public String getPath() {
        return filePath;
    }

    public String getChecksum(){
        return MD5.getMd5(entryText);
    }

    public RandomAccessFile getEntryRAF() {
        return entryRAF;
    }
}
