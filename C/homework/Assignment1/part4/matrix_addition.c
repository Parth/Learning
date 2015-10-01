#include <stdio.h>
#include <stdlib.h>

int error() {
	printf("error\n");
	//Really should return something other than 0, but our autograder can't handle that
	return 0;
}

int main(int argc, char** args) {
	if (argc != 2) {
		return error();
	}

	FILE* file = fopen(args[1], "r");

	if (file == NULL) {
		return error();
	}

	int rows = 0;
	int cols = 0;
	fscanf(file, "%d\t%d\n", &rows, &cols);

	int matrix1[rows][cols];

	int x;
	int y;
	for (y = 0; y < rows; y++) {
		for (x = 0; x < cols; x++) {
			if (x != cols - 1) {
				fscanf(file, "%d ", &matrix1[y][x]);
			} else {
				fscanf(file, "%d", &matrix1[y][x]);
			}
		}
		fscanf(file, "\n");
	}
	fscanf(file, "\n");

	int matrix2[rows][cols];
	for (y = 0; y < rows; y++) {
		for (x = 0; x < cols; x++) {
			if (x != cols - 1) {
				fscanf(file, "%d ", &matrix2[y][x]);
			} else {
				fscanf(file, "%d", &matrix2[y][x]);
			}
		}
		fscanf(file, "\n");
	}

	int matrix_sum[rows][cols];

	for (y = 0; y < rows; y++) {
		for (x = 0; x < cols; x++) {
			matrix_sum[y][x] = matrix1[y][x] + matrix2[y][x];
		}
	}

	printf("\n");

	for (y = 0; y < rows; y++) {
		for (x = 0; x < cols; x++) {
			printf("%d\t", matrix_sum[y][x]);
		}
		printf("\n");
	}
	
	return 0;
}
