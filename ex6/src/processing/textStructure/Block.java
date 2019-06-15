package processing.textStructure;

import processing.parsingRules.IparsingRule;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


/**
 * This class represents an arbitrary block of text within a file
 */
public class Block {

    private Entry entry;
    private long startIdx;                  //index within the file where the block begins
    private long endIdx;                    //index within the file where the block ends
    private RandomAccessFile inputFile;     //the RAF object pointing to the physical file in the file system
    private String blockString;


    private List<String> metaData;
    private List<String> data = new ArrayList<>();

    /**
     * Constructor
     *
     * @param inputFile the RAF object backing this block
     * @param startIdx  start index of the block within the file
     * @param endIdx    end index of the block within the file
     */
    public Block(RandomAccessFile inputFile, long startIdx, long endIdx) {
        this.inputFile = inputFile;
        this.startIdx = startIdx;
        this.endIdx = endIdx;
        try {buildString();
        } catch (IOException e){
            System.err.println("not a file");
        }
    }


    public Block(Entry entry, long startIdx, long endIdx) {
        this.entry = entry;
        this.startIdx = startIdx;
        this.endIdx = endIdx;
        try {buildString();
        } catch (IOException e){
            System.err.println("not a file");
        }
    }

    ///////// getters //////////

    /**
     * @return start index
     */
    public long getStartIndex() {

        return startIdx;
    }

    /**
     * @return end index
     */
    public long getEndIndex() {
        return endIdx;
    }

    /**
     * @return the RAF object for this block
     */
    public RandomAccessFile getRAF() {
        return inputFile;
    }

    /**
     * Get the metadata of the block, if applicable for the parsing rule used
     *
     * @return String of all metadata.
     */
    public String getMeta() {
        return metaData.toString();
    }


    public String getData() {
        return data.toString();
    }

    /**
     * The filename from which this block was extracted
     *
     * @return filename
     */
    public String getEntryName() {
        File entryFile = new File(entry.getPath());
        return entryFile.getName();
    }

    public void setMetadata(List<String> metaData) {
        this.metaData = metaData;
    }

    public void setData(List<String> data) {
        this.data = data;

    }


    public void setString(String string){
        blockString = string;
    }

    /**
     * this method builds a string representing the block from the RAF
     * @throws IOException if there was a problem reading from the RAF
     */
    public void buildString() throws IOException{

        String allString =IparsingRule.RAFToString(inputFile);
        blockString = allString.substring((int)startIdx,(int)endIdx) ;


    }

    /**
     * Convert an abstract block into a string
     *
     * @return string representation of the block
     */
    @Override
    public String toString() {
        return blockString;
    }
}
