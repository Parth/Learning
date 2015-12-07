#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#include "variable.h"

var* new_var(char name, int value) {
	var* v = (var*) malloc(sizeof(var));
	v -> name = name;
	v -> value = value;
	return v;
}

int AND(var* a, var* b) {
	return (a -> value) && (b -> value);
}

int OR(var* a, var* b) {
	return (a -> value) || (b -> value);
}

void DECODER(int n_inputs, var** inputs, var** outputs) {
	int n_outputs = (int) pow(2, n_inputs);
	int the_one_thats_true = dec_to_grey(get_number(inputs, n_inputs));
	int i;
	for (i = 0; i < n_outputs; i++) {
		outputs[i] -> value = (i == the_one_thats_true);
	}
}

void MULTIPLEXER(int n_inputs, var** inputs, var** selectors, var* output) {
	int n_selectors = (int) ceil(log2(n_inputs));
	int the_one_that_is_output = dec_to_grey(get_number(selectors, n_selectors));
	output -> value = inputs[the_one_that_is_output] -> value;
}

int get_number(var** inputs, int n) {
	int i;
	int number = 0;
	for (i = 0; i < n; i++) {
		int factor = (int) pow(2, i);
		number = number + (factor * inputs[i] -> value);
	}
	return number;
}

int dec_to_grey(int n) {
	return (n >> 1) ^ n;
}

char* to_string(var* a) {
	printf("%c = %d\n", a -> name, a -> value);
	return "sorry, no asprintf here";
}
