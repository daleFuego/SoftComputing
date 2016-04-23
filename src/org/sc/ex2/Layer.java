package org.sc.ex2;

import java.util.Arrays;

import org.sc.ex2.app.Utils;

public class Layer {

	private Neuron neurons[];
	private double[] errors;
	private int numUnits;

	public Layer(String typeOfNeuron, int numUnits, int numOfEntrences) {
		this.numUnits = numUnits;

		neurons = new Neuron[numUnits];
		errors = new double[numUnits];
		
		Arrays.fill(this.errors, 0.0);
		
		switch (typeOfNeuron) {
		case Utils.NEURON_INPUT:
			for (int i = 0; i < numUnits; i++) {
				neurons[i] = new Neuron(1, typeOfNeuron);
			}
			break;
		case Utils.NEURON_OUTPUT:
			for (int i = 0; i < numUnits; i++) {
				neurons[i] = new Neuron(numOfEntrences, typeOfNeuron);
			}
			break;
		}
	}

	public Neuron[] getNeurons() {
		return neurons;
	}

	public Neuron getNeuron(int i) {
		return neurons[i];
	}

	public int getNumUnits() {
		return numUnits;
	}
}
