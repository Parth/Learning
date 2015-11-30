#include <stdio.h>
#include <stdlib.h>

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

int get_inputVariables(char** instructions, int num_instructions, var*** input_variables) {
	
}

int get_temporaryVariables(char** instructions, int num_instructions, var*** temporary_variables) {

}

int get_output_variables(char** instructions, int num_instructions, var*** output_variables) {
	
}

int main(int argc, char** args) {
	FILE* instruction_file = fopen(args[1], "r");
	char** instructions = NULL;
	int lines = get_instructions(instruction_file, &instructions);
	printf("input variables: %d, temporary variables %d, output_variables %d\n", get_inputVariables(
	fclose(instruction_file);

	FILE* input_file = fopen(args[2], "r");
	fclose(input_file);
}
