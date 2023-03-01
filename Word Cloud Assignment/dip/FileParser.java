package ie.gmit.dip;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FileParser {

	// create class variables for input streams so I can shut them down later using
	// .close() function

	public BufferedReader loadFileFromDisk(String filePathName) throws IOException {
		FileInputStream fis = new FileInputStream(filePathName);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		fis.close();
		return br;
	}

	public BufferedReader loadFileFromURL(String urlPath) throws IOException {
		URL urlObject = new URL(urlPath);
		URLConnection urlConnection = urlObject.openConnection();
		InputStream is = urlConnection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		is.close();
		return br;
	}

	public Map<String, Integer> textFileToMap(BufferedReader file, Collection ignoreWords) throws IOException {
		Map<String, Integer> listOfWords = new HashMap<>();

		String line = null;
		while ((line = file.readLine()) != null) {
			line = line.trim(); // remove whitespaces at head and tail
			String[] words = line.split("\\s+");

			for (String word : words) {
				// O(1) for checking the stop words
				if (!ignoreWords.contains(word)) {
					int count = listOfWords.getOrDefault(word, 0);
					listOfWords.put(word, count + 1);
				}
			}
		}
		return listOfWords;
	}
}
