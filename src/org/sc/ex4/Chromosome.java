package org.sc.ex4;

import java.util.List;

public interface Chromosome<C extends Chromosome<C>> {
	
	List<C> crossover( C anotherChromosome );
	
	C mutate();

}
