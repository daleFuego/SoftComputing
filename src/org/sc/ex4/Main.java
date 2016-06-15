package org.sc.ex4;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

	private static double target;
	private static double threshold = 1e-5;

	public static void main(String[] args) {

		double functionOutput = 1.35;
		for (int x = 0; x < 5; x++) {
			functionOutput = (Math.pow(Math.E, x) * Math.sin(10 * Math.PI * x) + 1) / (x + 5);
			System.out.println("\nRequired value -> " + functionOutput);

			target = functionOutput;

			GeneticAlgorithm<MyVector, Double> ga = new GeneticAlgorithm<MyVector, Double>(createInitialPopulation(100), new MyVectorFitness());
			
			addListener(ga);
			ga.evolve(1000);
		}

	}

	private static Population<MyVector> createInitialPopulation(int populationSize) {
		Population<MyVector> population = new Population<MyVector>();
		MyVector base = new MyVector();
		for (int i = 0; i < populationSize; i++) {
			MyVector chr = base.mutate();
			population.addChromosome(chr);
		}
		return population;
	}

	private static void addListener(GeneticAlgorithm<MyVector, Double> ga) {
		System.out.println("Iteration\tFitness\t\t\t\tChromosome");

		ga.addIterationListener(new IterartionListener<MyVector, Double>() {

			@Override
			public void update(GeneticAlgorithm<MyVector, Double> ga) {

				MyVector best = ga.getBest();
				double bestFit = ga.fitness(best);
				int iteration = ga.getIteration();

				System.out.println(iteration + "\t\t" + bestFit + "\t\t" + best.vector[0]);

				if (bestFit < threshold) {
					ga.terminate();
				}
			}
		});
	}

	public static class MyVector implements Chromosome<MyVector>, Cloneable {

		private static final Random random = new Random();

		private final double[] vector = new double[2];

		@Override
		public MyVector mutate() {
			MyVector result = this.clone();

			int index = random.nextInt(this.vector.length);
			double mutationValue = random.nextDouble() - random.nextDouble();
			result.vector[index] += mutationValue;

			return result;
		}

		@Override
		public List<MyVector> crossover(MyVector other) {
			MyVector thisClone = this.clone();
			MyVector otherClone = other.clone();

			int index = random.nextInt(this.vector.length - 1);
			for (int i = index; i < this.vector.length; i++) {
				double tmp = thisClone.vector[i];
				thisClone.vector[i] = otherClone.vector[i];
				otherClone.vector[i] = tmp;
			}

			return Arrays.asList(thisClone, otherClone);
		}

		@Override
		protected MyVector clone() {
			MyVector clone = new MyVector();
			System.arraycopy(this.vector, 0, clone.vector, 0, this.vector.length);
			return clone;
		}

		public double[] getVector() {
			return this.vector;
		}

		@Override
		public String toString() {
			return Arrays.toString(this.vector);
		}
	}

	public static class MyVectorFitness implements Fitness<MyVector, Double> {

		@Override
		public Double calculate(MyVector chromosome) {
			return this.sqr(chromosome.getVector()[0] - target);
		}
		
		private double sqr(double x) {
			return x * x;
		}
	}
}
