package dataStructures.dictionary;

import dataStructures.Aindexer;
import processing.parsingRules.IparsingRule;
import processing.searchStrategies.IsearchStrategy;
import processing.textStructure.Corpus;

public class SuffixTreeIndexer extends Aindexer {


    public SuffixTreeIndexer(Corpus origin){
        super(origin);
    }
    @Override
    public IsearchStrategy asSearchInterface() {
        return null;
    }

    @Override
    protected void indexCorpus() {

    }

    @Override
    protected void writeIndexFile() {

    }

    @Override
    public IparsingRule getParseRule() {
        return null;
    }

    @Override
    protected IndexTypes getIndexType() {
        return null;
    }
}
