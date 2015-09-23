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

	int** matrix = (int **) malloc(sizeof(int*) * row);

	int i, j;
	for (i = 0; i < row; i++) {
		matrix[i] = (int*) malloc(sizeof(int) * col);
		for (j = 0; j < col; j++) {
			int temp;
			fscanf(file, "%d", &temp);
			matrix[i][j] = temp;
		}

		fscanf(file, "\n");
		printf("\n");

	}

	for (i = 0; i < row; i++) {
		for (j = 0; j < col; j++) {
			printf("%d ", matrix[i][j]);
		}

		printf("\n");
	}

	fclose(file);


	return 0;
}
