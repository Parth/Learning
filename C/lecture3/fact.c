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
