#include <stdio.h>

int main() {
	int a = 5;
	int* b = &a;
	int c = *b;
	printf("%u\n", a);
	printf("%u\n", b);
	printf("%u\n", *b);
	printf("%u\n", c);
}
