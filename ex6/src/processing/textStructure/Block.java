package processing.textStructure;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.List;

/**
 * This class represents an arbitrary block of text within a file
 */
public class Block {

    private Entry entry;
    private long startIdx;                  //index within the file where the block begins
    private long endIdx;                    //index within the file where the block ends
    private RandomAccessFile inputFile;     //the RAF object pointing to the physical file in the file system

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
    }


    public Block(Entry entry, long startIdx, long endIdx) {
        this.entry = entry;
        this.startIdx = startIdx;
        this.endIdx = endIdx;
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
        //TODO implement me!!!
    }

    /**
     * The filename from which this block was extracted
     *
     * @return filename
     */
    public String getEntryName() {
        File entryFile= new File(entry.getPath());
        return entryFile.getName();
    }

    public void setMetadata(List<String> metaData){}

    /**
     * Convert an abstract block into a string
     *
     * @return string representation of the block
     */
    @Override
    public String toString() {
        //TODO implement me!!!
    }


}
