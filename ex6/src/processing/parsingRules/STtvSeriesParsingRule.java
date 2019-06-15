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

public class STtvSeriesParsingRule implements IparsingRule, Serializable {
	public static final long serialVersionUID = 1L;


	public STtvSeriesParsingRule() {
	}


	public Block parseFirstBlock(RandomAccessFile inputFile, long startPos, long endPos){
		Block block = new Block(inputFile,startPos,endPos);
		ArrayList<String> metaData = new ArrayList<>();

		// we will use this regex (\b.*)$ to seperate text lines from empty lines

		String blockString = block.toString();
		String firstBlockRegex = "(\\b.*)$";
		Pattern firstBlockPattern = Pattern.compile(firstBlockRegex,Pattern.MULTILINE);
		Matcher firstBlockMatcher = firstBlockPattern.matcher(blockString);
		String currentMetaData;


		while (firstBlockMatcher.find()){
			currentMetaData = firstBlockMatcher.group();
			metaData.add(currentMetaData);
		}

		block.setMetadata(metaData);
		return block;
	}

	@SuppressWarnings("Duplicates")
	@Override
	public Block parseRawBlock(RandomAccessFile inputFile, long startPos, long endPos) {


		Block block = new Block(inputFile,startPos,endPos);
		ArrayList<String> metaData = new ArrayList<>();
		ArrayList<String> data = new ArrayList<>();

		// first we will extract metadata with this regex (?:^\t{4,}|^\t{0,1})([^\t]*?\n)
		// and make metadata out of it


		String blockString = block.toString();
		String currentMetaDataRegex = "(?:^\\t{4,}|^\\t{0,1})([^\\t]*?\\n)";
		Pattern currentMetadataPattern = Pattern.compile(currentMetaDataRegex, Pattern.MULTILINE);
		Matcher currentMetadataMatcher = currentMetadataPattern.matcher(blockString);
		String currentMetaData;


		while (currentMetadataMatcher.find()){
			currentMetaData = currentMetadataMatcher.group(1);
			currentMetaData = currentMetaData.replaceAll("\\s*?\n", "");
			metaData.add(currentMetaData);

		}
		block.setMetadata(metaData);




		// then we will extract all the data itself with this regex ^\t{3}[^\t]*?\n

		String currentBlockRegex = "^\\t{3}([^\\t]*?)\\n";
		Pattern currentBlockPattern = Pattern.compile(currentBlockRegex, Pattern.MULTILINE);
		Matcher currentBlockMatcher = currentBlockPattern.matcher(blockString);
		String currentBlockData;

		while (currentBlockMatcher.find()){
			currentBlockData = currentBlockMatcher.group(1);
			data.add(currentBlockData);
		}
		block.setData(data);

		return  block;


	}

	@SuppressWarnings("Duplicates")
	@Override
	public List<Block> parseFile(RandomAccessFile inputFile) {

		LinkedList<Block> blocks = new LinkedList<>();

		// make string from RAF

		try {

			String fileString = IparsingRule.RAFToString(inputFile);
			// find first block using regex, endidx-1 , make a block and append to list
			// regex for first block [\s\S]*?\n1  (lazy, efficient)

			Block metaDataBlock;
			String firstBlockRegex = ".*?(?=\\n^1\\h)";
			Pattern firstBlockPattern = Pattern.compile(firstBlockRegex, Pattern.DOTALL | Pattern.MULTILINE);
			Matcher firstBlockMatcher = firstBlockPattern.matcher(fileString);
			int end = 0;
			if (firstBlockMatcher.find()) {
				int start = firstBlockMatcher.start();
				end = firstBlockMatcher.end();
				metaDataBlock = parseFirstBlock(inputFile, start, end);
				blocks.add(metaDataBlock);
			}

			// ^\d+.*\n(?:\n+|\s+.*\n)* the regex for each regular block
			// while something (probably match), find the next block using

			Block currentSceneBlock;
			String blockRegex = "^\\d+.*?(?=^\\d+)";
			Pattern sceneBlockPattern = Pattern.compile(blockRegex, Pattern.DOTALL | Pattern.MULTILINE);
			Matcher sceneBlockMatcher = sceneBlockPattern.matcher(fileString);

			while (sceneBlockMatcher.find()) {
				int start = sceneBlockMatcher.start();
				end = sceneBlockMatcher.end();
				currentSceneBlock = parseRawBlock(inputFile, start, end);
				blocks.add(currentSceneBlock);
			}

			Block lastBlock;
			String lastBlockRegex = "^\\d+.*(?=\\z)";
			Pattern lastBlockPattern = Pattern.compile(lastBlockRegex, Pattern.DOTALL | Pattern.MULTILINE);
			Matcher lastBlockMatcher = lastBlockPattern.matcher(fileString);

			if (lastBlockMatcher.find(end)){
				int start = lastBlockMatcher.start();
				int end1 = lastBlockMatcher.end();
				lastBlock = parseRawBlock(inputFile, start, end1);
				lastBlock.setString(lastBlockMatcher.group());
				blocks.add(lastBlock);
			}


		} catch (IOException e){System.err.println("RAF file is empty");}
		return blocks;

	}


	@Override
	public void printResult(WordResult wordResult) {

	}

}
