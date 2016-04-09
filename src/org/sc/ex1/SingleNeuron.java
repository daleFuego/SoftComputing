package org.sc.ex1;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.junit.Test;

public class SingleNeuron {

	int[] inputValues = {1, 5};
	int outputValue = -2;		
	double marginForError = 0.00000001;
	double trainingStep = 0.001;
	double[] weights;

	public SingleNeuron() {
		weights = new double[inputValues.length];
	}

	public void deltaRule() {
		boolean allCorrect = false;
		
		while (!allCorrect) {
			double output = generateValue();
			for (int k = 0; k < inputValues.length; k++) {
				weights[k] = weights[k] + trainingStep * inputValues[k] * (outputValue - output);
			}
			if (Math.abs((output - outputValue)) < marginForError){
				allCorrect = true;}
		}

		System.out.println("Expected value = " + outputValue);
		System.out.println("Received value = " + generateValue());

	}

	double generateValue() {
		double result = 0;
		for (int i = 0; i < inputValues.length; i++) {
			result += weights[i] * inputValues[i];
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
	public void testSingleNeuron() {

		SingleNeuron p = new SingleNeuron();
		System.out.println("Weights before training: ");
		p.printMatrix(p.weights);
		p.deltaRule();
		System.out.println("Weights after training: ");
		p.printMatrix(p.weights);
	}
}
