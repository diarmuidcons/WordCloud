package ie.gmit.dip;

import java.io.BufferedReader;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Runner {
	public static void main(String[] args) throws Exception {

		System.out.println("***************************************************");
		System.out.println("* GMIT - Dept. Computer Science & Applied Physics *");
		System.out.println("*                                                 *");
		System.out.println("*              Word Cloud Generator               *");
		System.out.println("*     H.Dip in Science (Software Development)     *");
		System.out.println("*                                                 *");
		System.out.println("***************************************************");

		showOptions();

		// read stop words
		StopWordsFile swf = new StopWordsFile();
		Collection<String> ignoreWords = swf.getIgnoreWords("ignorewords.txt");
		System.out.println("Ignore words: " + ignoreWords);

		// read text file (start with file)

		FileParser fp = new FileParser();
		Menu menu = new Menu();
		BufferedReader file = fp.loadFileFromDisk(menu.getFilePath());

		// create map of words -> count, exluding stop words
		Map<String, Integer> listOfWords = fp.textFileToMap(file, ignoreWords);
		System.out.println(listOfWords);

		// sort map
		final List<Map.Entry<String, Integer>> sortedListOfWords = listOfWords.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toList());

		System.out.println(sortedListOfWords);

		// take first 30
		final List<Map.Entry<String, Integer>> shortenedListWithCount = sortedListOfWords.subList(0, 29);

		// cloud generator
		CloudGenerator cloudGenerator = new CloudGenerator();
		cloudGenerator.generateWordCloud("image.png", shortenedListWithCount);
	}

	private static void showOptions() throws Exception {
		Menu menu = new Menu();
		menu.userChoice();
	};
}