package org.sc.ex3;

import java.util.Random;

public class HiddenNeuron {

	private double[] weights;
	public double S1;
	public double V1;
	public double D1;

	public void initWeights(int numberOfWeights) {
		weights = new double[numberOfWeights];
		for (int i = 0; i < weights.length; i++) {
			weights[i] = new Random().nextDouble();
		}
	}

	public void functionS1(InputNeuron[] inputNeurons) {
		for (int i = 0; i < weights.length; i++) {
			S1 += inputNeurons[i].inputValue * weights[i];
		}
	}

	public void functionV1() {
		V1 = (1 / (1 + Math.pow(Math.E, (-1) * S1)));
	}

	public void functionD1(OutputNeuron[] outputNeurons, int hiddenNeuron) {
		double sum = 0;
		
		for (int i = 0; i < outputNeurons.length; i++) {
			sum += outputNeurons[i].D2 * outputNeurons[i].weights[hiddenNeuron];
		}
		
		D1 = sum * (V1 * (1 - V1));
	}

	public void updateWeights(double learningFactor, InputNeuron[] inputNeurons) {
		for(int i = 0; i < weights.length; i++){
			weights[i] = weights[i] + learningFactor*(D1)*inputNeurons[i].inputValue;
		}
	}

}
