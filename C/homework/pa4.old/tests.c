#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "variable.h"

int test_constructor() {
	puts("\tTesting Constructor");
	var* v = new_var('A', 1);
	if (v -> name == 'A' && v -> value == 1) {
		puts("\tPassed");
		return 0;
	} else {
		puts("Failed");
		return 1;
	}
	free(v);
} 
int test_AND_1() {
	puts("\tTesting AND 1");
	var* a = new_var('A', 1);
	var* b = new_var('B', 1);
	if (AND(a, b) == 1) {
		puts("\tPassed");
		return 0;
	} else {
		puts ("Failed");
		return 1;
	}
	free(a);
	free(b);
}

int test_AND_2() {
	puts("\tTesting AND 2");
	var* a = new_var('A', 1);
	var* b = new_var('B', 0);
	if (AND(a, b) == 0) {
		puts("\tPassed");
		return 0;
	} else {
		puts ("Failed");
		return 1;
	}
	free(a);
	free(b);
}

int test_OR_1() {
	puts("\tTesting OR 1");
	var* a = new_var('A', 1);
	var* b = new_var('B', 0);
	if (OR(a, b) == 1) {
		puts("\tPassed");
		return 0;
	} else {
		puts ("Failed");
		return 1;
	}
	free(a);
	free(b);
}

int test_OR_2() {
	puts("\tTesting OR 2");
	var* a = new_var('A', 0);
	var* b = new_var('B', 0);
	if (OR(a, b) == 0) {
		puts("\tPassed");
		return 0;
	} else {
		puts ("Failed");
		return 1;
	}
}

int test_to_string() {
	puts("\tTesting to_string");
	var* a = new_var('A', 0);
	if (strcmp(to_string(a), "A = 0") == 0) {
		puts("\tPassed");
		return 0;
	} else {
		puts("Failed");
		return 1;
	}
	free(a);
}

int test_get_number_1() {
	puts("\tTesting get_number 1");
	var* a = new_var('A', 1); 
	var* b = new_var('B', 0); 
	var* c = new_var('C', 1); 
	var* d = new_var('D', 0); 
	var* e = new_var('E', 0); 
	var* f = new_var('F', 1); 
	var* g = new_var('G', 1); 
	var* h = new_var('H', 1); 
	var** var_arr = (var **) malloc(8*sizeof(var *));
	var_arr[0] = a;
	var_arr[1] = b;
	var_arr[2] = c;
	var_arr[3] = d;
	var_arr[4] = e;
	var_arr[5] = f;
	var_arr[6] = g;
	var_arr[7] = h;
	
	if (get_number(var_arr, 8) == 229) {
		puts("\tPassed");
		return 0;
	} else {
		puts("Failed");
		return 1;
	}

	int i;
	for (i = 0; i < 8; i++) {
		free(var_arr[i]);
	}
	free(var_arr);
}

int test_get_number_2() {
	puts("\tTesting get_number 2");
	var* a = new_var('A', 1);
	var** var_arr = (var **) malloc(1 * sizeof(var *));
	var_arr[0] = a;

	if (get_number(var_arr, 1) == 1) {
		puts("\tPassed");
	} else {
		puts("\tFailed");
		return 1;
	}
	
	var_arr[0] -> value = 0;
	if (get_number(var_arr, 1) == 0) {
		puts("\tPassed");
		return 0;
	} else {
		puts("\tFailed");
		return 1;
	}

	int i;
	for (i = 0; i < 1; i++) {
		free(var_arr[i]);
	}
	free(var_arr);
}

int test_dec_to_grey() {
	puts("\tTest dec_to_grey");
	int ins[] =		{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
	int outs[] =	{0, 1, 3, 2, 6, 7, 5, 4, 12, 13};

	for (int i = 0; i < 10; i++) {
		if (dec_to_grey(ins[i]) != outs[i]) {
			printf("Failed at index: %d\n", i);
			return 1;
		}
	}
	puts("\tPassed");
	return 0;
}

int test_DECODER_1() {
	var** ins = (var **) malloc(2*sizeof(var *));
	var** outs = (var **) malloc(4*sizeof(var *));

	ins[0] = new_var('A', 1);
	ins[1] = new_var('B', 1);

	outs[0] = new_var('A', 0);
	outs[1] = new_var('A', 0);
	outs[2] = new_var('A', 0);
	outs[3] = new_var('A', 0);
	
	DECODER(2, ins, outs);
	
	if (
		!outs[0] -> value &&
		!outs[1] -> value &&
		outs[2] -> value &&
		!outs[3] -> value
	) {
		return 0;
		puts("\tPassed");
	} else {
		puts("Failed");
		return 1;
	}

	int i;
	for (i = 0; i < 2; i++) {
		free(ins[i]);
	}
	free(ins);

	for (i = 0; i < 4; i++) {
		free(outs[i]);
	}
	free(outs);
}

int test_MULTIPLEXERS() {
	puts("\tTesting Multiplexer computation");
	var** ins = (var **) malloc(4 * sizeof(var *));
	ins[0] = new_var('A', 0);	
	ins[1] = new_var('B', 1);
	ins[2] = new_var('C', 1);
	ins[3] = new_var('D', 0);

	var** selectors = (var **) malloc(2 * sizeof(var *));
	selectors[0] = new_var('a', 0);
	selectors[1] = new_var('b', 1);

	var* answer = new_var('Q', -1);

	MULTIPLEXER(4, ins, selectors, answer);
	if (answer -> value == 0) {
		puts("\tPassed");
		return 0;
	} else {
		puts("Failed");
		return 1;
	}
	int i;
	for (i = 0; i < 4; i++) {
		free(ins[i]);
	}
	free(ins);

	for (i = 0; i < 2; i++) {
		free(selectors[i]);
	}
	free(selectors);

	free(answer);
}


int main() {
	return 
		test_constructor()		||
		test_AND_1()			||
		test_AND_2()			||
		test_OR_1()				||
		test_OR_2()				||
		test_get_number_1()		||
		test_get_number_2() 	||
		test_dec_to_grey()		||
		test_to_string()		||
		test_DECODER_1()		||
		test_MULTIPLEXERS();
}
