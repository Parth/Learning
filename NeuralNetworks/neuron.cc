// neuron.cc

#include "neuron.h"

#include <cstdlib>
#include <ctime>
#include <algorithm>
#include <cstddef>
#include <cassert>

Neuron::Neuron(std::vector<Neuron*> inputs) {
	// copy inputs
	this -> inputs = inputs;
	// init to random (-1, 1)
	for(int i = 0; i < weights.size(); i++) {
		weights[i] = (static_cast <float> (rand()) / static_cast <float> (RAND_MAX)) * 2 - 1;
	}
	bias = (static_cast <float> (rand()) / static_cast <float> (RAND_MAX)) * 2 - 1;
	// init value
	value = 0;
}

void Neuron::fire() {
	float sum = 0;
	for(int i = 0; i < inputs.size(); i++) {
		sum += inputs[i].value * weights[i];
	}
	sum += bias;

	if(sum > 0) {
		value = 1;
	} else {
		value = 0;
	}

	history.push_back(value);
}

float **Neuron::rref(float **in, int dim_x, int dim_y) {
	// copy the array into a new one
	float **result = new float*[dim_x];
	for(int x = 0; x < dim_x; x++) {
		result[x] = new float[dim_y];
	}
	// iterate over columns
	for(int c = 0; c < dim_x; c++) {
		// iterate over rows
		for(int y = 0; y < dim_y; y++) {
			// if the first element of a row is not zero, divide the row by the first element
			if(result[0][y] != 0) {
				for(int )
			}
		}
	}
}