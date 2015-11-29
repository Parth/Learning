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
void DECODER(int n, var** inputs, var** outputs);
void MULTIPLEXER(int n, var** inputs, var** selectors, var* output);
int get_number(var** inputs, int n);
int dec_to_grey(int n);
char* to_string(var* a);

#endif
