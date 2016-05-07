package org.sc.ex3;

import org.sc.commons.Utils;

public class Layer {

	private Neuron neurons[];
	private NeuronType neuronType;

	public Layer(NeuronType neuronType, boolean isBias) {

		this.neuronType = neuronType;

		switch (neuronType) {
		case Input:
			neurons = new Neuron[Utils.NUMBER_OF_INPUT_NEURONS];
			for (int i = 0; i < neurons.length; i++) {
				neurons[i] = new Neuron(neuronType, isBias);
			}
			break;
		case Hidden:
			neurons = new Neuron[Utils.NUMBER_OF_HIDDEN_NEURONS];
			for (int i = 0; i < neurons.length; i++) {
				neurons[i] = new Neuron(neuronType, isBias);
			}
			break;
		case Output:
			neurons = new Neuron[Utils.NUMBER_OF_OUTPUT_NEURONS];
			for (int i = 0; i < neurons.length; i++) {
				neurons[i] = new Neuron(neuronType, isBias);
			}
			break;
		default:
		}
	}

	public Neuron[] getNeurons() {
		return neurons;
	}

	public void setNeurons(Neuron[] neurons) {
		this.neurons = neurons;
	}

	public String getLayerType(){
		return neuronType.toString();
	}
}
