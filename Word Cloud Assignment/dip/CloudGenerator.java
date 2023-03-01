package ie.gmit.dip;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

public class CloudGenerator {

	public Random random = new Random();

	public int fontMultiplier = 5; //

	public String randomFont() {
		final List<String> font = Arrays.asList(Font.MONOSPACED, Font.SANS_SERIF);
		return font.get(random.nextInt(font.size()));
	}

	public Color randomColour() {
		final List<Color> font = Arrays.asList(Color.red, Color.yellow, Color.blue, Color.BLACK, Color.CYAN);
		return font.get(random.nextInt(font.size()));
	}

	public int randomStyle() {
		final List<Integer> style = Arrays.asList(Font.BOLD, Font.ITALIC, Font.PLAIN);
		return style.get(random.nextInt(style.size()));
	}

	public int randomNumber() {
		int randLow = 0;
		int randHigh = 150;
		return random.nextInt(randHigh - randLow) + randLow;
	}

	public void generateWordCloud(String filePathName, List<Map.Entry<String, Integer>> listOfWordsToCount)
			throws IOException {

		BufferedImage image = new BufferedImage(600, 300, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics graphics = image.getGraphics();

		for (Map.Entry<String, Integer> wordToCount : listOfWordsToCount) {
			Font font = new Font(randomFont(), randomStyle(), wordToCount.getValue() * fontMultiplier);
			graphics.setColor(randomColour());
			graphics.setFont(font);
			graphics.drawString(wordToCount.getKey(), randomNumber(), randomNumber());
		}

		graphics.dispose();
		ImageIO.write(image, "png", new File(filePathName));
	}
}
