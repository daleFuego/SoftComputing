package org.sc.ex2;

import org.sc.commons.Utils;

public class Main {

	public static void main(String[] args) {

		InputPatterns inputPatterns = new InputPatterns(Utils.PATH_TO_PATTERNS);
		Utils.NUMBER_OF_PATTERNS = inputPatterns.getFileCount();

		double[][] inputValues = inputPatterns.getInputValues();
		double[][] weights = new double[Utils.NUMBER_OF_PATTERNS][Utils.LENGHT_OF_PATTERN];
		double[] outputValues = new double[Utils.NUMBER_OF_PATTERNS];

		for (int i = 0; i < Utils.NUMBER_OF_PATTERNS; i++) {
			int detectedOnes = 0;
			for (int j = 0; j < Utils.LENGHT_OF_PATTERN; j++) {
				weights[i][j] = inputValues[i][j];
			}
			for (int j = 0; j < Utils.LENGHT_OF_PATTERN; j++) {
				if (weights[i][j] == 1) {
					detectedOnes++;
				}
			}
			for (int j = 0; j < Utils.LENGHT_OF_PATTERN; j++) {
				weights[i][j] = weights[i][j] / Math.sqrt(detectedOnes);
			}
		}

		InputPatterns inputTest = new InputPatterns(Utils.PATH_TO_TEST);
		Utils.NUMBER_OF_TESTS = inputTest.getFileCount();

		double[][] testValues = inputTest.getInputValues();
		double[][] inputsTest = new double[Utils.NUMBER_OF_PATTERNS][Utils.LENGHT_OF_PATTERN];

		for (int i = 0; i < Utils.NUMBER_OF_PATTERNS; i++) {
			int detectedOnes = 0;
			for (int j = 0; j < Utils.LENGHT_OF_PATTERN; j++) {
				inputsTest[i][j] = testValues[i][j];
			}
			for (int j = 0; j < Utils.LENGHT_OF_PATTERN; j++) {
				if (inputsTest[i][j] == 1) {
					detectedOnes++;
				}
			}
			for (int j = 0; j < Utils.LENGHT_OF_PATTERN; j++) {
				inputsTest[i][j] = inputsTest[i][j] / Math.sqrt(detectedOnes);
			}
		}

		for (int i = 0; i < Utils.NUMBER_OF_PATTERNS; i++) {
			System.out.println("---------- PATTERN Nr " + (i + 1) + " ----------");
			for (int k = 0; k < 4; k++) {
				outputValues[k] = 0;
				for (int j = 0; j < inputsTest[i].length; j++) {
					outputValues[k] += weights[k][j] * inputsTest[i][j];
				}
				System.out.println("TEST Nr " + (k + 1) + " = " + outputValues[k]);
			}
		}
	}

}
