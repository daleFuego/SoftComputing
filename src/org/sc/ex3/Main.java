package org.sc.ex3;

import org.sc.commons.Utils;

public class Main {

	private static double[] inputWeights = { 0.05, 0.5 };
	private static double[] hiddenWeights = new double[Utils.NUMBER_OF_INPUT_NEURONS];
	private static double trainingStep = 0.1;
	private static boolean isNetworkWellTrained = true;

	public static void main(String[] args) {
		Perceptron perceptron = new Perceptron();

		// Utils.PATTERNS[0] - input values
		// Utils.PATTERNS[1] - expected output values

		do {
			for (int indexOfPattern = 0; indexOfPattern < Utils.PATTERNS.length; indexOfPattern++) {
				int[][] trainingPattern = Utils.PATTERNS[indexOfPattern];

				for (int indexOfInputNeuron = 0; indexOfInputNeuron < Utils.NUMBER_OF_INPUT_NEURONS; indexOfInputNeuron++) {
					Neuron inputNeuron = perceptron.getInputNeurons()[indexOfInputNeuron];
					inputNeuron.setOutput(trainingPattern[0][indexOfInputNeuron]);
				}

				for (int indexOfOutputNeuron = 0; indexOfOutputNeuron < Utils.NUMBER_OF_OUTPUT_NEURONS; indexOfOutputNeuron++) {
					Neuron outputNeuron = perceptron.getOutputNeurons()[indexOfOutputNeuron];
					outputNeuron.setWeights(inputWeights);
					double errorSignal = signoidFunctionDerivative(outputNeuron.getWeightedSum())
							* (trainingPattern[1][indexOfOutputNeuron] - outputNeuron.getOutput());
					outputNeuron.setErrorSignal(errorSignal);
				}

				for (int indexOfHiddenNeuron = 0; indexOfHiddenNeuron < Utils.NUMBER_OF_HIDDEN_NEURONS; indexOfHiddenNeuron++) {
					Neuron hiddenNeuron = perceptron.getHiddenNeurons()[indexOfHiddenNeuron];
					hiddenNeuron.setWeights(hiddenWeights);

					double sumOfOutputNeuronWeights = 0;

					for (int indexOfOutputNeuron = 0; indexOfOutputNeuron < Utils.NUMBER_OF_OUTPUT_NEURONS; indexOfOutputNeuron++) {
						Neuron outputNeuron = perceptron.getOutputNeurons()[indexOfOutputNeuron];
						sumOfOutputNeuronWeights += outputNeuron.getWeight(1) * outputNeuron.getErrorSignal();
					}

					double errorSignal = signoidFunctionDerivative(hiddenNeuron.getWeightedSum())
							* sumOfOutputNeuronWeights;
					hiddenNeuron.setErrorSignal(errorSignal);
				}

				for (int indexOfHiddenNeuron = 0; indexOfHiddenNeuron < Utils.NUMBER_OF_HIDDEN_NEURONS; indexOfHiddenNeuron++) {
					Neuron hiddenNeuron = perceptron.getHiddenNeurons()[indexOfHiddenNeuron];
					double[] updatedWeights = new double[Utils.NUMBER_OF_INPUT_NEURONS];
					for (int index = 0; index < hiddenNeuron.getWeights().length; index++) {
						updatedWeights[index] = hiddenNeuron.getWeight(index)
								+ trainingStep * hiddenNeuron.getErrorSignal() * hiddenNeuron.getOutput();
					}
					hiddenNeuron.setWeights(updatedWeights);
				}
				for (int indexOfOutputNeuron = 0; indexOfOutputNeuron < Utils.NUMBER_OF_OUTPUT_NEURONS; indexOfOutputNeuron++) {
					Neuron outputNeuron = perceptron.getOutputNeurons()[indexOfOutputNeuron];
					double[] updatedWeights = new double[Utils.NUMBER_OF_OUTPUT_NEURONS];
					for (int index = 0; index < outputNeuron.getWeights().length; index++) {
						updatedWeights[index] = outputNeuron.getWeight(index)
								+ trainingStep * outputNeuron.getErrorSignal() * outputNeuron.getOutput();
					}
					outputNeuron.setWeights(updatedWeights);
				}
			}

			for (int indexOfPattern = 0; indexOfPattern < Utils.PATTERNS.length; indexOfPattern++) {
				int[][] trainingPattern = Utils.PATTERNS[indexOfPattern];

				for (int indexOfOutputNeuron = 0; indexOfOutputNeuron < Utils.NUMBER_OF_OUTPUT_NEURONS; indexOfOutputNeuron++) {
					Neuron outputNeuron = perceptron.getOutputNeurons()[indexOfOutputNeuron];
					if (trainingPattern[1][indexOfOutputNeuron] != outputNeuron.getOutput()) {
						isNetworkWellTrained = false;
					}
				}
			}

			System.out.println("Is network well trained? " + isNetworkWellTrained);
		} while (!isNetworkWellTrained);

	}

	private static double signoidFunctionDerivative(double weightedSum) {
		double retValue = 0;

		// TODO

		return retValue;
	}
}
