#include<stdio.h>
#include<stdlib.h>

int fact(int n) {
	if (n < 0) {
		printf("faulty input");
		exit(1);
	}

	if (n == 0 || n == 1) {
		return 1;
	}

	return n * fact(n-1);
}

int main(int argc, char** argv) {
	int a = 0;
	int result = 0;

	printf("Input a number\n");
	scanf("%d", &a);

	result = fact(a);

	printf("fact(%d) = %d\n", a, result);
}
