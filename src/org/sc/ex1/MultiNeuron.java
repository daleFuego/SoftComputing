package org.sc.ex1;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.junit.Test;

public class MultiNeuron {

	int[][] inputValues = { { 1, 5 }, { 0, 4 } };
	int[] outputValue = { -2, -4, 7 };
	double marginForError = 0.00000001;
	double trainingStep = 0.001;
	double[] weights;

	int neuronInputs = inputValues.length;

	public MultiNeuron() {
		weights = new double[outputValue.length];
	}

	public void deltaRule() {
		boolean allCorrect = false;
		
		while (!allCorrect) {
			for (int i = 0; i < outputValue.length; i++) {
				double output = generateValue(i);
				for (int m = 0; m < inputValues.length; m++) {
					for (int n = 0; n < inputValues[m].length; n++) {
						weights[i] = weights[i] + trainingStep * inputValues[m][n] * (outputValue[i] - output);
					}
				}
				System.out.println("received " + output + " expected " + outputValue[i] + " diff = "
						+ (output - outputValue[i]));
				if (Math.abs((output - outputValue[i])) < marginForError) {
					allCorrect = true;
				}
			}
		}


	}

	double generateValue(int i) {
		double result = 0;
			for (int m = 0; m < inputValues.length; m++)
				for (int n = 0; n < inputValues.length; n++) {
					result = weights[i] * inputValues[m][n];
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
