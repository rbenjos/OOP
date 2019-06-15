

import Exceptions.CorpusNotFoundException;
import Exceptions.IndexerTypeNotFoundException;
import Exceptions.ParseRuleNotFoundException;
import Exceptions.ParsingException;
import dataStructures.Aindexer;
import processing.parsingRules.IparsingRule;
import processing.searchStrategies.IsearchStrategy;
import processing.textStructure.Corpus;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ArgsParser {

    // data members

    // file readers
    private FileReader fileReader;
    private BufferedReader bufferedReader;

    // an array containing a string for each line in the input file
    private String[] commandLines = new String[8];

    // the objects that were built from the input
    private Corpus corpus;
    private IparsingRule parseRule;
    private Aindexer<? extends IsearchStrategy> indexer;
    private QueryLine query;

    /**
     * the only constructor, builds a parser for the given file
     * @param filePath the path of the input file
     * @throws FileNotFoundException if the file path is not given
     */
    public ArgsParser(String filePath) throws FileNotFoundException {

        this.fileReader = new FileReader(filePath);
        this.bufferedReader = new BufferedReader(fileReader);
    }


    // TODO we can change this whole thing to a REGEX based parser by looking
    // TODO by looking for a REGEX representing an entire valid file, but maybe thats an overkill
    /**
     * the main method of this class, populates the data members by parsing the
     * file given
     * @throws IOException if there was a problem reading from the file
     * @throws ParsingException if one of the lines in the files was wrongly formatted
     */
    public void parse() throws IOException,ParsingException {

        // we will start by initialzing the current line with the first line in the file and setting the index to 0
        String currentLine = bufferedReader.readLine();
        int index = 0;

        // we have to read for at least 6 lines
        while (index < 7) {
            index++;

            // if the line is valid we will save its content to our array and move to the next line
            if (lineIsValid(index, currentLine)) {
                commandLines[index] = currentLine;
                currentLine = bufferedReader.readLine();

                // otherwise we would throw an exception
            } else {
                throwRelevantException(index);
            }
        }

        // then we will create our objects required for the searching
        parseRule = ParseRuleFactory.createParseRule(commandLines[5]);
        corpus = new Corpus(commandLines[1], parseRule);
        indexer = IndexerFactory.createIndexer(commandLines[3], corpus);

        // because our query is optional, we would parse them individually
        if (commandLines[6] != null) {
            query = new QueryLine(commandLines[7]);
        }

    }

    /**
     * this method takes in a line and an index, and a boolean representing the validity
     * of that line
     * @param index the index of the line
     * @param currentLine the text of the given line
     * @return true if the the line is valid, false if not
     */
    private boolean lineIsValid(int index, String currentLine) {

        switch (index) {

            case 1:
                return (currentLine != null && currentLine.equals("CORPUS"));
            case 2:
                return (currentLine != null);
            case 3:
                return (currentLine != null && currentLine.equals("INDEXER"));
            case 4:
                return (currentLine != null);
            case 5:
                return (currentLine != null && currentLine.equals("PARSE_RULE"));
            case 6:
                return (currentLine != null);
            default:
                return false;

        }
    }

    /**
     * this method takes an index of an invalid line, and throws an exception fitting that index
     * @param index the index of the invalid line
     * @throws ParsingException a fitting parsingException for that line
     */
    private void throwRelevantException(int index) throws ParsingException {

        switch (index) {
            case 1:
                throw new CorpusNotFoundException();
            case 2:
                throw new CorpusNotFoundException();
            case 3:
                throw new IndexerTypeNotFoundException();
            case 4:
                throw new IndexerTypeNotFoundException();
            case 5:
                throw new ParseRuleNotFoundException();
            case 6:
                throw new ParseRuleNotFoundException();

        }
    }

    /**
     * @return the corpus object made by this parser
     */
    public Corpus getCorpus() {
        return corpus;
    }

    /**
     * @return the parse rule object made by this parser
     */
    public IparsingRule getParseRule() {
        return parseRule;
    }

    /**
     * @return the indexer object made by this parser
     */
    public Aindexer<? extends IsearchStrategy> getIndexer() {
        return indexer;
    }

    /**
     * @return the query object made by this parser
     */
    public QueryLine getQuery() {
        return query;
    }

}
