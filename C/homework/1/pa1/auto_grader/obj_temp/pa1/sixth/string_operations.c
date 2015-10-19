#include <stdio.h>

int main(int argc, char** args) {
	int i;
	for (i = 1; i < argc; i++) {
		printf("%c", args[i][0]);
	}
	printf("\n");
	return 0;
}
