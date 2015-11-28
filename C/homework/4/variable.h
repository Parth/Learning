#ifndef VARIABLE_H
#define VARIABLE_H

struct var {
	char name;
	int value;
};
typedef struct var var;

var* new_var(char name, int value);
int AND(var* a, var* b);
int OR(var* a, var* b);
char* to_string(var* a);

#endif
