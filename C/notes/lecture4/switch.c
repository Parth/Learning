#include <stdio.h>
#include <stdlib.h>

int main(int argc, char** argv) {
	int a;
	printf("Enter a number\n");
	scanf("%d", &a);

	switch(a) {
		case 0:
			printf("the number is 0\n");
			break;
		case 1:
			printf("the number is 1 \n");
			break;
		default:
			printf("unknown number\n");
	}

	printf("I am done with switch");
	return 0;
}
