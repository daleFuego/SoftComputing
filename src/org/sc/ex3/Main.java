package org.sc.ex3;

import org.sc.commons.Utils;

public class Main {

	private static InputNeuron[] inputNeurons = new InputNeuron[Utils.NUMBER_OF_INPUT_NEURONS];
	private static HiddenNeuron[] hiddenNeurons = new HiddenNeuron[Utils.NUMBER_OF_HIDDEN_NEURONS];
	private static OutputNeuron[] outputNeurons = new OutputNeuron[Utils.NUMBER_OF_OUTPUT_NEURONS];
	private static double learningFactor = 0.1;

	public static void main(String[] args) {

		for (int inputNeuron = 0; inputNeuron < Utils.NUMBER_OF_INPUT_NEURONS; inputNeuron++) {
			inputNeurons[inputNeuron] = new InputNeuron();
		}

		for (int hiddenNeuron = 0; hiddenNeuron < Utils.NUMBER_OF_HIDDEN_NEURONS; hiddenNeuron++) {
			hiddenNeurons[hiddenNeuron] = new HiddenNeuron();
			hiddenNeurons[hiddenNeuron].initWeights(Utils.NUMBER_OF_INPUT_NEURONS);
		}

		for (int outputNeuron = 0; outputNeuron < Utils.NUMBER_OF_OUTPUT_NEURONS; outputNeuron++) {
			outputNeurons[outputNeuron] = new OutputNeuron();
			outputNeurons[outputNeuron].initWeights(Utils.NUMBER_OF_HIDDEN_NEURONS);
		}

		for (int i = 0; i < 1000; i++) {
			for (int inputPattern = 0; inputPattern < Utils.PATTERNS.length - 2; inputPattern++) {
				System.out.println("Pattern " + inputPattern);

				for (int inputNeuron = 0; inputNeuron < Utils.NUMBER_OF_INPUT_NEURONS; inputNeuron++) {
					inputNeurons[inputNeuron].inputValue = Utils.PATTERNS[inputPattern][0][inputNeuron];
					System.out.println("Input " + inputNeuron + " : " + inputNeurons[inputNeuron].inputValue);
				}

				for (int hiddenNeuron = 0; hiddenNeuron < Utils.NUMBER_OF_HIDDEN_NEURONS; hiddenNeuron++) {
					hiddenNeurons[hiddenNeuron].functionS1(inputNeurons);
					hiddenNeurons[hiddenNeuron].functionV1();
				}

				for (int outputNeuron = 0; outputNeuron < Utils.NUMBER_OF_OUTPUT_NEURONS; outputNeuron++) {
					outputNeurons[outputNeuron].functionS2(hiddenNeurons);
					outputNeurons[outputNeuron].functionV2();
				}
				// End of step 1

				for (int outputNeuron = 0; outputNeuron < Utils.NUMBER_OF_OUTPUT_NEURONS; outputNeuron++) {
					outputNeurons[outputNeuron].D2(Utils.PATTERNS[inputPattern][1][outputNeuron]);
				}

				for (int hiddenNeuron = 0; hiddenNeuron < Utils.NUMBER_OF_HIDDEN_NEURONS; hiddenNeuron++) {
					hiddenNeurons[hiddenNeuron].functionD1(outputNeurons, hiddenNeuron);
				}
				// End of step 2

				for (int hiddenNeuron = 0; hiddenNeuron < Utils.NUMBER_OF_HIDDEN_NEURONS; hiddenNeuron++) {
					hiddenNeurons[hiddenNeuron].updateWeights(learningFactor, inputNeurons);
				}

				for (int outputNeuron = 0; outputNeuron < Utils.NUMBER_OF_OUTPUT_NEURONS; outputNeuron++) {
					outputNeurons[outputNeuron].updateWeights(learningFactor, hiddenNeurons);
				}
				// End of step 3

				for (int outputNeuron = 0; outputNeuron < Utils.NUMBER_OF_OUTPUT_NEURONS; outputNeuron++) {
					System.out.println(outputNeuron + " : " + outputNeurons[outputNeuron].V2);
				}
			}
		}

	}

}