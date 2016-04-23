package org.sc.ex2.app;

public class Utils {

	public static final int NUMBER_OF_LAYERS = 2;
	public static int NUMBER_OF_PATTERNS = 0;
	public static int LENGHT_OF_PATTERN = 0;
	
	public static String PATH_TO_PATTERNS = "C:\\Users\\Magdalena\\Documents\\Szko³a\\Computer Science and Information Technology\\Semestr 1\\Soft Computing\\Patterns for Task2";
	public static final String NEURON_INPUT = "INPUT";
	public static final String NEURON_OUTPUT = "OUTPUT";

	public static int convertToASCII(char letter) {
		if (letter <= 90) {
			return letter - 65;
		} else {
			return letter - 97;
		}
	}
	
}
