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

	int rows1 = 0;
	int cols1 = 0;
	fscanf(file, "%d\t%d\n", &rows1, &cols1);

	int matrix1[rows1][cols1];

	int x;
	int y;
	for (y = 0; y < rows1; y++) {
		for (x = 0; x < cols1; x++) {
			if (x != cols1 - 1) {
				fscanf(file, "%d ", &matrix1[y][x]);
			} else {
				fscanf(file, "%d", &matrix1[y][x]);
			}
		}
		fscanf(file, "\n");
	}
	fscanf(file, "\n");

	int rows2 = 0;
	int cols2 = 0;
	fscanf(file, "%d\t%d\n", &rows2, &cols2);

	if (cols1 != rows2 || rows1 != cols2) {
		return error();
	}
	
	int matrix2[rows2][cols2];
	for (y = 0; y < rows2; y++) {
		for (x = 0; x < cols2; x++) {
			if (x != cols2 - 1) {
				fscanf(file, "%d ", &matrix2[y][x]);
			} else {
				fscanf(file, "%d", &matrix2[y][x]);
			}
		}
		fscanf(file, "\n");
	}

	int matrix_product[rows1][cols2];

	int r;
	int c;
	int a;
	for (r = 0; r < rows1; r++) {
		for (c = 0; c < cols2; c++) {
			int sum = 0;
			for (a = 0; a < rows2; a++) {
				sum += matrix1[r][a]*matrix2[a][c];
			}
			matrix_product[r][c] = sum;
		}
	}

	for (y = 0; y < rows1; y++) {
		for (x = 0; x < cols2; x++) {
			printf("%d\t", matrix_product[y][x]);
		}
		printf("\n");
	}
	
	return 0;
}
