package org.sc.ex2;

import org.sc.ex2.app.Utils;

public class Neuron {
	private double[] entrence;
	private double[] weights;
	private double output;

	public Neuron(int numOfEntrences, String typeOfNeuron) {
		switch (typeOfNeuron) {
		case Utils.NEURON_INPUT:
			weights = new double[1];
			weights[0] = 1.0;
			entrence = new double[1];
			output = 0.0;

			break;
		case Utils.NEURON_OUTPUT:
			weights = new double[numOfEntrences];
			entrence = new double[numOfEntrences];
			output = 0.0;

			for (int i = 0; i < weights.length; i++) {
				weights[i] = Math.random() - 0.5;
			}

			break;
		default:
		}
	}

	public void updateOutput() {
		for (int i = 0; i < weights.length; i++) {
			output += weights[i] * entrence[i];
		}
	}

	public void setEntrence(double[] entrence) {
		this.entrence = entrence;
	}

	public void setEntrencesNeuronsInput(double e) {
		this.entrence[0] = e;
	}

	public void setWeights(double[] weights) {
		this.weights = weights;
	}

	public double getOutput() {
		return output;
	}
}
