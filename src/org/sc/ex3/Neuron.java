package org.sc.ex3;

public class Neuron {

	private double output;
	private boolean isBias;
	private NeuronType neuronType;
	private double errorSignal;
	private double[] weights;

	public Neuron(NeuronType neuronType, boolean isBias) {
		this.isBias = isBias;
		this.neuronType = neuronType;

		if (isBias) {
			output = 1;
		} else {
			switch (this.neuronType) {
			case Input:
				output = 0.0;
				break;
			case Hidden:
				output = 0.0;
				break;
			case Output:
				output = 0.0;
				break;
			default:
			}
		}
	}

	public double getOutput() {
		return output;
	}

	public void updateOutput() {
		if (isBias) {
			output = 1;
		} else {
			// TODO
		}
	}

	public NeuronType getNeuronType() {
		return neuronType;
	}

	public void setOutput(int trainingPatternValue) {
		if (isBias) {
			output = 1;
		} else {
			output = trainingPatternValue;
		}
	}

	public void setErrorSignal(double errorSignal) {
		this.errorSignal = errorSignal;
	}

	public double getErrorSignal() {
		return errorSignal;
	}

	public double getWeightedSum() {
		double retValue = 0;

		for (int j = 1; j < weights.length; j++) {
			retValue = weights[j] * output;
		}

		return retValue;
	}

	public void setWeights(double[] weights) {
		this.weights = weights;
	}

	public double[] getWeights() {
		return weights;
	}

	public double getWeight(int index) {
		return weights[index];
	}
}
