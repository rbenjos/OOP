package processing.parsingRules;

import processing.textStructure.Block;
import processing.textStructure.WordResult;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class STmovieParsingRule implements IparsingRule, Serializable {
    public static final long serialVersionUID = 1L;

    public STmovieParsingRule() {
    }



    @SuppressWarnings("Duplicates")
    public Block parseFirstBlock(RandomAccessFile inputFile, long startPos, long endPos) {
        Block block = new Block(inputFile, startPos, endPos);
        ArrayList<String> metaData = new ArrayList<>();

        // we will use this regex (\b.*)$ to separate text lines from empty lines


        String firstBlockRegex = "(\\b.+)$";

        Pattern firstBlockPattern = Pattern.compile(firstBlockRegex,Pattern.MULTILINE);

        Matcher firstBlockMatcher = firstBlockPattern.matcher(block.toString());
        String currentMetaData;


        while (firstBlockMatcher.find()) {
            currentMetaData = firstBlockMatcher.group();
            metaData.add(currentMetaData);
        }

        block.setMetadata(metaData);
        return block;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public Block parseRawBlock(RandomAccessFile inputFile, long startPos, long endPos) {

        Block block = new Block(inputFile, startPos, endPos);
        ArrayList<String> metaData = new ArrayList<>();
        ArrayList<String> data = new ArrayList<>();

        // first we will extract metadata with this regex ^\h{11,15}(\d+.*)|^\h{19}(\S.*)|^\h{43}(\S.*)
        // and make metadata out of it


        String blockString = block.toString();
        String currentMetaDataRegex = "^\\h{11,15}(\\d+.*)|^\\h{19}(\\S.*)|^\\h{43}(\\S.*)";
        Pattern currentMetadataPattern = Pattern.compile(currentMetaDataRegex,Pattern.MULTILINE);
        Matcher currentMetadataMatcher = currentMetadataPattern.matcher(blockString);
        String currentMetaData;



        while (currentMetadataMatcher.find()) {
            currentMetaData = currentMetadataMatcher.group();
            currentMetaData = currentMetaData.replaceAll("^\\h*", "");
            metaData.add(currentMetaData);
        }
        block.setMetadata(metaData);


        // then we will extract all the data itself with this regex ^\h{29}(\S.*)

        String currentBlockRegex = "^\\h{29}(\\S.*)";
        Pattern currentBlockPattern = Pattern.compile(currentBlockRegex,Pattern.MULTILINE);
        Matcher currentBlockMatcher = currentBlockPattern.matcher(blockString);
        String currentBlockData;


        while (currentBlockMatcher.find()) {
            currentBlockData = currentBlockMatcher.group(1);
            data.add(currentBlockData);
        }
        block.setData(data);

        return block;


    }

    @Override
    @SuppressWarnings("Duplicates")
    public List<Block> parseFile(RandomAccessFile inputFile) {

        LinkedList<Block> blocks = new LinkedList<>();
        try {


            String fileString = IparsingRule.RAFToString(inputFile);

            // we will separate the first block from the others using this regex .*?(?=1\h{3}\w) with DOTALL mode

            Block metaDataBlock;
            String firstBlockRegex = ".*?(?=1\\h{3}\\w)";
            Pattern firstBlockPattern = Pattern.compile(firstBlockRegex, Pattern.DOTALL);
            Matcher firstBlockMatcher = firstBlockPattern.matcher(fileString);

            if (firstBlockMatcher.find()) {
                int start = firstBlockMatcher.start();
                int end = firstBlockMatcher.end();
                metaDataBlock = parseFirstBlock(inputFile, start, end);
                blocks.add(metaDataBlock);
            }




            // we can capture a scene title by using this regex ^\h{1,15}n\h{1,3}[\w\W]*(?=^\h{1,15}n+1\h{1,3})
            // when n is the current scene number, and n+1 is the one after it

            Block currentSceneBlock;
            String blockRegex = "^\\h{11,15}(\\d+).*?\\1.*?(?=^\\h{11,15}\\d+)";
            Pattern sceneBlockPattern = Pattern.compile(blockRegex, Pattern.MULTILINE|Pattern.DOTALL);
            Matcher sceneBlockMatcher = sceneBlockPattern.matcher(fileString);


            // we will need that end variable later, so we better define it outside the loop's scope

            long end=0;
//
//            if (sceneBlockMatcher.find()) {
//                long start = sceneBlockMatcher.start();
//                end = sceneBlockMatcher.end();
//                System.out.println(start + " " + end);
//                currentSceneBlock = parseRawBlock(inputFile, start, end);
//                currentSceneBlock.setString(sceneBlockMatcher.group());
//                blocks.add(currentSceneBlock);
//                System.out.println(currentSceneBlock.getMeta());
//                System.out.println(currentSceneBlock.getData());
//            }






            while (sceneBlockMatcher.find()) {
                long start = sceneBlockMatcher.start();
                end = sceneBlockMatcher.end();
                currentSceneBlock = parseRawBlock(inputFile, start, end);
                currentSceneBlock.setString(sceneBlockMatcher.group());

                blocks.add(currentSceneBlock);

            }

//
//            for (Block block : blocks){
//////                System.out.println(block.toString());
//                System.out.println(block.getMeta());
//                System.out.println(block.getData()+"\n#####################\n");
//            }

            // we need to parse the last block with a separate method using the following regex
             //" ^\h{11,15}(\d+).*?\1.*?(?=\z)"

            String lastBlockRegex = "^\\h{11,15}(\\d+).*?\\1.*?(?=\\z)";
            Pattern lastBlockPattern = Pattern.compile(lastBlockRegex,Pattern.MULTILINE|Pattern.DOTALL);
            Matcher lastBlockMatcher = lastBlockPattern.matcher(fileString);


            if (lastBlockMatcher.find((int)end)){
                int start = lastBlockMatcher.start();
                int end1 = lastBlockMatcher.end();
                Block lastBlock = parseRawBlock(inputFile, start, end1);
                lastBlock.setString(lastBlockMatcher.group());
                blocks.add(lastBlock);
            }


        } catch (IOException e) {
            System.err.println("RAF file is empty");
        }
        return blocks;
    }

    @Override
    public void printResult(WordResult wordResult) {

    }
}


