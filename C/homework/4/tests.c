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

int main() {
	return 
		test_constructor()	||
		test_AND_1()		||
		test_AND_2()		||
		test_OR_1()			||
		test_OR_2()			||
		test_get_number_1()	||
		test_get_number_2() ||
		test_to_string();
}
