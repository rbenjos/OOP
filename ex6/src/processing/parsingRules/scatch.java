package processing.parsingRules;

import processing.textStructure.Block;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class scatch {

    public static void main(String args[]){
        STtvSeriesParsingRule st = new STtvSeriesParsingRule();
        File f = new File("/cs/usr/roeyby/Desktop/OOP/ex6/Corpuses/TV/StarTrek_TV_Episode1.txt");
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(f, "r");
            st.parseFile(randomAccessFile);

        } catch (IOException e){
            System.err.println("file not found");
        }

    }

}
