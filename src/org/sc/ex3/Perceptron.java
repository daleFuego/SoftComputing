package org.sc.ex3;

public class Perceptron {

	private Layer[] layers = new Layer[NeuronType.values().length];

	public Perceptron() {
		for (int i = 0; i < NeuronType.values().length; i++) {
			this.layers[i] = new Layer(NeuronType.values()[i], false);
		}
	}

	public Layer[] getLayers() {
		return layers;
	}

	public Neuron[] getInputNeurons() {
		return layers[0].getNeurons();
	}
	
	public Neuron[] getHiddenNeurons() {
		return layers[1].getNeurons();
	}
	
	public Neuron[] getOutputNeurons() {
		return layers[2].getNeurons();
	}
}