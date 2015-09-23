#include<stdio.h>
#include<stdlib.h>

int main(int argc, char** args) {

	FILE* file;
	int row, col;
	
	if (argc != 2) {
		printf("error\n");
		return 0;
	}

	file = fopen(args[1], "r");

	if (file == NULL) {
		printf("file does not exist\n");
		return 0;
	}

	fscanf(file, "%d %d\n", &row, &col);

	int i, j;
	for (i = 0; i < row; i++) {
		for (j = 0; j < cols; j++) {
			int temp;
			fscanf(file, "%d", &temp);
			printf("%d ", temp);
		}
		fscanf("\n");
		printf("\n");

	}

	fclose(file);

	return 0;
}
