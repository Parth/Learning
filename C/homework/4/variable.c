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

void DECODER(int n, var* inputs, var* outputs) {
}

int get_number(var* inputs, int n) {
	int i;
	int number = 0;
	for (i = n; i >= 0; i--) {
		int factor = (int) pow(2, i);
		number = number + (factor * inputs[i].value);
	}
	return number;
}

char* to_string(var* a) {
	char* str; 
	asprintf(&str, "%c = %d", a -> name, a -> value);
	return str;
}
