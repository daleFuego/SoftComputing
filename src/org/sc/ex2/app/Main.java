package org.sc.ex2.app;

import java.util.Arrays;

import org.sc.ex2.Madaline;
import org.sc.ex2.patterns.InputPatterns;

public class Main {

	public static void main(String[] args) {

		InputPatterns inputPatterns = new InputPatterns(Utils.PATH_TO_PATTERNS);

		double[][] inputValues = inputPatterns.getInputValues();
		double[][][] weights = new double[1][Utils.NUMBER_OF_PATTERNS][Utils.LENGHT_OF_PATTERN];

		Madaline madaline = new Madaline();

		for (int i = 0; i < Utils.NUMBER_OF_PATTERNS; i++) {
			for (int j = 0; j < Utils.LENGHT_OF_PATTERN; j++) {
				weights[0][i][j] = inputValues[i][j] / Utils.LENGHT_OF_PATTERN;
			}
		}

		madaline.setAllWeights(weights);

		System.out.println(Arrays.toString(madaline.epoc(inputValues[Utils.convertToASCII('C')])));
	}

}
