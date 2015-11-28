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

int test_to_number() {
	
}

int main() {
	return 
		test_constructor()	||
		test_AND_1()		||
		test_AND_2()		||
		test_OR_1()			||
		test_OR_2()			||
		test_get_number()	||
		test_to_string();
}
