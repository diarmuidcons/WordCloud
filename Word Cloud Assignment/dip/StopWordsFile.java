package ie.gmit.dip;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;

public class StopWordsFile {

	// On average, the contains() of HashSet runs in O(1) time, meaning lookups are
	// fast
	private final Collection<String> ignoreWords = new HashSet<>();

	public Collection<String> getIgnoreWords(String filePathName) throws FileNotFoundException {
		final Scanner scan = new Scanner(new File(filePathName));

		while (scan.hasNext()) {
			final String newWord = scan.nextLine();
			ignoreWords.add(newWord);
		}
		return ignoreWords;
	}
}
