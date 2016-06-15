package org.sc.ex4;

public interface Fitness<C extends Chromosome<C>, T extends Comparable<T>> {

	T calculate(C chromosome);

}
