#include <stdio.h>

#include "variable.h"

int AND(var* a, var* b) {
	return (a -> value) && (b -> value);
}

int OR(var* a, var* b) {
	return (a -> value) || (b -> value);
}

char* to_string(var* a) {
	char* str; 
	asprintf(&str, "%c = %d", a -> name, a -> value);
	return str;
}
