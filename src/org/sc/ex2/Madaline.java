package org.sc.ex2;

import org.sc.ex2.app.Utils;

public class Madaline {

	private Layer[] layers;

	public Madaline() {
		layers = new Layer[Utils.NUMBER_OF_LAYERS];
		layers[0] = new Layer(Utils.NEURON_INPUT, Utils.LENGHT_OF_PATTERN, 1);
		layers[1] = new Layer(Utils.NEURON_OUTPUT, Utils.NUMBER_OF_PATTERNS, Utils.LENGHT_OF_PATTERN);
	}

	public void setAllWeights(double[][][] weights) {
		for (int i = 1; i < layers.length; i++) {
			for (int j = 0; j < weights[i - 1].length; j++) {
				layers[i].getNeuron(j).setWeights(weights[i - 1][j]);
			}
		}
	}

	private void putLayerEntrences(int index) {
		double[] outputs = new double[layers[index - 1].getNumUnits()];

		for (int i = 0; i < layers[index - 1].getNumUnits(); i++) {
			outputs[i] = layers[index - 1].getNeuron(i).getOutput();
		}

		for (int i = 0; i < layers[index].getNumUnits(); i++) {
			layers[index].getNeuron(i).setEntrence(outputs);
		}
	}

	private void updateLayerOutput(int index) {
		for (int i = 0; i < layers[index].getNumUnits(); i++) {
			layers[index].getNeuron(i).updateOutput();
		}
	}

	private void setEntrencesValues(double[] n) {
		for (int i = 0; i < n.length; i++) {
			layers[0].getNeuron(i).setEntrencesNeuronsInput(n[i]);
		}
	}

	void setWeight(double[] weights, int layer, int neuron) {
		layers[layer].getNeuron(neuron).setWeights(weights);
	}

	public double[] epoc(double[] characterToRecognize) {
		setEntrencesValues(characterToRecognize);

		for (int i = 0; i < layers.length; i++) {
			updateLayerOutput(i);

			if (i != layers.length - 1)
				putLayerEntrences(i + 1);
		}

		Neuron[] neurons = this.layers[this.layers.length - 1].getNeurons();
		double[] output = new double[neurons.length];
		for (int i = 0; i < neurons.length; i++) {
			output[i] = neurons[i].getOutput();
		}

		return output;
	}
}
