package ie.gmit.dip;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
	private boolean keepRunning = true;

	public Menu() {
	}

	public String getURLPath() {
		Scanner s = new Scanner(System.in);
		return s.next().strip();
	}

	public String getFilePath() {
		Scanner s = new Scanner(System.in);
		return s.next().strip();
	}

	public String getImageName() {
		Scanner s = new Scanner(System.in);
		return s.next().strip();
	}

	public double[][] userChoice() throws Exception {
		System.out.println("Please choose an option from below:");
		System.out.println("1: Enter text file path.");
		System.out.println("2: Enter URL path.");
		System.out.println("3: Exit.");

		while (keepRunning) {

			Scanner scan = new Scanner(System.in);
			int choice = scan.nextInt();

			switch (choice) {
			case 1:
				fileChoice();

			case 2:
				urlChoice();

			case 3:
				System.out.println("Application is exiting.");
				System.exit(1);
			default:
				System.out.println("Invalid input. Please try again.");
				System.exit(1);
			}
			scan.close();
		}
		return null;
	}

	public String setImageName() {
		Scanner s = new Scanner(System.in);
		String finalImageName;

		finalImageName = s.nextLine();
		return finalImageName;
	}

	void fileChoice() throws IOException {
		System.out.println("Please enter .txt file path.");
		FileParser fp = new FileParser();
		fp.loadFileFromDisk(getFilePath());

		System.out.println("Please choose a name for your image.");
		setImageName();

	}

	void urlChoice() throws IOException {
		System.out.println("Please enter URL.");
		getURLPath();
		FileParser fp = new FileParser();
		fp.loadFileFromURL(getURLPath());

		System.out.println("Please choose a name for your image.");
		setImageName();
	}
}
