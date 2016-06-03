package org.sc.ex3;

import java.util.Random;

public class OutputNeuron {

	public double[] weights;
	public double S2;
	public double V2;
	public double D2;

	public void initWeights(int numberOfWeights) {
		weights = new double[numberOfWeights];
		for (int i = 0; i < weights.length; i++) {
			weights[i] = new Random().nextDouble();
		}
	}

	public void functionS2(HiddenNeuron[] hiddenNeurons) {
		for (int i = 0; i < weights.length; i++) {
			S2 += hiddenNeurons[i].V1 + weights[i];
		}
	}

	public void functionV2() {
		V2 = (1 / (1 + Math.pow(Math.E, (-1) * S2)));
	}

	public void D2(int patternOutput) {
		D2 = (patternOutput - V2) * (V2 * (1 - V2));
	}

	public void updateWeights(double learningFactor, HiddenNeuron[] hiddenNeurons) {
		for (int i = 0; i < weights.length; i++) {
			weights[i] = weights[i] + learningFactor * (D2) * hiddenNeurons[i].V1;
		}
	}

}
