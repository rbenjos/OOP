import Exceptions.IndexerTypeNotFoundException;
import dataStructures.Aindexer;
import dataStructures.dictionary.DictionaryIndexer;
import dataStructures.dictionary.SuffixTreeIndexer;
import dataStructures.naive.NaiveIndexer;
import dataStructures.naive.NaiveIndexerRK;
import processing.textStructure.Corpus;

/**
 * this class represents a factory for Aindexer extending objects, a factory for indexers
 */
public class IndexerFactory {


    /**
     * the only method in this class, takes in a string representing the type of the indexer
     * and a corpus required for creating that indexer
     * @param indexerName a string representing the type of the indexer required
     * @param origin a corpus of text required for creating an indexer
     * @return the appropriate type of indexer
     * @throws IndexerTypeNotFoundException if the indexer type requested is unsupported
     */
    public static Aindexer createIndexer(String indexerName, Corpus origin) throws IndexerTypeNotFoundException {
        Aindexer indexer;

        switch (indexerName) {
            case "DICT":
                indexer = new DictionaryIndexer(origin);
                break;
            case "NAIVE":
                indexer = new NaiveIndexer(origin);

                break;
            case "NAIVE_RK":
                indexer = new NaiveIndexerRK(origin);

                break;
            case "SUFFIX_TREE":
                indexer = new SuffixTreeIndexer(origin);
                break;
            case "CUSTOM":
                indexer = new NaiveIndexerRK(origin);
                break;
            default:
                throw new IndexerTypeNotFoundException();
        }

        return indexer;

    }
}
