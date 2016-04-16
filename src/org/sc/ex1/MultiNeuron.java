package org.sc.ex1;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

import org.junit.Test;

public class MultiNeuron {

	int[][] inputValues = { { 1, 5, 3 }, { 2, 0, -1 }, { 12, 3, -1 }, { 0, 4, -2 } };
	int[] outputValue = { -12, 5, 2, 9 };
	double marginForError = 0.00000001;
	double trainingStep = 0.005;
	double[] weights;

	int neuronInputs = inputValues[0].length;
	private int MAX_ITERATIONS = 100000;

	public MultiNeuron() {
		weights = new double[inputValues[0].length];

		for (int i = 0; i < weights.length; i++) {
			weights[i] = new Random().nextDouble();
		}
	}

	public void deltaRule() {
		for (int index = 0; index < MAX_ITERATIONS; index++) {
			System.out.println("Iteration no " + index);
			for (int m = 0; m < outputValue.length; m++) {
				double output = generateValue(m);
				for (int i = 0; i < inputValues[0].length; i++) {
					weights[i] = weights[i] + trainingStep * inputValues[m][i] * (outputValue[m] - output);
				}
				System.out.println(
						"received " + output + " expected " + outputValue[m] + " diff = " + (output - outputValue[m]));
			}
		}
	}

	double generateValue(int m) {
		double result = 0;
		for (int i = 0; i < inputValues[0].length; i++) {
			result += weights[i] * inputValues[m][i];
		}
		return result;
	}

	public void printMatrix(double[] matrix) {

		for (int i = 0; i < matrix.length; i++) {
			NumberFormat f = NumberFormat.getInstance();
			if (f instanceof DecimalFormat) {
				DecimalFormat decimalFormat = ((DecimalFormat) f);
				decimalFormat.setMaximumFractionDigits(1);
				decimalFormat.setMinimumFractionDigits(1);
				System.out.println("" + f.format(matrix[i]) + "");
			}
		}
	}

	@Test
	public void testMultiNeuron() {

		MultiNeuron p = new MultiNeuron();
		System.out.println("Weights before training: ");
		p.printMatrix(p.weights);
		p.deltaRule();
		System.out.println("Weights after training: ");
		p.printMatrix(p.weights);
	}
}
