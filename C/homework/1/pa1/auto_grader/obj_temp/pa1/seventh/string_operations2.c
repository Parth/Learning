#include <stdio.h>
#include <string.h>

int main(int argc, char** args) {
	int i;
	for (i = 1; i < argc; i++) {
		printf("%c", args[i][strlen(args[i]) - 1]);
	}
	printf("\n");
	return 0;
}
