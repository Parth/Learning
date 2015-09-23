#include<stdio.h>
#include<stdlib.h>

int main(int argc, char** args) {

	FILE* file;
	int d;
	char c;
	
	if (argc != 2) {
		printf("error\n");
		return 0;
	}

	file = fopen(args[1], "r");

	if (file == NULL) {
		printf("file does not exist\n");
		return 0;
	}

	while(fscanf(file, "%c %d\n", &c, &d) == 2) {
		printf("%c, %d\n", c, d);
	}

	fclose(file);

	return 0;
}
