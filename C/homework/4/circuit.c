#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

#include "variable.h"

#define true 1
#define false 0

int get_instructions(FILE* instruction_file, char*** instructions) {
	int lines = 0;
	char ch;
	while (!feof(instruction_file)) {
		ch = fgetc(instruction_file);
	  	if (ch == '\n') {
	  		lines++;
	  	}
	}
	rewind(instruction_file);

	instructions[0] = (char **) malloc(lines * sizeof(char *));

	int line_index = 0;
	char* string = (char *) malloc(256 * sizeof(char *));
	while (fscanf(instruction_file, "%[^\n]\n", string) == 1) {
		(instructions[0])[line_index] = string;
		line_index++;
		string = (char *) malloc(256 * sizeof(char *));
	}

	return lines;
}

int get_input_variables(char** instructions, int num_instructions, var*** input_variables) {
	char instruction[16];
	char number_of_variables[4];
	char variables[64];
	sscanf(instructions[0], "%s %s %[^\n]\n", instruction, number_of_variables, variables);

	int n_input_variables = atoi(number_of_variables);
	input_variables[0] = (var **) malloc(n_input_variables * sizeof(var *));
	
	int i;
	for (i = 0; i < n_input_variables; i++) {
		input_variables[0][i] = new_var(variables[i*2], 0);
	}
	return n_input_variables;
}

int get_temporary_variables(char** instructions, int num_instructions, var*** temporary_variables) {
	int i;
	int g = 0;

	int is_temp_var['z' - 'a' - 1];
	for (i = 0; i < 26; i++) {
		is_temp_var[i] = 0;
	}

	for (i = 2; i < num_instructions; i++) {
		while (instructions[i][g] != 0) {
			if (instructions[i][g] >= 'a' && instructions[i][g] <= 'z') {
				is_temp_var[instructions[i][g] - 'a'] = 1;
			}
			g++;
		}
		g = 0;
	}

	int count = 0;
	for (i = 0; i < 26; i++) {
		if(is_temp_var[i] != 0) {
			count++;
		}
	}

	temporary_variables[0] = (var **) malloc(count * sizeof(var *));
	g = 0;
	for (i = 0; i < 26; i++) {
		if (is_temp_var[i] != 0) {
			temporary_variables[0][g] = new_var(i + 'a', 0);
			g++;
		}
	}
	return count;
}

int get_output_variables(char** instructions, int num_instructions, var*** output_variables) {
	char instruction[16];
	char number_of_variables[4];
	char variables[64];
	sscanf(instructions[1], "%s %s %[^\n]\n", instruction, number_of_variables, variables);

	int n_output_variables = atoi(number_of_variables);
	output_variables[0] = (var **) malloc(n_output_variables * sizeof(var *));
	
	int i;
	for (i = 0; i < n_output_variables; i++) {
		output_variables[0][i] = new_var(variables[i*2], 0);
	}
	return n_output_variables;
}

void execute_program(char** instructions, int n_instructions, var** vars) {
	int i;
	char instruction[16];
	char argument[128];
	for (i = 2; i < n_instructions; i++) {
		sscanf(instructions[i], "%s %[^\n]\n", instruction, argument);
		if (strcmp(instruction, "AND")) {
			vars[(int) argument[4]] -> value = AND(vars[(int)argument[0]], vars[(int)argument[2]]);
		} else if (strcmp(instruction, "OR")) {
			vars[(int) argument[4]] -> value = OR(vars[(int) argument[0]], vars[(int) argument[2]]);
		} else if (strcmp(instruction, "MULTIPLEXER")) {
			int num_inputs = argument[0];
			int num_selectors = (int) ceil(log2(num_inputs));
			var* inputs[num_inputs];
			var* selectors[num_selectors];
			int g;
			for (g = 0; g < num_inputs; g++) {
				inputs[g] = vars[(int) argument[g*2+2]];
			}
			for (g = 0; g < num_selectors; g++) {
				selectors[g] = vars[(int) argument[(2 * num_selectors + 2) + g * 2]];
			}
			var* output = vars[(int) argument[num_inputs*2 + num_selectors*2 + 3]];
			MULTIPLEXER(num_inputs, inputs, selectors, output);
		} else if (strcmp(instruction, "DECODER")) {
			int num_inputs = argument[0];
			int num_outputs = pow(num_inputs, 2);
			var* inputs[num_inputs];
			var* outputs[num_outputs];
			int g;
			for (g = 0; g < num_inputs; g++) {
				inputs[g] = vars[(int) argument[g*2 + 2]];
			}

			for (g = 0; g < num_outputs; g++) {
				outputs[g] = vars[(int) argument[(2 * num_inputs + 2) + g * 2]];
			}
			DECODER(num_inputs, inputs, outputs);
		}
	}

}

void print_output(var** output_variables, int n_outputs) {
	int i;
	for (i = 0; i < n_outputs; i++) {
		if (i != n_outputs-1) {
			printf("%d ", output_variables[i] -> value);
		} else {
			printf("%d\n", output_variables[i] -> value);
		}
	}
}

void run_program(char** instructions, int n_instructions, var** input_variables, int n_inputs, var** temp_variables, int n_temps, var** output_variables, int n_outputs) {
	var** vars = (var **) malloc(256 * sizeof(var *));
	vars['0'] = new_var('0', 0);
	vars['1'] = new_var('1', 1);
	
	int i;
	for (i = 0; i < n_inputs; i++) {
		vars[(int) (input_variables[i] -> name)] = input_variables[i];
	}

	for (i = 0; i < n_temps; i++) {
		vars[(int) (temp_variables[i] -> name)] = temp_variables[i];
	}

	for (i = 0; i < n_outputs; i++) {
		vars[(int) (output_variables[i] -> name)] = output_variables[i];
	}


	execute_program(instructions, n_instructions, vars);
	print_output(output_variables, n_outputs);
}

int main(int argc, char** args) {
	FILE* instruction_file = fopen(args[1], "r");
	char** instructions = NULL;
	int lines = get_instructions(instruction_file, &instructions);
	fclose(instruction_file);

	var** input_variables = NULL;
	int num_input_variables = get_input_variables(instructions, lines, &input_variables);

	var** temp_variables = NULL;
	int num_temp_variables = get_temporary_variables(instructions, lines, &temp_variables);

	var** output_variables = NULL;
	int num_output_variables = get_output_variables(instructions, lines, &output_variables);

	FILE* value_file = fopen(args[2], "r");
	char* string = (char *) malloc(256 * sizeof(char *));
	while (fscanf(value_file, "%[^\n]\n", string) == 1) {
		int i;
		for (i = 0; i < num_output_variables; i++) {
			input_variables[i] -> value = string[i*2];
		}

		run_program(instructions, lines, 
			input_variables, num_input_variables, 
			temp_variables, num_temp_variables,
			output_variables, num_output_variables
		);
	}
	return 0;
}
